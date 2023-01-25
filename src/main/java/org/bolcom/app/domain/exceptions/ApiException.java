package org.bolcom.app.domain.exceptions;

public class ApiException {
    private final String message;

    public ApiException(String message) {
        this.message = message;
    }
}
