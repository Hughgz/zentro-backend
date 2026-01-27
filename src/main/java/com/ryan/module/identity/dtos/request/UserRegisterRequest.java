package com.ryan.module.identity.dtos.request;

import com.ryan.module.identity.shared.enums.SexEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegisterRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private SexEnum sex;
    private LocalDate dob;
    @NotBlank(message = "Cannot blank email")
    private String email;
    @NotBlank(message = "Cannot blank phone")
    private String phone;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;
}
