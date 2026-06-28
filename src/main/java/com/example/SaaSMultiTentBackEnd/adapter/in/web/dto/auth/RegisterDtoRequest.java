package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.auth;

import lombok.Data;

@Data
public class RegisterDtoRequest {
    private String name;
    private String nameCompany;
    private String email;
    private String password;
}
