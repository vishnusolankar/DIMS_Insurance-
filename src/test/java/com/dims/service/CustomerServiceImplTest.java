package com.dims.service;

import com.dims.dto.CustomerDto;
import com.dims.entity.Customer;
import com.dims.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerDto customerDto;
    private Customer customer;

    @BeforeEach
    void setUp(){
        customerDto=new CustomerDto(1L,"Amar","amar0000@gmail.com"
                ,"9112676716","775426600348","NPSPS9595J"
                , LocalDate.of(2000,10, 30),"At.Pune");
        customer=new Customer(1L,"Amar","amar0000@gmail.com"
                ,"9112676716","775426600348","NPSPS9595J"
                , LocalDate.of(2000,10, 30),"At.Pune");
    }

    @Test
    void testCreateCustomer(){
        when(modelMapper.map(customerDto,Customer.class)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(modelMapper.map(customer,CustomerDto.class)).thenReturn(customerDto);

        CustomerDto response = customerService.createCustomer(customerDto);

        assertNotNull(response);
        assertEquals("Amar",response.getName());
        verify(customerRepository,times(1)).save(customer);
    }

    @Test
    void testUpdateCustomerById_FOUND(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(modelMapper.map(customer,CustomerDto.class)).thenReturn(customerDto);

        CustomerDto response = customerService.updateCustomerById(1L, customerDto);

        assertNotNull(response);
        assertEquals("Amar",response.getName());
        verify(customerRepository,times(1)).findById(1L);
        verify(customerRepository,times(1)).save(any(Customer.class));
    }

    @Test
    void testUpdateCustomerById_NOTFOUND(){
        when(customerRepository.findById(2L)).thenReturn(Optional.empty());

        CustomerDto response = customerService.updateCustomerById(2L, customerDto);

        assertNull(response);
        verify(customerRepository,times(1)).findById(2L);
        verify(customerRepository,never()).save(any(Customer.class));
    }

    @Test
    void testUpdateCustomerByAadhar_FOUND() {
        when(customerRepository.findByAadhar("775426600348")).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(modelMapper.map(customer,CustomerDto.class)).thenReturn(customerDto);

        CustomerDto response = customerService.updateCustomerByAadhar("775426600348", customerDto);

        assertNotNull(response);
        assertEquals("Amar",response.getName());
        verify(customerRepository,times(1)).findByAadhar("775426600348");
        verify(customerRepository,times(1)).save(any(Customer.class));
    }

    @Test
    void testUpdateCustomerByAadhar_NOTFOUND(){
        when(customerRepository.findByAadhar("775426600349")).thenReturn(Optional.empty());

        CustomerDto response = customerService.updateCustomerByAadhar("775426600349", customerDto);

        assertNull(response);
        verify(customerRepository,times(1)).findByAadhar("775426600349");
        verify(customerRepository,never()).save(any(Customer.class));
    }

    @Test
    void testGetCustomerById(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(modelMapper.map(customer,CustomerDto.class)).thenReturn(customerDto);

        CustomerDto response = customerService.getCustomerById(1L);

        assertNotNull(response);
        assertEquals("Amar",response.getName());
        verify(customerRepository,times(1)).findById(1L);

    }

    @Test
    void testGetCustomerByAadhar(){
        when(customerRepository.findByAadhar("775426600348")).thenReturn(Optional.of(customer));
        when(modelMapper.map(customer,CustomerDto.class)).thenReturn(customerDto);

        CustomerDto response = customerService.getCustomerByAadhar("775426600348");

        assertNotNull(response);
        assertEquals("Amar",response.getName());
        verify(customerRepository,times(1)).findByAadhar("775426600348");
    }

    @Test
    void testGetAllCustomers(){
        List<Customer> customers = Arrays.asList(customer, new Customer());
        when(customerRepository.findAll()).thenReturn(customers);
        when(modelMapper.map(any(Customer.class), eq(CustomerDto.class))).thenReturn(customerDto);

        List<CustomerDto> response = customerService.getAllCustomers();
        for (CustomerDto c: response){
            System.out.println(c);
        }

        assertEquals(2,response.size());
        verify(customerRepository,times(1)).findAll();
    }

    @Test
    void testDeleteById_FOUND() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        String response = customerService.deleteById(1L);

        assertEquals("Record deleted Successfully",response);
        verify(customerRepository,times(1)).deleteById(1L);
    }

    @Test
    void testDeleteById_NOTFOUND() {
        when(customerRepository.findById(2L)).thenReturn(Optional.empty());

        String response = customerService.deleteById(2L);

        assertEquals("Customer Not found in Record",response);
        verify(customerRepository, never()).deleteById(2L);
    }

    @Test
    void testDeleteByAadhar() {
        when(customerRepository.findByAadhar("775426600349")).thenReturn(Optional.of(customer));

        String response = customerService.deleteByAadhar("775426600349");

        assertEquals("Record deleted Successfully",response);
        verify(customerRepository,times(1)).deleteByAadhar("775426600349");
    }

    @Test
    void testDeleteByAadhar_NOTFOUND() {
        when(customerRepository.findByAadhar("775426600348")).thenReturn(Optional.empty());

        String response = customerService.deleteByAadhar("775426600348");

        assertEquals("Customer Not found in Record",response);
        verify(customerRepository,never()).deleteByAadhar("775426600348");
    }

    @Test
    void testDeleteAll() {
        String response = customerService.deleteAll();

        assertEquals("All Records Deleted Successfully",response);
        verify(customerRepository,times(1)).deleteAll();
    }

}
