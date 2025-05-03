package com.anunez.conexa.star.wars.service;

import com.anunez.conexa.star.wars.bean.PeopleGetRes;
import com.anunez.conexa.star.wars.bean.SwappiRes;

public interface ApiService {

    SwappiRes getPerson(String id, String name);

    PeopleGetRes getPeople(int page, int limit);

}
