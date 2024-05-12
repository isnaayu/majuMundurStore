package com.enigma.majuMundurStore.service;

import com.enigma.majuMundurStore.dto.request.CustomerRequest;
import com.enigma.majuMundurStore.dto.response.CustomerResponse;
import com.enigma.majuMundurStore.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest customerRequest);
    CustomerResponse update(CustomerRequest customerRequest);
    List<Customer> getAll();
    CustomerResponse getById(String id);
    void delete(String id);
    CustomerResponse createNewCustomer(Customer request);
}
