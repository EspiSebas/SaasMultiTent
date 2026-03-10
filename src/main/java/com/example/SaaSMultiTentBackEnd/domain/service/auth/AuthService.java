package com.example.SaaSMultiTentBackEnd.domain.service.auth;

import com.example.SaaSMultiTentBackEnd.domain.model.company.Company;
import com.example.SaaSMultiTentBackEnd.domain.model.user.User;
import com.example.SaaSMultiTentBackEnd.domain.port.in.auth.AuthUseCase;
import com.example.SaaSMultiTentBackEnd.domain.port.out.company.CompanyRepository;
import com.example.SaaSMultiTentBackEnd.domain.port.out.user.TokenProvider;
import com.example.SaaSMultiTentBackEnd.domain.port.out.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public String register(String nameCompany, String name, String email, String password) {

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        Company company = new Company(null, nameCompany);
        company = companyRepository.save(company);
        String passwordBy = passwordEncoder.encode(password);

        User user = new User(null, name, email,passwordBy, company.getId());
        userRepository.saveUser(user);

        return "User created";


    }

    @Override
    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));

        System.out.println(passwordEncoder.matches(password, user.getPassword()));
        System.out.println(new BCryptPasswordEncoder().encode("mario1234"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return tokenProvider.generateToken(user.getEmail(), user.getCompanyId());
    }
}
