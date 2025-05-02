package com.anunez.conexa.star.wars.repository;

import com.anunez.conexa.star.wars.constant.ApiConstant;
import com.anunez.conexa.star.wars.enums.ErrorMessage;
import com.anunez.conexa.star.wars.exception.InternalServerException;
import com.anunez.conexa.star.wars.exception.NotFoundException;
import com.anunez.conexa.star.wars.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Repository
public class HttpClientImpl implements HttpClient{

    private static final Logger LOG = LoggerFactory.getLogger(HttpClientImpl.class);

    @Autowired
    @Qualifier("swapiRestTemplate")
    private RestTemplate restTemplate;

    private static final String SWAPPI_URL = "https://swapi.dev/api/";

    @Override
    public String call(String resource, String id, String name) {

        LOG.info("Calling SWAPI with resource: {}, id: {}, name: {}", resource, id, name);

        HttpHeaders headers = buildHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String url = RequestUtils.getUrl(resource, id, name);
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (RestClientException e) {
            LOG.error("Error calling resource: {}, ", e.getMessage());
            throw new InternalServerException(ErrorMessage.INTERNAL_SERVER_ERROR.getCode(), ErrorMessage.INTERNAL_SERVER_ERROR.getMessage());
        }

        if (response.getStatusCode() != HttpStatus.OK) {
            LOG.error("Error response from SWAPI: {}", response.getStatusCode());
            throw new NotFoundException(ErrorMessage.NOT_FOUND.getCode(), ErrorMessage.NOT_FOUND.getMessage());
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

}
