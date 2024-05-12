package com.enigma.majuMundurStore.Mapper.impl;

import com.enigma.majuMundurStore.Mapper.CustomerMapper;
import com.enigma.majuMundurStore.dto.request.CustomerRequest;
import com.enigma.majuMundurStore.dto.response.CustomerResponse;
import com.enigma.majuMundurStore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public Customer mapToCustomerRequest(CustomerRequest customerRequest) {
        return Customer.builder()
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .mobilePhone(customerRequest.getMobilePhone())
                .email(customerRequest.getEmail())
                .build();
    }

    @Override
    public CustomerResponse mapToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .CustomerName(customer.getName())
                .address(customer.getAddress())
                .phone(customer.getMobilePhone())
                .email(customer.getEmail())
                .build();
    }

}
