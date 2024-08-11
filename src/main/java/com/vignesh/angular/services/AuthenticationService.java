package com.vignesh.angular.services;

import com.vignesh.angular.dto.LoginUserDto;
import com.vignesh.angular.dto.RegisterUserDto;
import com.vignesh.angular.entities.User;
import com.vignesh.angular.repositories.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setFullName(input.getFullName());
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        try {
            System.out.println("inside authenticate");

            System.out.println(authenticationManager);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getEmail(),
                            input.getPassword()
                    ));
            System.out.println("inside authenticate");


            return userRepository.findByEmail(input.getEmail())
                    .orElseThrow();
        } catch (Exception e) {
            e.printStackTrace();
        }
return null;

    }

    public boolean hasUserwithEmail() {
        return true;
    }
}
