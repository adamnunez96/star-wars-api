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
public class AuthControllerDecoratorTest {

    @Mock
    private AuthController authController;

    @InjectMocks
    private AuthControllerDecorator authControllerDecorator;

    @Test
    void testLogin() {
        Mockito.when(authController.login(Mockito.any())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        assertNotNull(authControllerDecorator.login(MockUtils.getLoginReq()));

        Mockito.verify(authController).login(Mockito.any());
    }

    @Test
    void testRegister() {
        Mockito.when(authController.register(Mockito.any())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        assertNotNull(authControllerDecorator.register(MockUtils.getRegisterReq()));

        Mockito.verify(authController).register(Mockito.any());
    }
    
}
