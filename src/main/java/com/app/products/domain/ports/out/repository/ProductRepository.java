package com.app.products.domain.ports.out.repository;

import com.app.products.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(UUID id);
    List<Product> findAll();
    void delete(UUID id);
}
