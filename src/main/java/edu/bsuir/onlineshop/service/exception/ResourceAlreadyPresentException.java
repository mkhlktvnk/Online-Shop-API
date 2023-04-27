package edu.bsuir.onlineshop.service.exception;

public class ResourceAlreadyPresentException extends RuntimeException {
    public ResourceAlreadyPresentException(String message) {
        super(message);
    }

    public ResourceAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }
}
