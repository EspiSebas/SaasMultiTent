package com.example.SaaSMultiTentBackEnd.domain.service.auth;

import com.example.SaaSMultiTentBackEnd.domain.model.company.Company;
import com.example.SaaSMultiTentBackEnd.domain.model.user.User;
import com.example.SaaSMultiTentBackEnd.domain.port.in.auth.AuthUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.out.company.CompanyRepository;
import com.example.SaaSMultiTentBackEnd.domain.port.out.user.TokenProvider;
import com.example.SaaSMultiTentBackEnd.domain.port.out.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthService implements AuthUseCase {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, CompanyRepository companyRepository, TokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override

    public String register(String nameCompany, String name, String email, String password) {

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        Company company = new Company(null, nameCompany);
        companyRepository.save(company);

        User user = new User(null,name,email,password,company.getId());

        userRepository.saveUser(user);


        return "User created";
    }

    @Override
    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return tokenProvider.generateToken(user.getEmail(), user.getCompanyId());
    }
}
