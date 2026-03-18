package com.app.products.domain.model;

import com.app.products.application.exceptions.BusinessException;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Product {

    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;

    @Builder
    public Product(UUID id, String name, String description, BigDecimal price) {
        if (price != null && price.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("O preço do produto não pode ser negativo.");
        }
        if (name == null || name.isBlank()) {
            throw new BusinessException("O nome do produto é obrigatório.");
        }

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}