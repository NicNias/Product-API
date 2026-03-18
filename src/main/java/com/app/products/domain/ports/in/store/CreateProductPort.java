package com.app.products.domain.ports.in.store;

import com.app.products.domain.command.CreateProductCommand;

public interface CreateProductPort {
    void execute(CreateProductCommand createProductCommand);
}
