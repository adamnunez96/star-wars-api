package com.anunez.conexa.star.wars.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private UserDetails userDetails;

    private String token;

    @BeforeEach
    void setUp() {
        when(userDetails.getUsername()).thenReturn("testUser");
        token = jwtService.getToken(userDetails);
    }

    @Test
    void testGetToken() {
        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void testGetUsernameFromToken() {
        String username = jwtService.getUsernameFromToken(token);
        assertEquals("testUser", username);
    }

    @Test
    void testIsTokenValid() {
        boolean isValid = jwtService.isTokenValid(token, userDetails);
        assertTrue(isValid);
    }

    @Test
    void testIsTokenInvalidWhenUsernameDoesNotMatch() {
        when(userDetails.getUsername()).thenReturn("differentUser");
        boolean isValid = jwtService.isTokenValid(token, userDetails);
        assertFalse(isValid);
    }

}
