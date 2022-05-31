package org.bolcom.app.game.domain.exceptions;

import org.bolcom.app.rules.domain.exceptions.RuleException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {RuleException.class})
    public ResponseEntity<Object> handleApiRequestException(RuleException exception){
        ApiException apiException = new ApiException(exception.getMessage());
        return new ResponseEntity<>(apiException,exception.getStatus());
    }
}
