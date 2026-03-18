package com.app.products.infrastructure.adapter.out.persistence;

import com.app.products.domain.model.Product;
import com.app.products.domain.ports.out.repository.ProductRepository;
import com.app.products.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.app.products.infrastructure.adapter.out.persistence.mappers.ProductPersistenceMapper;
import com.app.products.infrastructure.adapter.out.persistence.repository.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepository {
    private final JpaProductRepository jpaRepository;
    private final ProductPersistenceMapper persistenceMapper;

    @Override
    public Product save(Product product) {
        ProductEntity entity = persistenceMapper.toEntity(product);

        return persistenceMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(persistenceMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> entities = jpaRepository.findAll();
        return persistenceMapper.toDomainList(entities);
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }
}
