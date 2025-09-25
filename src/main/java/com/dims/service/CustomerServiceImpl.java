package com.dims.service;

import com.dims.dto.CustomerDto;
import com.dims.entity.Customer;
import com.dims.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerServiceImpl implements CustomerServiceI{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return modelMapper.map(customerRepository.save(modelMapper.map(customerDto, Customer.class)),CustomerDto.class);
    }

    @Override
    public CustomerDto updateCustomerById(Long id, CustomerDto customerDto) {
        Optional<Customer> byId = customerRepository.findById(id);


        if(byId.isPresent()){
            Customer cust = new Customer().builder()
                    .name(customerDto.getName())
                    .email(customerDto.getEmail())
                    .aadhar(customerDto.getAadharNumber())
                    .dob(customerDto.getDob())
                    .phoneNumber(customerDto.getPhoneNumber())
                    .panNumber(customerDto.getPanNumber())
                    .address(customerDto.getAddress())
                    .build();
            return modelMapper.map(customerRepository.save(cust),CustomerDto.class);
        }else {
            return null;
        }
    }

    @Override
    public CustomerDto updateCustomerByAadhar(String aadhar, CustomerDto customerDto) {

        Optional<Customer> byId = customerRepository.findByAadhar(aadhar);

        if(byId.isPresent()){
            Customer cust = new Customer().builder()
                    .name(customerDto.getName())
                    .email(customerDto.getEmail())
                    .aadhar(customerDto.getAadharNumber())
                    .dob(customerDto.getDob())
                    .phoneNumber(customerDto.getPhoneNumber())
                    .panNumber(customerDto.getPanNumber())
                    .address(customerDto.getAddress())
                    .build();
            return modelMapper.map(customerRepository.save(cust),CustomerDto.class);
        }else {
            return null;
        }
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).get();
        return modelMapper.map(customer,CustomerDto.class);
    }

    @Override
    public CustomerDto getCustomerByAadhar(String aadhar) {
        Customer customer = customerRepository.findByAadhar(aadhar).get();
        return modelMapper.map(customer,CustomerDto.class);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(customer -> modelMapper.map(customer,CustomerDto.class)).toList();
    }

    @Override
    public String deleteById(Long id) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isPresent()){
            customerRepository.deleteById(id);
            return "Record deleted Successfully";
        }else {
            return "Customer Not found in Record";
        }
    }

    @Override
    public String deleteByAadhar(String aadhar) {
        Optional<Customer> byId = customerRepository.findByAadhar(aadhar);
        if (byId.isPresent()){
            customerRepository.deleteByAadhar(aadhar);
            return "Record deleted Successfully";
        }else {
            return "Customer Not found in Record";
        }
    }

    @Override
    public String deleteAll() {
        customerRepository.deleteAll();
        return "All Records Deleted Successfully";
    }
}
