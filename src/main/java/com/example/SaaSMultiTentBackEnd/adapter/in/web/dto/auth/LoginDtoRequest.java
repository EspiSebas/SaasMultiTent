package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Data to log in")
public class LoginDtoRequest {
    @Schema(
            description = "User email",
            example = "sebastian@email.com"
    )
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;
    @Schema(
            description = "Account password",
            example = "1234Se*"
    )
    @NotBlank(message = "Password is required")
    private String password;
}
