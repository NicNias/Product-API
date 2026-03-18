package com.app.products.application.usecase;

import com.app.products.domain.model.Product;
import com.app.products.domain.ports.in.query.FindAllProductsPort;
import com.app.products.domain.ports.out.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllProductsUseCase implements FindAllProductsPort {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}