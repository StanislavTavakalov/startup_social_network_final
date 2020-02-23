package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.payload.SignUpRequest;
import org.springframework.http.ResponseEntity;


public interface AuthService {
    ResponseEntity<?> authenticateUser(String login, String password);
    ResponseEntity<?> registerUser(SignUpRequest signUpRequest);
}
