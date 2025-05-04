package com.anunez.conexa.star.wars.service;

import com.anunez.conexa.star.wars.bean.PeopleGetRes;
import com.anunez.conexa.star.wars.bean.PersonGetRes;
import com.anunez.conexa.star.wars.bean.PersonGetResByName;

public interface HttpClientService {

    PersonGetRes getPerson(String id);

    PersonGetResByName getPersonByName(String name);

    PeopleGetRes getPeople(int page, int limit);

}
