package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company;

import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.auth.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name="companies")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<UserEntity> users;

}
