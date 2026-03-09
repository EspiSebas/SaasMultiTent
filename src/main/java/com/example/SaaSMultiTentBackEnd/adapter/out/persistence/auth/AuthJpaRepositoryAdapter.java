package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.auth;

import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company.CompanyEntity;
import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company.JpaCompanyRepository;
import com.example.SaaSMultiTentBackEnd.domain.model.user.User;
import com.example.SaaSMultiTentBackEnd.domain.port.out.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthJpaRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JpaCompanyRepository jpaCompanyRepository;

    public AuthJpaRepositoryAdapter(JpaUserRepository jpaUserRepository, JpaCompanyRepository jpaCompanyRepository) {
        this.jpaUserRepository = jpaUserRepository;
        this.jpaCompanyRepository = jpaCompanyRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(entity-> new User(
                        entity.getId(),
                        entity.getName(),
                        entity.getEmail(),
                        entity.getPassword(),
                        entity.getCompany().getId()
                ));
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        CompanyEntity companyEntity = jpaCompanyRepository.findById(user.getCompanyId())
                .orElseThrow(()-> new RuntimeException(
                        "Company not found"
                ));

        UserEntity userEntity = new UserEntity();
        userEntity.setId(null);
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(userEntity.getPassword());
        userEntity.setCompany(companyEntity);

        UserEntity saved = jpaUserRepository.save(userEntity);

        return new User(
                saved.getId(),
                saved.getName(),
                saved.getName(),
                saved.getPassword(),
                saved.getCompany().getId()
        );
    }
}
