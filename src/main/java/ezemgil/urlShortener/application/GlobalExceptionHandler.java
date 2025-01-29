package ezemgil.urlShortener.application;

import ezemgil.urlShortener.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 400 Bad Request
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response<Object>> handleNotValidConstraints(ConstraintViolationException ex) {
        return Response.error(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 404 Not Found
    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<Response<Object>> handleUrlNotFoundException(UrlNotFoundException ex) {
        return Response.error(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response<Object>> handleUserNotFoundException(UserNotFoundException ex) {
        return Response.error(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 409 Conflict
    @ExceptionHandler(UrlAlreadyExistsException.class)
    public ResponseEntity<Response<Object>> handleUrlAlreadyExistsException(UrlAlreadyExistsException ex) {
        return Response.error(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserWithEmailAlreadyExistsException.class)
    public ResponseEntity<Response<Object>> handleUserWithEmailAlreadyExistsException(
            UserWithEmailAlreadyExistsException ex) {
        return Response.error(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // 500 Internal Server Error
    @ExceptionHandler(KeyGenerationException.class)
    public ResponseEntity<Response<Object>> handleKeyGenerationException(KeyGenerationException ex) {
        return Response.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Excepción genérica para aquellas que no fueron manejadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> handleException(Exception ex) {
        return Response.error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
