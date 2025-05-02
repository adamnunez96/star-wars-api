package com.anunez.conexa.star.wars.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage implements Serializable {
    private String code;
    private String message;
}
