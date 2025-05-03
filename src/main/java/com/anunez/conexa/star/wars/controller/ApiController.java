package com.anunez.conexa.star.wars.controller;

import org.springframework.http.ResponseEntity;

import com.anunez.conexa.star.wars.bean.PeopleGetRes;
import com.anunez.conexa.star.wars.bean.SwappiRes;

public interface ApiController {

    ResponseEntity<SwappiRes> getPerson(String id, String name);

    ResponseEntity<PeopleGetRes> getPeople(int page, int limit);

    // String getFilms(String id, String name, String page, String search);

    // String getStarships(String id, String name, String page, String search);

    // String getVehicles(String id, String name, String page, String search);
    
}
