package com.app.products.application.usecase;

import com.app.products.domain.command.CreateProductCommand;
import com.app.products.domain.model.Product;
import com.app.products.domain.ports.in.store.CreateProductPort;
import com.app.products.domain.ports.out.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateProductUseCase implements CreateProductPort {

    private final ProductRepository productRepository;

    @Override
    public void execute(CreateProductCommand command) {
        Product product = Product.builder()
                .name(command.name())
                .description(command.description())
                .price(command.price())
                .build();
        productRepository.save(product);
    }
}