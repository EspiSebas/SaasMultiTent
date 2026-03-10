package com.example.SaaSMultiTentBackEnd.config.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    private SecurityUtils(){}
    public static Long getCompanyId() {
        return (Long) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getDetails();
    }

}
