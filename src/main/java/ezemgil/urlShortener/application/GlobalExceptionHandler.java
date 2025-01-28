package ezemgil.urlShortener.application;

import ezemgil.urlShortener.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 400 Bad Request
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleNotValidConstraints(ConstraintViolationException ex) {
        return null;
    }

    // 404 Not Found
    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<Object> handleUrlNotFoundException(UrlNotFoundException ex) {
        return null;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        return null;
    }

    // 409 Conflict
    @ExceptionHandler(UrlAlreadyExistsException.class)
    public ResponseEntity<Object> handleUrlAlreadyExistsException(UrlAlreadyExistsException ex) {
        return null;
    }

    @ExceptionHandler(UserWithEmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserWithEmailAlreadyExistsException(UserWithEmailAlreadyExistsException ex) {
        return null;
    }

    // 500 Internal Server Error
    @ExceptionHandler(KeyGenerationException.class)
    public ResponseEntity<Object> handleKeyGenerationException(KeyGenerationException ex) {
        return null;
    }
}
