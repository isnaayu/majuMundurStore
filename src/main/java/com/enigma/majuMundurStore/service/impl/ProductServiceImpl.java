package com.enigma.majuMundurStore.service.impl;

import com.enigma.majuMundurStore.Mapper.ProductMapper;
import com.enigma.majuMundurStore.dto.request.ProductRequest;
import com.enigma.majuMundurStore.dto.response.ProductResponse;
import com.enigma.majuMundurStore.entity.Product;
import com.enigma.majuMundurStore.entity.ProductPrice;
import com.enigma.majuMundurStore.repository.ProductRepository;
import com.enigma.majuMundurStore.service.ProductPriceService;
import com.enigma.majuMundurStore.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductPriceService productPriceService;
    private final ProductMapper productMapper;
    @Transactional(rollbackOn = Exception.class)
    @Override
    public ProductResponse createProductAndProductPrice(ProductRequest productRequest) {
        Product product = productMapper.mapToProductRequest(productRequest);
        productRepository.saveAndFlush(product);
        ProductPrice productPrice = ProductPrice.builder()
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .isActive(true)
                .product(product)
                .build();
        productPriceService.create(productPrice);
        return productMapper.mapToProductResponse(product, productPrice);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll().stream()
                .filter(product -> product.getProductPrices().stream().anyMatch(ProductPrice::isActive))
                .map(product -> Product.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .productPrices(product.getProductPrices())
                .build()).collect(Collectors.toList());
    }

    @Override
    public ProductResponse getById(String id) {
        return productRepository.findById(id).map((product -> {
            ProductResponse productResponse = new ProductResponse();
            ProductPrice productPrice = productPriceService.findProductPriceIsActive(product.getId(), true);
            productResponse.setProductId(product.getId());
            productResponse.setProductName(product.getName());
            productResponse.setProductDesc(product.getDescription());
            productResponse.setStock(productPrice.getStock());
            productResponse.setPrice(productPrice.getPrice());
            return productResponse;
        })).orElseThrow(()-> new  ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
    }

    @Override
    public void delete(String id) {
        ProductResponse productResponse = getById(id);
        if (productResponse != null){
            ProductPrice productPrice = productPriceService.findProductPriceIsActive(productResponse.getProductId(), true);
            productPrice.setActive(false);
            productPriceService.create(productPrice);
            System.out.println("Successfully Deleted Product");

        }else {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        }
    }
}
