package com.anunez.conexa.star.wars.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnprocessableEntity extends RuntimeException {

    private String code;
    private String message;

    public UnprocessableEntity(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    
}
