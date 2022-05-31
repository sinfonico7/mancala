package org.bolcom.app.game.domain.exceptions;

import lombok.Data;

@Data
public class ApiException {
    private final String message;

    ApiException(String message){
        this.message = message;
    }
}
