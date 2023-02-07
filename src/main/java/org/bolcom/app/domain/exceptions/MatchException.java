package org.bolcom.app.domain.exceptions;

public class MatchException extends RuntimeException{
    private int status;

    public MatchException(String message, int statusCode){
        super(message);
        status = statusCode;
    }

    public int getStatus() {
        return status;
    }
}
