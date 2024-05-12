package com.enigma.majuMundurStore.controller;

import com.enigma.majuMundurStore.constant.AppPath;
import com.enigma.majuMundurStore.dto.request.CustomerRequest;
import com.enigma.majuMundurStore.dto.response.CommonResponse;
import com.enigma.majuMundurStore.dto.response.CustomerResponse;
import com.enigma.majuMundurStore.entity.Customer;
import com.enigma.majuMundurStore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.CUSTOMER)
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.create(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<CustomerResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Created Customer")
                        .data(customerResponse)
                        .build()
                );
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Customer> customerResponse = customerService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Customer")
                        .data(customerResponse)
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        CustomerResponse customerResponse = customerService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Customer")
                        .data(customerResponse)
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.update(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<CustomerResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Update Customer")
                        .data(customerResponse)
                        .build()
                );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        customerService.delete(id);
    }

}
