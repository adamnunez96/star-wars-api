package com.anunez.conexa.star.wars.utils;

import java.util.List;

import com.anunez.conexa.star.wars.bean.PeopleGetRes;
import com.anunez.conexa.star.wars.bean.PersonBasicInfo;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MockUtils {

    public static final String TEST = "test";
    public static final int TEST_INT = 1;

    public static PeopleGetRes getPeopleGetRes() {
        return PeopleGetRes.builder()
                .next("https://www.swapi.tech/api/people?page=3&limit=10")
                .previous("https://www.swapi.tech/api/people?page=1&limit=10")
                .results(List.of(new PersonBasicInfo()))
                .build();
    }
    
}
