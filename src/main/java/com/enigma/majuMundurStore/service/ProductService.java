package com.enigma.majuMundurStore.service;

import com.enigma.majuMundurStore.dto.request.ProductRequest;
import com.enigma.majuMundurStore.dto.response.ProductResponse;
import com.enigma.majuMundurStore.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ProductResponse createProductAndProductPrice(ProductRequest productRequest);
    List<Product> getAll();
    ProductResponse getById(String id);
    void delete(String id);
}
