package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.stock;

import com.example.SaaSMultiTentBackEnd.adapter.out.persistence.company.CompanyEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="categories")
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

}
