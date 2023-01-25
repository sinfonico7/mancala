package org.bolcom.app.domain.exceptions;

public class UserException extends RuntimeException{
    private int status;

    public UserException(String message, int statusCode){
        super(message);
        status = statusCode;
    }

    public int getStatus() {
        return status;
    }
}
