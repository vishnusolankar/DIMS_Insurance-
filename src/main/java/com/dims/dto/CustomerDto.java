package com.dims.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomerDto {
    private Long id;
    @NotNull
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format"
    )
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid phone number (must be 10 digits and start with 6–9)"
    )
    private String phoneNumer;

    @NotNull
    @Size(min =12, max = 12, message = "aadhar should be 12 characters")
    private String aadharNumber;

    @NotNull
    @Size(min=5, max = 10, message = "email should be between 5 to 10 characters")
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "invalid pan number")
    private String panNumber;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")  // ensures JSON accepts "1998-05-20"
    private LocalDate dob;

    private String address;
}
