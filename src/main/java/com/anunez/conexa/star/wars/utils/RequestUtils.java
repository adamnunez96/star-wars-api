package com.anunez.conexa.star.wars.utils;

public class RequestUtils {

    public static String getUrl(String resource, String id, String name) {
        StringBuilder url = new StringBuilder("https://swapi.dev/api/");
        url.append(resource);
        if (id != null && !id.isEmpty()) {
            url.append("/").append(id);
        } else if (name != null && !name.isEmpty()) {
            url.append("/?search=").append(name);
        }
        return url.toString();
    }

}
