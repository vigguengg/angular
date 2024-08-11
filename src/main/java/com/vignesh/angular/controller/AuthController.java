package com.vignesh.angular.controller;

import com.vignesh.angular.dto.*;
import com.vignesh.angular.entities.User;
import com.vignesh.angular.services.AuthenticationService;
import com.vignesh.angular.services.JwtService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("sign-up")
    public ResponseEntity<SignupResponse> signup(@RequestBody RegisterUserDto loginForm){
        System.out.println("Login2  called" + loginForm.getEmail() + loginForm.getPassword());
        if(authenticationService.hasUserwithEmail()) {

            return new ResponseEntity("Email already exists. Please login", HttpStatus.NOT_ACCEPTABLE);
        }

        User authenticatedUser = authenticationService.signup(loginForm);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        SignupResponse response = new SignupResponse();
        response.setId(authenticatedUser.getId());
        response.setEmail(authenticatedUser.getEmail());
        response.setToken(jwtToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("reset-pass")
    public ResponseEntity<SignupResponse> resetpass(@RequestBody RegisterUserDto loginForm){
        System.out.println("Login2  called" + loginForm.getEmail() + loginForm.getPassword());
        User authenticatedUser = authenticationService.signup(loginForm);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        SignupResponse response = new SignupResponse();
        response.setId(authenticatedUser.getId());
        response.setEmail(authenticatedUser.getEmail());
        response.setToken(jwtToken);
        return ResponseEntity.ok(response);
    }


    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginForm){
        System.out.println("Login2  called" + loginForm.getEmail() + loginForm.getPassword());
        User authenticatedUser = authenticationService.authenticate(loginForm);
        System.out.println("äfter login");

        String jwtToken = jwtService.generateToken(authenticatedUser);
        System.out.println("äfter login");

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        System.out.println(authenticatedUser.getUsername());
        loginResponse.setName(authenticatedUser.getUsername());
        return ResponseEntity.ok(loginResponse);
    }
}
