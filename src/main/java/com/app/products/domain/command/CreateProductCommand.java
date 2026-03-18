package com.app.products.domain.command;

import java.math.BigDecimal;

public record CreateProductCommand(
        String name,
        String description,
        BigDecimal price
) {
}
