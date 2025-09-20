package com.dims.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Customer {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(name = "customer_name")
    private String name;
   @Column(name = "customer_email", unique = true)
    private String email;
   @Column(name = "customer_phone", unique = true)
    private String phoneNumer;
   @Column(name = "customer_aadhar", unique = true)
    private String aadharNumber;
   @Column(name = "customer_pan", unique = true)
    private String panNumber;
   @Column(name = "customer_dob")
    private LocalDate dob;
   @Column(name = "customer_address")
    private String address;
}
