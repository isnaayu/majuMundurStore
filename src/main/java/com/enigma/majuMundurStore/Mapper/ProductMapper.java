package com.enigma.majuMundurStore.Mapper;

import com.enigma.majuMundurStore.dto.request.ProductRequest;
import com.enigma.majuMundurStore.dto.response.ProductResponse;
import com.enigma.majuMundurStore.entity.Product;
import com.enigma.majuMundurStore.entity.ProductPrice;

public interface ProductMapper {
    Product mapToProductRequest(ProductRequest productRequest);
    ProductResponse mapToProductResponse(Product product, ProductPrice productPrice);
}
