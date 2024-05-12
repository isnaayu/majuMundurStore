package com.enigma.majuMundurStore.service.impl;

import com.enigma.majuMundurStore.entity.ProductPrice;
import com.enigma.majuMundurStore.repository.ProductPriceRepository;
import com.enigma.majuMundurStore.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {
    private final ProductPriceRepository productPriceRepository;
    @Override
    public ProductPrice create(ProductPrice productPrice) {
        return productPriceRepository.save(productPrice);
    }

    @Override
    public ProductPrice getById(String id) {
        return productPriceRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductPrice findProductPriceIsActive(String id, boolean active) {
        return productPriceRepository.findByProduct_IdAndIsActive(id, active).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
    }
}
