package com.anunez.conexa.star.wars.controller;

import org.springframework.http.ResponseEntity;

import com.anunez.conexa.star.wars.bean.auth.AuthRes;
import com.anunez.conexa.star.wars.bean.auth.LoginReq;
import com.anunez.conexa.star.wars.bean.auth.RegisterReq;

public interface AuthController {

    ResponseEntity<AuthRes> login(LoginReq request);

    ResponseEntity<AuthRes> register(RegisterReq request);
    
}
