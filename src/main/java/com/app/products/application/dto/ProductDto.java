package com.app.products.application.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductDto(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        String descricao,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal preco
) {
}
