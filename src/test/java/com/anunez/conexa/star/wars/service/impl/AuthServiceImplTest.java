package com.anunez.conexa.star.wars.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.anunez.conexa.star.wars.bean.auth.AuthRes;
import com.anunez.conexa.star.wars.jwt.JwtService;
import com.anunez.conexa.star.wars.repository.user.User;
import com.anunez.conexa.star.wars.repository.user.UserRepository;
import com.anunez.conexa.star.wars.utils.MockUtils;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private User user;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void testLogin() {
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(jwtService.getToken(Mockito.any(User.class))).thenReturn("token");

        assertNotNull(authService.login(MockUtils.getLoginReq()));
    }

    @Test
    void testRegisterSuccess() {
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");
        Mockito.when(jwtService.getToken(Mockito.any(User.class))).thenReturn("token");

        AuthRes authRes = authService.register(MockUtils.getRegisterReq());

        assertNotNull(authRes);
        assertNotNull(authRes.getToken());
        verify(userRepository).save(any(User.class));
        verify(jwtService).getToken(any(User.class));
    }
    
}
