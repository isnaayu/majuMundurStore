package com.enigma.majuMundurStore.controller;

import com.enigma.majuMundurStore.constant.AppPath;
import com.enigma.majuMundurStore.dto.request.OrderRequest;
import com.enigma.majuMundurStore.dto.response.CommonResponse;
import com.enigma.majuMundurStore.dto.response.OrderResponse;
import com.enigma.majuMundurStore.service.TrxOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.ORDER)
public class TrxOrderController {
    private final TrxOrderService trxOrderService;

    @PostMapping
    public ResponseEntity<?> order(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = trxOrderService.create(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommonResponse.<OrderResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Order Product")
                        .data(orderResponse)
                        .build()
        );
    }
}
