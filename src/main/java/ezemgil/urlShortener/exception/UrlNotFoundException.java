package ezemgil.urlShortener.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException() {
        super(ErrorMessages.URL_NOT_FOUND.getMessage());
    }
}
