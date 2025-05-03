package com.anunez.conexa.star.wars.repository.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.anunez.conexa.star.wars.bean.PeopleGetRes;
import com.anunez.conexa.star.wars.bean.PersonGetRes;
import com.anunez.conexa.star.wars.bean.PersonGetResByName;
import com.anunez.conexa.star.wars.exception.InternalServerException;
import com.anunez.conexa.star.wars.exception.NotFoundException;
import com.anunez.conexa.star.wars.utils.MockUtils;

@ExtendWith(MockitoExtension.class)
public class HttpClientImplTest {

    @Mock
    private RestTemplate restTemplate;
    
    @InjectMocks
    private HttpClientImpl httpClientImpl;

    @Test
    void testGetPerson_Success() {
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(PersonGetRes.class))).thenReturn(ResponseEntity.ok(new PersonGetRes()));

        assertNotNull(httpClientImpl.getPerson(MockUtils.TEST));
    }

    @Test
    void testGetPerson_InternalServerError() {
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(PersonGetRes.class))).thenThrow(new RestClientException("Internal Server Error"));

        assertThrows(InternalServerException.class, () -> {
            httpClientImpl.getPerson(MockUtils.TEST);
        });
    }

    @Test
    void testGetPerson_NotFound() {
        ResponseEntity<PersonGetRes> responseEntity = ResponseEntity.status(404).body(new PersonGetRes());
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(PersonGetRes.class))).thenReturn(responseEntity);

        assertThrows(NotFoundException.class, () -> {
            httpClientImpl.getPerson(MockUtils.TEST);
        });
    }

    @Test
    void testgetPersonByName_Success() {
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(PersonGetResByName.class))).thenReturn(ResponseEntity.ok(new PersonGetResByName()));

        assertNotNull(httpClientImpl.getPersonByName(MockUtils.TEST));
    }

    @Test
    void testgetPersonByName_InternalServerError() {
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(PersonGetResByName.class))).thenThrow(new RestClientException("Internal Server Error"));

        assertThrows(InternalServerException.class, () -> {
            httpClientImpl.getPersonByName(MockUtils.TEST);
        });
    }

    @Test
    void testgetPersonByName_NotFound() {
        ResponseEntity<PersonGetResByName> responseEntity = ResponseEntity.status(404).body(new PersonGetResByName());
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(PersonGetResByName.class))).thenReturn(responseEntity);

        assertThrows(NotFoundException.class, () -> {
            httpClientImpl.getPersonByName(MockUtils.TEST);
        });
    }

    @Test
    void testgetPeople_Success(){
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(PeopleGetRes.class))).thenReturn(ResponseEntity.ok(new PeopleGetRes()));

        assertNotNull(httpClientImpl.getPeople(MockUtils.TEST_INT, MockUtils.TEST_INT));
    }

    @Test
    void testgetPeople_InternalServerError() {
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(PeopleGetRes.class))).thenThrow(new RestClientException("Internal Server Error"));

        assertThrows(InternalServerException.class, () -> {
            httpClientImpl.getPeople(MockUtils.TEST_INT, MockUtils.TEST_INT);
        });
    }
    
    
}
