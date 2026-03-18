package com.app.products.infrastructure.adapter.out.persistence.mappers;

import com.app.products.domain.model.Product;
import com.app.products.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {

    @Mapping(target = "id", ignore = true)
    ProductEntity toEntity(Product product);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "id", source = "id")
    Product toDomain(ProductEntity productEntity);

    List<Product> toDomainList(List<ProductEntity> productEntities);
}