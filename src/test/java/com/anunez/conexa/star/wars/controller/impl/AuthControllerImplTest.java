package com.anunez.conexa.star.wars.controller.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.anunez.conexa.star.wars.bean.auth.AuthRes;
import com.anunez.conexa.star.wars.service.AuthService;
import com.anunez.conexa.star.wars.utils.MockUtils;

@ExtendWith(MockitoExtension.class)
public class AuthControllerImplTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthControllerImpl authControllerImpl;

    @Test
    void testLogin() {
        Mockito.when(authService.login(Mockito.any())).thenReturn(new AuthRes());

        assertNotNull(authControllerImpl.login(MockUtils.getLoginReq()));
    }

    @Test
    void testRegister() {
        Mockito.when(authService.register(Mockito.any())).thenReturn(new AuthRes());
        
        assertNotNull(authControllerImpl.register(MockUtils.getRegisterReq()));
    }
    
}
