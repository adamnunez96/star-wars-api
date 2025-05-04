package com.anunez.conexa.star.wars.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.anunez.conexa.star.wars.utils.MockUtils;

@ExtendWith(MockitoExtension.class)
public class ApiControllerDecoratorTest {

    @Mock
    private ApiController apiController;

    @InjectMocks
    private ApiControllerDecorator apiControllerDecorator;

    @Test
    void testgetPerson() {
        Mockito.when(apiController.getPerson(Mockito.anyString(), Mockito.anyString())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        assertNotNull(apiControllerDecorator.getPerson(MockUtils.TEST, MockUtils.TEST));
    }

    @Test
    void getPeople() {
        Mockito.when(apiController.getPeople(Mockito.anyInt(), Mockito.anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        assertNotNull(apiControllerDecorator.getPeople(1, 10));
    }
    
}
