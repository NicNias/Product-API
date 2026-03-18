package com.app.products.infrastructure.adapter.in.web.controllers;

import com.app.products.application.dto.ProductDto;
import com.app.products.application.mappers.ProductMapper;
import com.app.products.domain.ports.in.query.FindAllProductsPort;
import com.app.products.domain.ports.in.query.FindProductByIdPort;
import com.app.products.domain.ports.in.store.CreateProductPort;
import com.app.products.domain.ports.in.store.DeleteProductPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "Gerenciamento de produtos")
public class ProductController {

    private final FindAllProductsPort findAllProductsPort;
    private final FindProductByIdPort findProductByIdPort;
    private final CreateProductPort createProductPort;
    private final DeleteProductPort deleteProductPort;
    private final ProductMapper productMapper;

    @GetMapping
    @Operation(summary = "Lista todos os produtos")
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> products = productMapper.toListDto(findAllProductsPort.findAll());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um produto pelo ID")
    public ResponseEntity<ProductDto> findById(@PathVariable UUID id) {
        ProductDto product = productMapper.toDto(findProductByIdPort.findById(id));
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @Operation(summary = "Cria um novo produto")
    public ResponseEntity<Void> create(@RequestBody @Valid ProductDto productDto) {
        createProductPort.execute(productMapper.toCommand(productDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um produto pelo ID")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteProductPort.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
