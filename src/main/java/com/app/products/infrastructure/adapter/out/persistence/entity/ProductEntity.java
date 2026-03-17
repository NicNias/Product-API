package com.app.products.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String description;

    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}
