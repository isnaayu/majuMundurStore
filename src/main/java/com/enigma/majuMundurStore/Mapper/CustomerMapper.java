package com.enigma.majuMundurStore.Mapper;

import com.enigma.majuMundurStore.dto.request.CustomerRequest;
import com.enigma.majuMundurStore.dto.response.CustomerResponse;
import com.enigma.majuMundurStore.entity.Customer;

public interface CustomerMapper {
    Customer mapToCustomerRequest(CustomerRequest customerRequest);
    CustomerResponse mapToCustomerResponse(Customer customer);
}
