package com.anunez.conexa.star.wars.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.anunez.conexa.star.wars.bean.ErrorMessage;

@ExtendWith(MockitoExtension.class)
public class ApiExceptionHandlerTest {

    @InjectMocks
    private ApiExceptionHandler apiExceptionHandler;

    @Test
    public void testHandleInternalServerException() {
        InternalServerException exception = new InternalServerException("01", "Internal Server Error");
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handleInternalServerException(exception);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("01", response.getBody().getCode());
        assertEquals("Internal Server Error", response.getBody().getMessage());
    }

    @Test
    public void testHandleNotFoundException() {
        NotFoundException exception = new NotFoundException("02", "Resource Not Found");
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handleNotFoundException(exception);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("02", response.getBody().getCode());
        assertEquals("Resource Not Found", response.getBody().getMessage());
    }

    @Test
    public void testHandleUnprocessableEntity() {
        UnprocessableEntity exception = new UnprocessableEntity("03", "Unprocessable Entity");
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handleUnprocessableEntity(exception);

        assertEquals(422, response.getStatusCodeValue());
        assertEquals("03", response.getBody().getCode());
        assertEquals("Unprocessable Entity", response.getBody().getMessage());
    }
}
