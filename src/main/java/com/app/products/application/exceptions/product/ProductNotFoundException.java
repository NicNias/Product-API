package com.app.products.application.exceptions.product;

import com.app.products.application.exceptions.BaseException;

import java.util.UUID;

public class ProductNotFoundException extends BaseException {
    public ProductNotFoundException(UUID id) {
        super(
                "Produto não encontrado",
                "Não foi possível encontrar um produto com o ID: " + id
        );
    }

    public ProductNotFoundException(String message) {
        super("Produto não encontrado", message);
    }
}
