package com.anunez.conexa.star.wars.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.anunez.conexa.star.wars.bean.PeopleGetRes;
import com.anunez.conexa.star.wars.bean.SwappiRes;
import com.anunez.conexa.star.wars.controller.ApiController;
import com.anunez.conexa.star.wars.service.ApiService;

@Component
public class ApiControllerImpl implements ApiController {

    private final ApiService apiService;

    public ApiControllerImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public ResponseEntity<SwappiRes> getPerson(String id, String name) {
        return ResponseEntity.ok(apiService.getPerson(id, name));
    }
    
    @Override
    public ResponseEntity<PeopleGetRes> getPeople(int page, int limit) {
        return ResponseEntity.ok(apiService.getPeople(page, limit));
    }

    // @Override
    // public String getFilms(String id, String name, String page, String search) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    // @Override
    // public String getStarships(String id, String name, String page, String search) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    // @Override
    // public String getVehicles(String id, String name, String page, String search) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

}
