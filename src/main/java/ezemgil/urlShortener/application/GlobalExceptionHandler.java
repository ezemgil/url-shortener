package ezemgil.urlShortener.application;

import ezemgil.urlShortener.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    static Logger logger;

    // 400 Bad Request
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response<Object>> handleNotValidConstraints(ConstraintViolationException ex) {
        logger.error("Validation error: {}", ex.getMessage());
        return Response.error(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 404 Not Found
    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<Response<Object>> handleUrlNotFoundException(UrlNotFoundException ex) {
        logger.error("Url not found: {}", ex.getMessage());
        return Response.error(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response<Object>> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error("User not found: {}", ex.getMessage());
        return Response.error(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 409 Conflict
    @ExceptionHandler(UrlAlreadyExistsException.class)
    public ResponseEntity<Response<Object>> handleUrlAlreadyExistsException(UrlAlreadyExistsException ex) {
        logger.error("Url already exists: {}", ex.getMessage());
        return Response.error(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserWithEmailAlreadyExistsException.class)
    public ResponseEntity<Response<Object>> handleUserWithEmailAlreadyExistsException(
            UserWithEmailAlreadyExistsException ex) {
        logger.error("User with email already exists: {}", ex.getMessage());
        return Response.error(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // 500 Internal Server Error
    @ExceptionHandler(KeyGenerationException.class)
    public ResponseEntity<Response<Object>> handleKeyGenerationException(KeyGenerationException ex) {
        logger.error("Error generating key: {}", ex.getMessage());
        return Response.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EncryptionException.class)
    public ResponseEntity<Response<Object>> handleEncryptionException(EncryptionException ex) {
        logger.error("Error encrypting: {}", ex.getMessage());
        return Response.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Excepción genérica para aquellas que no fueron manejadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> handleException(Exception ex) {
        logger.error("Internal server error: {}", ex.getMessage());
        return Response.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
