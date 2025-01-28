package ezemgil.url_shortener.exception;

public class KeyGenerationException extends RuntimeException {
    public KeyGenerationException() {
        super(ErrorMessages.HASH_GENERATION_ERROR.getMessage());
    }
}
