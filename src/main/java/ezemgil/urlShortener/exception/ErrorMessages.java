package ezemgil.urlShortener.exception;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    HASH_GENERATION_ERROR("Algorithm not found while generating hash"),
    URL_NOT_FOUND("URL not found"),
    USER_NOT_FOUND("User not found"),
    USER_ALREADY_EXISTS("User already exists"),
    URL_ALREADY_EXISTS("URL already exists"),
    USER_WITH_EMAIL_ALREADY_EXISTS("User with email already exists"),
    ENCRYPTION_ERROR("Error while encrypting data");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
