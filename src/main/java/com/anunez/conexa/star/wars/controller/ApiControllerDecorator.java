package com.anunez.conexa.star.wars.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anunez.conexa.star.wars.bean.PeopleGetRes;
import com.anunez.conexa.star.wars.bean.SwappiRes;


@RestController
@RequestMapping("${version}")
@Validated
public class ApiControllerDecorator implements ApiController {

    private final ApiController apiController;

    public ApiControllerDecorator(ApiController apiController) {
        this.apiController = apiController;
    }

    @GetMapping("/person")
    @Override
    public ResponseEntity<SwappiRes> getPerson(@RequestParam(required = true) String id, 
    @RequestParam(required = false) String name) {
        return apiController.getPerson(id, name);
    }

    @GetMapping("/people")
    @Override
    public ResponseEntity<PeopleGetRes> getPeople(@RequestParam(defaultValue = "0", required = false) int page, 
    @RequestParam(defaultValue = "10", required = false) int limit) {
        return apiController.getPeople(page, limit);
    }
    
}
