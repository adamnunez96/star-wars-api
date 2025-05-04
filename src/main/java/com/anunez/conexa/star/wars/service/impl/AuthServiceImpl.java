package com.anunez.conexa.star.wars.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anunez.conexa.star.wars.bean.auth.AuthRes;
import com.anunez.conexa.star.wars.bean.auth.LoginReq;
import com.anunez.conexa.star.wars.bean.auth.RegisterReq;
import com.anunez.conexa.star.wars.enums.Role;
import com.anunez.conexa.star.wars.jwt.JwtService;
import com.anunez.conexa.star.wars.repository.user.User;
import com.anunez.conexa.star.wars.repository.user.UserRepository;
import com.anunez.conexa.star.wars.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthRes login(LoginReq request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthRes.builder()
            .token(token)
            .build();
    }

    @Override
    public AuthRes register(RegisterReq request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .role(request.getRole())
            .build();

        userRepository.save(user);

        return AuthRes.builder()
            .token(jwtService.getToken(user))
            .build();
    }
    
}
