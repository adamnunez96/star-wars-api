package com.anunez.conexa.star.wars.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.anunez.conexa.star.wars.bean.auth.AuthRes;
import com.anunez.conexa.star.wars.bean.auth.LoginReq;
import com.anunez.conexa.star.wars.bean.auth.RegisterReq;
import com.anunez.conexa.star.wars.controller.AuthController;
import com.anunez.conexa.star.wars.service.AuthService;

@Component
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<AuthRes> login(LoginReq request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Override
    public ResponseEntity<AuthRes> register(RegisterReq request) {
        return ResponseEntity.ok(authService.register(request));
    }
    
}
