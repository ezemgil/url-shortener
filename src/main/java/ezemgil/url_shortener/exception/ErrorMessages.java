package ezemgil.url_shortener.exception;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    HASH_GENERATION_ERROR("Algorithm not found while generating hash");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
