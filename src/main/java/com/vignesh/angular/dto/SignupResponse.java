package com.vignesh.angular.dto;

import lombok.Data;

@Data
public class SignupResponse {

    private String email;


    private Integer Id;
    private String token;
    private String error;

}
