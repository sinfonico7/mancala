package org.bolcom.app.rules.domain.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class RuleException extends RuntimeException{

    private HttpStatus status;

    public RuleException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
