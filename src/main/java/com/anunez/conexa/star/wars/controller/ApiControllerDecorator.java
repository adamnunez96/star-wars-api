package com.anunez.conexa.star.wars.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anunez.conexa.star.wars.bean.PeopleGetRes;
import com.anunez.conexa.star.wars.bean.SwappiRes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("${version}/api")
@Validated
public class ApiControllerDecorator implements ApiController {

    private final ApiController apiController;

    public ApiControllerDecorator(ApiController apiController) {
        this.apiController = apiController;
    }

    @Operation(
        summary = "Get a character",
        description = "Returns a character based on the provided identifier. If a name is passed as a parameter, the search is performed by name and a list of characters that match the name is returned."
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/person")
    @Override
    public ResponseEntity<SwappiRes> getPerson(@RequestParam(required = true) String id, 
    @RequestParam(required = false) String name) {
        return apiController.getPerson(id, name);
    }

    @Operation(
        summary = "Get character list",
        description = "Returns a list of characters based on the pagination parameters. If no parameters are passed, the first page with 10 characters is returned."
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/people")
    @Override
    public ResponseEntity<PeopleGetRes> getPeople(@RequestParam(defaultValue = "1", required = false) int page, 
    @RequestParam(defaultValue = "10", required = false) int limit) {
        return apiController.getPeople(page, limit);
    }
    
}
