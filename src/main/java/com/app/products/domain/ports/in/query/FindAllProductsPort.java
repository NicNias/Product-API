package com.app.products.domain.ports.in.query;

import com.app.products.domain.model.Product;

import java.util.List;

public interface FindAllProductsPort {
    List<Product> findAll();
}
