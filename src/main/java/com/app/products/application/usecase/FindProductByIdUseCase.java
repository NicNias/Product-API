package com.app.products.application.usecase;

import com.app.products.application.exceptions.product.ProductNotFoundException;
import com.app.products.domain.model.Product;
import com.app.products.domain.ports.in.query.FindProductByIdPort;
import com.app.products.domain.ports.out.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindProductByIdUseCase implements FindProductByIdPort {

    private final ProductRepository productRepository;

    @Override
    public Product findById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}