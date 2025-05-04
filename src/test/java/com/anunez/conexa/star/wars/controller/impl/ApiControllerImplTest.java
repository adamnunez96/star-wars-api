package com.anunez.conexa.star.wars.controller.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.anunez.conexa.star.wars.bean.PeopleGetRes;
import com.anunez.conexa.star.wars.bean.SwappiRes;
import com.anunez.conexa.star.wars.service.ApiService;
import com.anunez.conexa.star.wars.utils.MockUtils;

@ExtendWith(MockitoExtension.class)
public class ApiControllerImplTest {

    @Mock
    private ApiService apiService;

    @InjectMocks
    private ApiControllerImpl apiControllerImpl;

    @Test
    void testgetPerson(){
        Mockito.when(apiService.getPerson(Mockito.anyString(), Mockito.anyString())).thenReturn(new SwappiRes());

        assertNotNull(apiControllerImpl.getPerson(MockUtils.TEST, MockUtils.TEST));
    }

    @Test
    void testgetPeople(){
        Mockito.when(apiService.getPeople(Mockito.anyInt(), Mockito.anyInt())).thenReturn(new PeopleGetRes());

        assertNotNull(apiControllerImpl.getPeople(1, 10));
    }
    
}
