package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request payload for registering a new user and company")
public class RegisterDtoRequest {

    @Schema(
            description = "Full name of the user",
            example = "Sebastian Espinosa",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(
            description = "Company name",
            example = "AutoService Medellin",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Name Company is required")
    private String nameCompany;

    @Schema(
            description = "User email address",
            example = "sebastian@example.com",
            format = "email",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Email(message = "Email is not correct")
    @NotBlank(message = "Email is required")
    private String email;

    @Schema(
            description = "Account password",
            example = "P@ssw0rd123",
            minLength = 8,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Password is required")
    private String password;
}

