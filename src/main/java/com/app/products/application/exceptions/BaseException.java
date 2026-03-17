package com.app.products.application.exceptions;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final String title;
    private final String detail;

    protected BaseException(String title, String detail) {
        super(detail);
        this.title = title;
        this.detail = detail;
    }
}
