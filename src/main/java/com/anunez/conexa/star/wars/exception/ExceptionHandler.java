package com.anunez.conexa.star.wars.exception;

import com.anunez.conexa.star.wars.bean.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler extends RuntimeException {

    public ResponseEntity<ErrorMessage> handleInternalServerException(InternalServerException e) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(500).body(errorMessage);
    }

    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException e) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(404).body(errorMessage);
    }

}
