package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.stock;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_id" , nullable = false)
    private CategoryEntity category;


}
