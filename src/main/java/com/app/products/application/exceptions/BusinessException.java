package com.app.products.application.exceptions;

public class BusinessException extends BaseException {
    public BusinessException(String detail) {
        super("Violação de Regra de Negócio", detail);
    }
}
