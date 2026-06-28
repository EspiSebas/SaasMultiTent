package com.example.SaaSMultiTentBackEnd.adapter.in.web.controllers.auth;

import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.auth.AuthResponse;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.auth.LoginDtoRequest;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.auth.RegisterDtoRequest;
import com.example.SaaSMultiTentBackEnd.domain.port.in.auth.AuthUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {
    private final AuthUseCase authUseCase;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthUseCase authUseCase, PasswordEncoder passwordEncoder) {
        this.authUseCase = authUseCase;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @Operation(summary = "Register users")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Error")
    })
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterDtoRequest request){

        authUseCase.register(
                request.getNameCompany(),
                request.getName(),
                request.getEmail(),
                request.getPassword()

        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    @Operation(summary = "Login users")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User logged in"),
            @ApiResponse(responseCode = "400", description = "Invalid")
    })
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDtoRequest request){

        String token = authUseCase.login(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
