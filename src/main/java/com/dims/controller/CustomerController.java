package com.dims.controller;

import com.dims.dto.CustomerDto;
import com.dims.service.CustomerServiceI;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceI customerServiceI;

    @PostMapping("/createNewCustomer")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerServiceI.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id, @Valid @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerServiceI.updateCustomerById(id,customerDto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateCustomer/{aadhar}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String aadhar,@Valid @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerServiceI.updateCustomerByAadhar(aadhar,customerDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable long id) {
        return new ResponseEntity<>(customerServiceI.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping("/getCustomerByAadhar")
    public ResponseEntity<CustomerDto> getCustomerByAadhar(@PathVariable String aadhar) {
        return new ResponseEntity<>(customerServiceI.getCustomerByAadhar(aadhar), HttpStatus.OK);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return new ResponseEntity<>(customerServiceI.getAllCustomers(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomerById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        return new ResponseEntity<>(customerServiceI.deleteById(id),HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomerByAadhar/{aadhar}")
    public ResponseEntity<String> deleteByAadhar(@PathVariable String aadhar){
        return new ResponseEntity<>(customerServiceI.deleteByAadhar(aadhar),HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllCustomer")
    public ResponseEntity<String> deleteAllCustomer(){
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
