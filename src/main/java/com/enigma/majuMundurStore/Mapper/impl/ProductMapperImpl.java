package com.enigma.majuMundurStore.Mapper.impl;

import com.enigma.majuMundurStore.Mapper.ProductMapper;
import com.enigma.majuMundurStore.dto.request.ProductRequest;
import com.enigma.majuMundurStore.dto.response.ProductResponse;
import com.enigma.majuMundurStore.entity.Product;
import com.enigma.majuMundurStore.entity.ProductPrice;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product mapToProductRequest(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .build();
    }

    @Override
    public ProductResponse mapToProductResponse(Product product, ProductPrice productPrice) {
        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getName())
                .ProductDesc(product.getDescription())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .build();
    }
}
