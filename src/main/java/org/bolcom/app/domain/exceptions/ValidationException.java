package org.bolcom.app.domain.exceptions;

public class ValidationException extends RuntimeException{
    private int status;

    public ValidationException(String message, int statusCode){
        super(message);
        status = statusCode;
    }

    public int getStatus() {
        return status;
    }
}
