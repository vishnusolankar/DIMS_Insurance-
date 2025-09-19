package com.dims.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto {
    private Long id;
    @NotNull
    private String name;

    @NotNull
    private String email;

    private String phoneNumer;

    @NotNull
    @Size(min =12, max = 12, message = "aadhar should be 12 characters")
    private String aadharNumber;

    @NotNull
    @Size(min=5, max = 10, message = "email should be between 5 to 10 characters")
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "invalid pan number")
    private String panNumber;

    @NotNull
    private String dob;

    private String address;
}
