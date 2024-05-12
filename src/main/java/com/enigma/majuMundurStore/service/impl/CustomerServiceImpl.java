package com.enigma.majuMundurStore.service.impl;

import com.enigma.majuMundurStore.Mapper.CustomerMapper;
import com.enigma.majuMundurStore.dto.request.CustomerRequest;
import com.enigma.majuMundurStore.dto.response.CustomerResponse;
import com.enigma.majuMundurStore.entity.Customer;
import com.enigma.majuMundurStore.repository.CustomerRepository;
import com.enigma.majuMundurStore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        Customer customer = customerMapper.mapToCustomerRequest(customerRequest);
        customerRepository.save(customer);
        return customerMapper.mapToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse update(CustomerRequest customerRequest) {
        CustomerResponse customerResponse = getById(customerRequest.getId());
        if (customerResponse != null){
            Customer customer = customerMapper.mapToCustomerRequest(customerRequest);
            customer.setId(customerRequest.getId());
            customerRepository.save(customer);
            return customerMapper.mapToCustomerResponse(customer);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found");
        }
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll().stream()
                .map(customer -> Customer.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .address(customer.getAddress())
                        .mobilePhone(customer.getMobilePhone())
                        .email(customer.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse getById(String id) {
        return customerRepository.findById(id).map((customer -> {
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setId(customer.getId());
            customerResponse.setCustomerName(customer.getName());
            customerResponse.setAddress(customer.getAddress());
            customerResponse.setPhone(customer.getMobilePhone());
            customerResponse.setEmail(customer.getEmail());
            return customerResponse;
        })).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found"));
    }

    @Override
    public void delete(String id) {
        if (getById(id) != null){
            customerRepository.deleteById(id);
            System.out.println("Successfully Deleted Customer");
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found");
        }
    }

    @Override
    public CustomerResponse createNewCustomer(Customer request) {
        Customer customer = customerRepository.saveAndFlush(request);
        return CustomerResponse.builder()
                .id(customer.getId())
                .CustomerName(customer.getName())
                .phone(customer.getMobilePhone())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
}
