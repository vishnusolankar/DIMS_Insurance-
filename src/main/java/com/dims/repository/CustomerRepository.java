package com.dims.repository;

import com.dims.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByAadhar(String aadhar);
    long deleteByAadhar(String aadhar);

}
