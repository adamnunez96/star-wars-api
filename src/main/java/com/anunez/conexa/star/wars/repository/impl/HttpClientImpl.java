package com.anunez.conexa.star.wars.repository.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.anunez.conexa.star.wars.bean.PeopleGetRes;
import com.anunez.conexa.star.wars.bean.PersonGetRes;
import com.anunez.conexa.star.wars.bean.PersonGetResByName;
import com.anunez.conexa.star.wars.constant.ApiConstant;
import com.anunez.conexa.star.wars.enums.ErrorMessage;
import com.anunez.conexa.star.wars.exception.InternalServerException;
import com.anunez.conexa.star.wars.exception.NotFoundException;
import com.anunez.conexa.star.wars.repository.HttpClient;
import com.anunez.conexa.star.wars.utils.RequestUtils;

@Repository
public class HttpClientImpl implements HttpClient{

    private static final Logger LOG = LoggerFactory.getLogger(HttpClientImpl.class);

    private final RestTemplate restTemplate;

    public HttpClientImpl(@Qualifier("swappiRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String SWAPPI_URL = "https://swapi.tech/api";
    private static final String PEOPLE_ENDPOINT = "/people";
    private static final String STARSHIPS_ENDPOINT = "/starships";
    private static final String VEHICLES_ENDPOINT = "/vehicles";
    private static final String ID_URI_VARIABLE_TEMPLATE = "/{id}";
    private static final String SLASH = "/";

    @Override
    public PersonGetRes getPerson(String id) {

        LOG.info("Calling SWAPI with id: {}", id);

        HttpHeaders headers = buildHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        Map<String, ?> uriVariables = null;

        StringBuilder swappiUrl = new StringBuilder(SWAPPI_URL);

        swappiUrl.append(PEOPLE_ENDPOINT).append(ID_URI_VARIABLE_TEMPLATE);
        uriVariables = Map.of(ApiConstant.ID, id);
    
        String url = RequestUtils.getURIRequest(swappiUrl.toString(), null, uriVariables).toString();
        ResponseEntity<PersonGetRes> response = null;

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, PersonGetRes.class);
        } catch (RestClientException e) {
            LOG.error(ApiConstant.ERROR_CALLING_SERVICE_MESSAGE, url, e.getMessage());
            throw new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR.getCode(), ErrorMessage.INTERNAL_SERVER_ERROR.getMessage());
        }

        LOG.info("Response from SWAPI: {}", response.getBody());

        if (response.getStatusCode() != HttpStatus.OK) {
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                LOG.error(ApiConstant.RESOURCE_NOT_FOUND_MESSAGE, response.getStatusCode());
                throw new NotFoundException(ErrorMessage.NOT_FOUND.getCode(), ErrorMessage.NOT_FOUND.getMessage());
            }
            LOG.error(ApiConstant.ERROR_CALLING_SERVICE_MESSAGE, url, response.getStatusCode());
        }

        return response.getBody();
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(ApiConstant.CONTENT_ENCODING, StandardCharsets.UTF_8.toString());
        headers.set(ApiConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    @Override
    public PersonGetResByName getPersonByName(String name) {
        LOG.info("Calling SWAPI with name: {}", name);

        HttpHeaders headers = buildHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        Map<String, Object[]> queryParams = null;

        StringBuilder swappiUrl = new StringBuilder(SWAPPI_URL);

        swappiUrl.append(PEOPLE_ENDPOINT).append(SLASH);
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        queryParams = Map.of(ApiConstant.NAME, new Object[]{encodedName});
    
        String url = RequestUtils.getURIRequest(swappiUrl.toString(), queryParams, null).toString();
        ResponseEntity<PersonGetResByName> response = null;

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, PersonGetResByName.class);
        } catch (RestClientException e) {
            LOG.error(ApiConstant.ERROR_CALLING_SERVICE_MESSAGE, url, e.getMessage());
            throw new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR.getCode(), ErrorMessage.INTERNAL_SERVER_ERROR.getMessage());
        }

        LOG.info(ApiConstant.SWAPPI_RESPONSE_MESSAGE, response.getBody());

        if (response.getStatusCode() != HttpStatus.OK) {
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                LOG.error(ApiConstant.RESOURCE_NOT_FOUND_MESSAGE, response.getStatusCode());
                throw new NotFoundException(ErrorMessage.NOT_FOUND.getCode(), ErrorMessage.NOT_FOUND.getMessage());
            }
            LOG.error(ApiConstant.ERROR_CALLING_SERVICE_MESSAGE, url, response.getStatusCode());
        }

        return response.getBody();
    }

    @Override
    public PeopleGetRes getPeople(int page, int limit) {
        
        HttpHeaders headers = buildHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        Map<String, Object[]> queryParams = null;

        StringBuilder swappiUrl = new StringBuilder(SWAPPI_URL);
  
        swappiUrl.append(PEOPLE_ENDPOINT);
        queryParams = Map.of(ApiConstant.PAGE, new Object[]{page}, ApiConstant.LIMIT, new Object[]{limit});

        String url = RequestUtils.getURIRequest(swappiUrl.toString(), queryParams, null).toString();
        ResponseEntity<PeopleGetRes> response = null;

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, PeopleGetRes.class);
        } catch (RestClientException e) {
            LOG.error(ApiConstant.ERROR_CALLING_SERVICE_MESSAGE, url, e.getMessage());
            throw new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR.getCode(), ErrorMessage.INTERNAL_SERVER_ERROR.getMessage());
        }

        LOG.info(ApiConstant.SWAPPI_RESPONSE_MESSAGE, response.getBody());

        return response.getBody();
        
    }

}
