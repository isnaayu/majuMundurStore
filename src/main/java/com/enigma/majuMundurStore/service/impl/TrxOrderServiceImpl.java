package com.enigma.majuMundurStore.service.impl;

import com.enigma.majuMundurStore.dto.request.OrderDetailRequest;
import com.enigma.majuMundurStore.dto.request.OrderRequest;
import com.enigma.majuMundurStore.dto.response.CustomerResponse;
import com.enigma.majuMundurStore.dto.response.OrderDetailResponse;
import com.enigma.majuMundurStore.dto.response.OrderResponse;
import com.enigma.majuMundurStore.dto.response.ProductResponse;
import com.enigma.majuMundurStore.entity.Customer;
import com.enigma.majuMundurStore.entity.ProductPrice;
import com.enigma.majuMundurStore.entity.TrxOrder;
import com.enigma.majuMundurStore.entity.TrxOrderDetail;
import com.enigma.majuMundurStore.repository.TrxOrderDetailRepository;
import com.enigma.majuMundurStore.repository.TrxOrderRepository;
import com.enigma.majuMundurStore.service.CustomerService;
import com.enigma.majuMundurStore.service.ProductPriceService;
import com.enigma.majuMundurStore.service.TrxOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrxOrderServiceImpl implements TrxOrderService {
    private final TrxOrderRepository orderRepository;
    private final TrxOrderDetailRepository orderDetailRepository;
    private final ProductPriceService productPriceService;
    private final CustomerService customerService;
    @Transactional(rollbackOn = Exception.class)
    @Override
    public OrderResponse create(OrderRequest orderRequest) {
        CustomerResponse customerResponse = customerService.getById(orderRequest.getCustomerId());
        List<TrxOrderDetail> orderDetails = orderRequest.getOrderDetails().stream()
                .map(orderDetailRequest -> {
                    ProductPrice productPrice = productPriceService.getById(orderDetailRequest.getProductPriceId());
                    TrxOrderDetail trxOrderDetail = TrxOrderDetail.builder()
                            .productPrice(productPrice)
                            .quantity(orderDetailRequest.getQuantity())
                            .build();
                    return orderDetailRepository.save(trxOrderDetail);
                })
                .toList();

        TrxOrder order = TrxOrder.builder()
                .orderDate(LocalDateTime.now())
                .orderDetails(orderDetails)
                .customer(Customer.builder()
                        .id(customerResponse.getId())
                        .build())
                .build();
        orderRepository.saveAndFlush(order);

        List<OrderDetailResponse> orderDetailResponses = order.getOrderDetails().stream().map(trxOrderDetail -> {
                    trxOrderDetail.setOrder(order);
                    ProductPrice currentProductPrice = trxOrderDetail.getProductPrice();
                    currentProductPrice.setStock(currentProductPrice.getStock() - trxOrderDetail.getQuantity());
                    return OrderDetailResponse.builder()
                            .orderDetailId(trxOrderDetail.getId())
                            .quantity(trxOrderDetail.getQuantity())
                            .product(ProductResponse.builder()
                                    .productId(currentProductPrice.getProduct().getId())
                                    .productName(currentProductPrice.getProduct().getName())
                                    .ProductDesc(currentProductPrice.getProduct().getDescription())
                                    .price(currentProductPrice.getPrice())
                                    .stock(currentProductPrice.getStock())
                                    .build())
                            .build();
                }).toList();
        return OrderResponse.builder()
                .orderId(order.getId())
                .customer(customerResponse)
                .orderDetails(orderDetailResponses)
                .orderDate(order.getOrderDate())
                .build();
    }
}
