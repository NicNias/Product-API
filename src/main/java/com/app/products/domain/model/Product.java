package com.app.products.domain.model;

import com.app.products.application.exceptions.BusinessException;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(
        UUID id,
        String name,
        String description,
        BigDecimal price
) {
    public Product {
        if (price != null && price.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("O preço do produto não pode ser negativo.");
        }
        if (name == null || name.isBlank()) {
            throw new BusinessException("O nome do produto é obrigatório e não pode estar vazio.");
        }
    }
}
