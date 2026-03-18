package com.app.products.domain.ports.in.query;

import com.app.products.domain.model.Product;

import java.util.UUID;

public interface FindProductByIdPort {
    Product findById(UUID id);
}
