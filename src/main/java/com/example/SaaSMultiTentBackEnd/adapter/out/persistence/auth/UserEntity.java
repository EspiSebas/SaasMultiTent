package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.auth;

import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company.CompanyEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;


}
