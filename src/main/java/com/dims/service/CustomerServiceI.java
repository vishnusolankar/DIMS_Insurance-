package com.dims.service;

import com.dims.dto.CustomerDto;

import java.util.List;

public interface CustomerServiceI {

    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomerById(Long id,CustomerDto customerDto);
    CustomerDto updateCustomerByAadhar(String aadhar,CustomerDto customerDto);
    CustomerDto getCustomerById(Long id);
    CustomerDto getCustomerByAadhar(String aadhar);
    List<CustomerDto> getAllCustomers();
    String deleteById(Long id);
    String deleteByAadhar(String aadhar);
    String deleteAll();


}
