package com.app.products.application.mappers;

import com.app.products.application.dto.ProductDto;
import com.app.products.domain.command.CreateProductCommand;
import com.app.products.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    CreateProductCommand toCommand(ProductDto productDto);

    ProductDto toDto(Product product);

    List<ProductDto> toListDto(List<Product> products);

    @Mapping(target = "id", ignore = true)
    Product toDomain(CreateProductCommand command);
}
