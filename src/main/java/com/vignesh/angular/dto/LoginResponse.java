package com.vignesh.angular.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginResponse {

    private String email;
    private String token;
    private String name;
}
