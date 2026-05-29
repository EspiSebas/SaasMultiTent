package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDtoRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Name Company is required")
    private String nameCompany;
    @Email(message = "Email is not correct")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
}
