package com.enigma.majuMundurStore.controller;

import com.enigma.majuMundurStore.constant.AppPath;
import com.enigma.majuMundurStore.dto.request.ProductRequest;
import com.enigma.majuMundurStore.dto.response.CommonResponse;
import com.enigma.majuMundurStore.dto.response.ProductResponse;
import com.enigma.majuMundurStore.entity.Product;
import com.enigma.majuMundurStore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.MERCHANT)
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.createProductAndProductPrice(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<ProductResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Created Product")
                        .data(productResponse).build()
                );
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Product> productResponse = productService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Product")
                        .data(productResponse)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        ProductResponse productResponse = productService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<ProductResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get Product By Id")
                        .data(productResponse)
                        .build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        productService.delete(id);
    }




}
