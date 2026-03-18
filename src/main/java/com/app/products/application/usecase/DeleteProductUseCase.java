package com.app.products.application.usecase;

import com.app.products.application.exceptions.product.ProductNotFoundException;
import com.app.products.domain.ports.in.store.DeleteProductPort;
import com.app.products.domain.ports.out.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase implements DeleteProductPort {

    private final ProductRepository productRepository;

    @Override
    public void deleteProduct(UUID id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(id);
    }
}