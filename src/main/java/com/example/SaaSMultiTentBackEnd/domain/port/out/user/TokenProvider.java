package com.example.SaaSMultiTentBackEnd.domain.port.out.user;

public interface TokenProvider {
    String generateToken(String email,Long companyId);
    boolean validateToken(String token);
    String getEmail(String token);
    Long getCompanyId(String token);
}
