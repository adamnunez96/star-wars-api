package com.anunez.conexa.star.wars.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException{

    private String code;
    private String message;

    public NotFoundException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
