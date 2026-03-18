package com.app.products.infrastructure.adapter.out.persistence.mappers;

import com.app.products.domain.model.Product;
import com.app.products.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {

    ProductEntity toEntity(Product product);

    Product toDomain(ProductEntity productEntity);

    List<Product> toDomainList(List<ProductEntity> productEntities);
}
