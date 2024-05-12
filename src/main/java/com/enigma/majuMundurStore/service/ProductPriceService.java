package com.enigma.majuMundurStore.service;

import com.enigma.majuMundurStore.entity.ProductPrice;

public interface ProductPriceService {
    ProductPrice create(ProductPrice productPrice);
    ProductPrice getById(String id);
    ProductPrice findProductPriceIsActive(String id, boolean active);

}
