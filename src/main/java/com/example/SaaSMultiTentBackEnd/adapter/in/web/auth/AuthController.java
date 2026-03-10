package com.example.SaaSMultiTentBackEnd.adapter.in.web.auth;

import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.auth.LoginDtoRequest;
import com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.auth.RegisterDtoRequest;
import com.example.SaaSMultiTentBackEnd.domain.port.in.auth.AuthUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthUseCase authUseCase;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthUseCase authUseCase, PasswordEncoder passwordEncoder) {
        this.authUseCase = authUseCase;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDtoRequest request){

        authUseCase.register(
                request.getNameCompany(),
                request.getName(),
                request.getEmail(),
                request.getPassword()

        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDtoRequest request){

        String token = authUseCase.login(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(token);
    }
}
