package com.dims.controller;

import com.dims.dto.CustomerDto;
import com.dims.service.CustomerServiceI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerServiceI customerServiceI;

    @InjectMocks
    private CustomerController customerController;

    private CustomerDto customerDto;

    @BeforeEach
    void setUp(){
        customerDto=new CustomerDto(1L,"Amar","amar0000@gmail.com"
                ,"9112676716","775426600348","NPSPS9595J"
                , LocalDate.of(2000,10, 30),"At.Pune");
    }

    @Test
    void testCreateCustomer() {
        when(customerServiceI.createCustomer(customerDto)).thenReturn(customerDto);

        ResponseEntity<CustomerDto> response = customerController.createCustomer(customerDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerDto, response.getBody());
        verify(customerServiceI, times(1)).createCustomer(customerDto);
    }

    @Test
    void testUpdateCustomerById(){
        when(customerServiceI.updateCustomerById(1L,customerDto)).thenReturn(customerDto);

        ResponseEntity<CustomerDto> response = customerController.updateCustomer(1L, customerDto);

        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        assertEquals(customerDto,response.getBody());
        verify(customerServiceI,times(1)).updateCustomerById(1L,customerDto);
    }

    @Test
    void testUpdateCustomerByAadhar(){
        when(customerServiceI.updateCustomerByAadhar("775426600348",customerDto)).thenReturn(customerDto);

        ResponseEntity<CustomerDto> response = customerController.updateCustomer("775426600348", customerDto);

        assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
        assertEquals(customerDto,response.getBody());
        verify(customerServiceI,times(1)).updateCustomerByAadhar("775426600348",customerDto);
    }

    @Test
    void testGetCustomerById(){
        when(customerServiceI.getCustomerById(1L)).thenReturn(customerDto);

        ResponseEntity<CustomerDto> response = customerController.getCustomerById(1L);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(customerDto,response.getBody());
        verify(customerServiceI,times(1)).getCustomerById(1L);
    }

    @Test
    void testGetCustomerByAadhar(){
        when(customerServiceI.getCustomerByAadhar("775426600348")).thenReturn(customerDto);

        ResponseEntity<CustomerDto> response = customerController.getCustomerByAadhar("775426600348");

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(customerDto,response.getBody());
        verify(customerServiceI,times(1)).getCustomerByAadhar("775426600348");
    }

    @Test
    void testGetAllCustomers(){
        List<CustomerDto> customers = Arrays.asList(new CustomerDto());
        when(customerServiceI.getAllCustomers()).thenReturn(customers);

        ResponseEntity<List<CustomerDto>> response = customerController.getAllCustomers();

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(customers,response.getBody());
        verify(customerServiceI,times(1)).getAllCustomers();
    }

    @Test
    void testDeleteById(){
        String s= "Deleted";
        when(customerServiceI.deleteById(1L)).thenReturn(s);

        ResponseEntity<String> response = customerController.deleteById(1L);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Deleted",response.getBody());
        verify(customerServiceI,times(1)).deleteById(1L);
    }

    @Test
    void testDeleteByAadhar(){
        when(customerServiceI.deleteByAadhar("775426600348")).thenReturn("Deleted");

        ResponseEntity<String> response = customerController.deleteByAadhar("775426600348");

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Deleted",response.getBody());
        verify(customerServiceI,times(1)).deleteByAadhar("775426600348");
    }

    @Test
    void testDeleteAllCustomer(){
        when(customerServiceI.deleteAll()).thenReturn("Deleted");

        ResponseEntity<String> response = customerController.deleteAllCustomer();

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Deleted",response.getBody());
        verify(customerServiceI,times(1)).deleteAll();
    }

}
