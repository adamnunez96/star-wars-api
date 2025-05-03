package com.anunez.conexa.star.wars.bean;

import java.io.Serializable;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonProperties implements Serializable {
    private OffsetDateTime created;
    private OffsetDateTime edited;
    private String name;
    private String gender;
    @JsonProperty("skin_color")
    private String skinColor;
    @JsonProperty("hair_color")
    private String hairColor;
    private String height;
    @JsonProperty("eye_color")
    private String eyeColor;
    private String mass;
    //private String homeworld;
    @JsonProperty("birth_year")
    private String birthYear;
    private String url;
}
