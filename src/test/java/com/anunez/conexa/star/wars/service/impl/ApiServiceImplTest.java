package com.anunez.conexa.star.wars.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.anunez.conexa.star.wars.bean.PeopleGetRes;
import com.anunez.conexa.star.wars.bean.PersonGetRes;
import com.anunez.conexa.star.wars.bean.PersonGetResByName;
import com.anunez.conexa.star.wars.exception.UnprocessableEntity;
import com.anunez.conexa.star.wars.utils.MockUtils;

@ExtendWith(MockitoExtension.class)
public class ApiServiceImplTest {

    @Mock
    private HttpClientServiceImpl httpClient;

    @InjectMocks
    private ApiServiceImpl apiServiceImpl;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(apiServiceImpl, "serviceBaseUrl", "http://localhost:8080");
    }

    @Test
    void testgetPerson_ByIdCase() {
        Mockito.when(httpClient.getPerson(Mockito.anyString())).thenReturn(new PersonGetRes());

        assertNotNull(apiServiceImpl.getPerson(MockUtils.TEST, null));
    }

    @Test
    void testgetPerson_ByNameCase() {
        Mockito.when(httpClient.getPersonByName(Mockito.anyString())).thenReturn(new PersonGetResByName());

        assertNotNull(apiServiceImpl.getPerson(null, MockUtils.TEST));
    }

    @Test
    void testgetPeople() {
        Mockito.when(httpClient.getPeople(Mockito.anyInt(), Mockito.anyInt())).thenReturn(MockUtils.getPeopleGetRes());

        assertNotNull(apiServiceImpl.getPeople(1, 10));
    }

    @Test
    void testgetPeople_NullServiceResponseCase() {
        Mockito.when(httpClient.getPeople(Mockito.anyInt(), Mockito.anyInt())).thenReturn(null);

        assertThrows(UnprocessableEntity.class, () -> {
            apiServiceImpl.getPeople(1, 10);
        });
    }

    @Test
    void testgetPeople_NullNextPreviusProperties() {
        PeopleGetRes peopleGetRes = MockUtils.getPeopleGetRes();
        peopleGetRes.setNext(null);
        peopleGetRes.setPrevious(null);
        Mockito.when(httpClient.getPeople(Mockito.anyInt(), Mockito.anyInt())).thenReturn(peopleGetRes);

        assertNotNull(apiServiceImpl.getPeople(1, 10));
    }
    
}
