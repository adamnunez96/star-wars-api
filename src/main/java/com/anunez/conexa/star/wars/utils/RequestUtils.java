package com.anunez.conexa.star.wars.utils;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RequestUtils {

    public static URI getURIRequest(final String httpUrl, final Map<String, Object[]> queryParams,
                                    final Map<String, ?> uriVariables) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(httpUrl);
            if (Objects.nonNull(queryParams)) {
                queryParams.forEach(builder::queryParam);
            }
            return URI.create(builder.buildAndExpand(Objects.nonNull(uriVariables) ? uriVariables : new HashMap<>())
                                    .toUriString());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid URL: " + httpUrl, e);
        }
    }
    
}
