package ezemgil.urlShortener.exception;

public class EncryptionException extends RuntimeException {
    public EncryptionException() {
        super(ErrorMessages.ENCRYPTION_ERROR.getMessage());
    }
}
