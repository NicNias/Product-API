package com.app.products.infrastructure.adapter.in.web.exceptions;

import com.app.products.application.exceptions.BaseException;
import com.app.products.application.exceptions.BusinessException;
import com.app.products.application.exceptions.product.ProductNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ProblemDetail> handleBaseException(BaseException ex) {
        HttpStatus status = switch (ex) {
            case ProductNotFoundException p -> HttpStatus.NOT_FOUND;
            case BusinessException b -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        ProblemDetail pb = ProblemDetail.forStatusAndDetail(status, ex.getDetail());
        pb.setTitle(ex.getTitle());
        pb.setProperty("timestamp", Instant.now());

        return ResponseEntity.status(status).body(pb);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));

        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, errors);
        pb.setTitle("Erro de validação nos campos");

        return ResponseEntity.badRequest().body(pb);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handleDataIntegrity(DataIntegrityViolationException ex) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Conflito de dados no banco de dados.");
        pb.setTitle("Violação de integridade");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(pb);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericException(Exception ex) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado no servidor.");
        pb.setTitle("Erro crítico");

        return ResponseEntity.internalServerError().body(pb);
    }
}
