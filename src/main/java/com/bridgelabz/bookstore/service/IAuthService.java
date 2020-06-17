package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.payload.request.LoginRequest;
import com.bridgelabz.bookstore.payload.request.SignupRequest;
import com.bridgelabz.bookstore.payload.response.JwtResponse;

public interface IAuthService {
    String registerUser( SignupRequest signUpRequest);
    JwtResponse authenticateUser(LoginRequest loginRequest);
    String verifyUserAccount(Long userId);
}

