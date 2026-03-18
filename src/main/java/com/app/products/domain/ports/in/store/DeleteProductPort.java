package com.app.products.domain.ports.in.store;

import java.util.UUID;

public interface DeleteProductPort {
    void deleteProduct(UUID id);
}
