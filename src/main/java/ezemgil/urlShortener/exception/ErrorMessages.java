package ezemgil.urlShortener.exception;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    HASH_GENERATION_ERROR("Algorithm not found while generating hash"),
    URL_NOT_FOUND("URL not found"),
    USER_NOT_FOUND("User not found");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
