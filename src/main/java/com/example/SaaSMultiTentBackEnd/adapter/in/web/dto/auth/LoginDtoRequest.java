package com.example.SaaSMultiTentBackEnd.adapter.in.web.dto.auth;

import lombok.Data;

@Data
public class LoginDtoRequest {
    private String email;
    private String password;
}
