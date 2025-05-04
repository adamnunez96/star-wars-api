package com.anunez.conexa.star.wars.bean.auth;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReq implements Serializable {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
}
