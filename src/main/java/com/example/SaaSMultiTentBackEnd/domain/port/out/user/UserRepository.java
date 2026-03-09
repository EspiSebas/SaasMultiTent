package com.example.SaaSMultiTentBackEnd.domain.port.out.user;

import com.example.SaaSMultiTentBackEnd.domain.model.user.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    User saveUser(User user);
}
