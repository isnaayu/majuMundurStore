package com.enigma.majuMundurStore.service;

import com.enigma.majuMundurStore.dto.request.OrderRequest;
import com.enigma.majuMundurStore.dto.response.OrderResponse;

public interface TrxOrderService {
    OrderResponse create(OrderRequest orderRequest);
}
