package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="companies")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
