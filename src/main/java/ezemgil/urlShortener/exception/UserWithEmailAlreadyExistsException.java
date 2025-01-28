package ezemgil.urlShortener.exception;

public class UserWithEmailAlreadyExistsException extends RuntimeException {
    public UserWithEmailAlreadyExistsException() {
        super(ErrorMessages.USER_WITH_EMAIL_ALREADY_EXISTS.getMessage());
    }
}
