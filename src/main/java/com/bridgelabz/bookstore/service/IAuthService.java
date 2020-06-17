package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.payload.request.LoginRequest;
import com.bridgelabz.bookstore.payload.request.SignupRequest;
import com.bridgelabz.bookstore.payload.response.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity registerUser(SignupRequest signUpRequest);
    ResponseEntity authenticateUser(LoginRequest loginRequest);
    String verifyUserAccount(Long userId);
}

