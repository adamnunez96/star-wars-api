package com.anunez.conexa.star.wars.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anunez.conexa.star.wars.bean.PeopleGetRes;
import com.anunez.conexa.star.wars.bean.SwappiRes;
import com.anunez.conexa.star.wars.repository.impl.HttpClientImpl;
import com.anunez.conexa.star.wars.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService {

    private static final Logger LOG = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Value("${service.star-wars.url}")
    private String baseUrl;

    private final HttpClientImpl httpClient;

    public ApiServiceImpl(HttpClientImpl httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public SwappiRes getPerson(String id, String name) {
        LOG.info("Fetching person with id: {}, name: {}", id, name);

        if (name != null && !name.isEmpty()) {
            return httpClient.getPersonByName(name);
        } else  {
            return httpClient.getPerson(id);
        } 
        
    }

    @Override
    public PeopleGetRes getPeople(int page, int limit) {
        LOG.info("Fetching people with page: {}, limit: {}", page, limit);

        PeopleGetRes peopleGetRes = httpClient.getPeople(page, limit);

        String nextPage = peopleGetRes.getNext() != null ? baseUrl.concat("/people") + "?page=" + (page + 1) + "&limit=" + limit : null;
        String previousPage = peopleGetRes.getPrevious() != null ? baseUrl.concat("/people") + "?page=" + (page - 1) + "&limit=" + limit : null;

        peopleGetRes.setPrevious(previousPage);
        peopleGetRes.setNext(nextPage);

        return peopleGetRes;
    }
    
}
