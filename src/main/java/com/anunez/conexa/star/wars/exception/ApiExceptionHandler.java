package com.anunez.conexa.star.wars.exception;

import com.anunez.conexa.star.wars.bean.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler extends RuntimeException {

    @ExceptionHandler(value = InternalServerException.class)
    public ResponseEntity<ErrorMessage> handleInternalServerException(InternalServerException e) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(500).body(errorMessage);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException e) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(404).body(errorMessage);
    }

    @ExceptionHandler(value = UnprocessableEntity.class)
    public ResponseEntity<ErrorMessage> handleUnprocessableEntity(UnprocessableEntity e) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(422).body(errorMessage);
    }

}
