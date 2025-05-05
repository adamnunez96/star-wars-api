package com.anunez.conexa.star.wars.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anunez.conexa.star.wars.bean.auth.AuthRes;
import com.anunez.conexa.star.wars.bean.auth.LoginReq;
import com.anunez.conexa.star.wars.bean.auth.RegisterReq;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("${version}/auth")
@Validated
public class AuthControllerDecorator implements AuthController {

    private final AuthController authController;

    public AuthControllerDecorator(AuthController authController) {
        this.authController = authController;
    }

    @Operation(
        summary = "Login User",
        description = "Authenticate a user and return a JWT token."
    )
    @PostMapping(value = "login")
    @Override
    public ResponseEntity<AuthRes> login(@RequestBody LoginReq request) {
        return authController.login(request);
    }

    @Operation(
        summary = "Register User",
        description = "Register a new user and return a JWT token. Avaible Roles: USER, ADMIN."
    )
    @PostMapping(value = "register")
    @Override
    public ResponseEntity<AuthRes> register(@RequestBody RegisterReq request) {
        return authController.register(request);
    }

    
}
