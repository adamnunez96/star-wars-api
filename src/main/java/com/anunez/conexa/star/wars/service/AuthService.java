package com.anunez.conexa.star.wars.service;

import com.anunez.conexa.star.wars.bean.auth.AuthRes;
import com.anunez.conexa.star.wars.bean.auth.LoginReq;
import com.anunez.conexa.star.wars.bean.auth.RegisterReq;

public interface AuthService {
    
    AuthRes login(LoginReq request);

    AuthRes register(RegisterReq request);

}
