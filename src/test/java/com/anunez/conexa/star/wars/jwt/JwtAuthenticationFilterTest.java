package com.anunez.conexa.star.wars.jwt;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @Test
    void testDoFilterInternal_NoToken() throws ServletException, IOException {
        Mockito.when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternal_InvalidToken() throws ServletException, IOException {
        Mockito.when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer invalidToken");
        //when(jwtService.getUsernameFromToken("invalidToken")).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    // @Test
    // void testDoFilterInternal_ValidToken() throws ServletException, IOException {
    //     String token = "validToken";
    //     String username = "testUser";

    // // Configurar los mocks
    //     Mockito.when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer " + token);
    //     Mockito.when(jwtService.getUsernameFromToken(token)).thenReturn(username);
    //     Mockito.when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
    //     Mockito.when(jwtService.isTokenValid(token, userDetails)).thenReturn(true);

    //     // Ejecutar el filtro
    //     jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    //     // Verificar que el filtro pasó al siguiente en la cadena
    //     verify(filterChain, times(1)).doFilter(request, response);

    //     // Verificar que la autenticación se configuró correctamente
    //     assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    //     assertTrue(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken);
    //     assertTrue(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
    // }

    @Test
    void testDoFilterInternal_ValidTokenButAlreadyAuthenticated() throws ServletException, IOException {
        String token = "validToken";
        String username = "testUser";

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("existingUser", null, null));

        Mockito.when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer " + token);
        //when(jwtService.getUsernameFromToken(token)).thenReturn(username);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertNotEquals(username, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}