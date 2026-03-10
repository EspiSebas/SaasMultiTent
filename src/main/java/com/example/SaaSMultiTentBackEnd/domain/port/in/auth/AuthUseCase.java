package com.example.SaaSMultiTentBackEnd.domain.port.in.auth;

public interface AuthUseCase {
    String register(String nameCompany,String name, String email, String password);
    String login(String email, String password);
}
