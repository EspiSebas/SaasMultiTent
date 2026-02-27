package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.stock;

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

}
