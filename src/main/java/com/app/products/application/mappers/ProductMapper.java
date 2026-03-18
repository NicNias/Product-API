package com.app.products.application.mappers;

import com.app.products.application.dto.ProductDto;
import com.app.products.domain.command.CreateProductCommand;
import com.app.products.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "nome", target = "name")
    @Mapping(source = "descricao", target = "description")
    @Mapping(source = "preco", target = "price")
    CreateProductCommand toCommand(ProductDto productDto);

    @Mapping(source = "name", target = "nome")
    @Mapping(source = "description", target = "descricao")
    @Mapping(source = "price", target = "preco")
    ProductDto toDto(Product product);

    List<ProductDto> toListDto(List<Product> products);

    @Mapping(target = "id", ignore = true)
    Product toDomain(CreateProductCommand command);
}