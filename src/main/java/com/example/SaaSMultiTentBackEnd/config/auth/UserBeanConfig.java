package com.example.SaaSMultiTentBackEnd.config.auth;

import com.example.SaaSMultiTentBackEnd.domain.port.in.auth.AuthUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.out.company.CompanyRepository;
import com.example.SaaSMultiTentBackEnd.domain.port.out.user.TokenProvider;
import com.example.SaaSMultiTentBackEnd.domain.port.out.user.UserRepository;
import com.example.SaaSMultiTentBackEnd.domain.service.auth.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserBeanConfig {
    @Bean
    public AuthUseCase authUseCase(UserRepository userRepository, CompanyRepository companyRepository, TokenProvider tokenProvider, PasswordEncoder passwordEncoder){
        return new AuthService(userRepository,companyRepository,tokenProvider,passwordEncoder);
    }
}
