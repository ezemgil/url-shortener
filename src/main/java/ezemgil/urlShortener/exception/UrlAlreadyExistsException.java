package ezemgil.urlShortener.exception;

public class UrlAlreadyExistsException extends RuntimeException {
    public UrlAlreadyExistsException() {
        super(ErrorMessages.URL_ALREADY_EXISTS.getMessage());
    }
}
