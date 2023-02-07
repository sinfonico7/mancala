package org.bolcom.app.infrastracture.exception;

import org.bolcom.app.domain.exceptions.ApiException;
import org.bolcom.app.domain.exceptions.MatchException;
import org.bolcom.app.domain.exceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {UserException.class})
    public ResponseEntity<Object> handleApiRequestException(UserException exception){
        ApiException apiException = new ApiException(exception.getMessage());
        HttpStatus httpStatus = HttpStatus.resolve(exception.getStatus()) != null ?  HttpStatus.resolve(exception.getStatus()) : HttpStatus.CONFLICT;
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {MatchException.class})
    public ResponseEntity<Object> handleApiRequestException(MatchException exception){
        ApiException apiException = new ApiException(exception.getMessage());
        HttpStatus httpStatus = HttpStatus.resolve(exception.getStatus()) != null ?  HttpStatus.resolve(exception.getStatus()) : HttpStatus.CONFLICT;
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
