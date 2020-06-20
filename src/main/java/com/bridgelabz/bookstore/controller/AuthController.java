package com.bridgelabz.bookstore.controller;

import javax.validation.Valid;

import com.bridgelabz.bookstore.dto.SetPasswordDto;
import com.bridgelabz.bookstore.payload.request.*;
import com.bridgelabz.bookstore.payload.response.*;
import com.bridgelabz.bookstore.repository.*;


import com.bridgelabz.bookstore.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
   private IAuthService iAuthService;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return iAuthService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return iAuthService.registerUser(signUpRequest);
    }

    @GetMapping("/verify/{userId}")
    public ResponseEntity<String> verifyAccount(@PathVariable long userId) {
        return new ResponseEntity(iAuthService.verifyUserAccount(userId), HttpStatus.OK);
    }
    @PostMapping("/forgot-password")
    public ResponseEntity findEmail(@RequestHeader String email){
    	return new ResponseEntity(iAuthService.findEmail(email), HttpStatus.OK);
    }

    @PostMapping("/set-new-password")
    public ResponseEntity setNewPassword(@RequestHeader String token, @RequestBody SetPasswordDto setpassworddto){
    	return new ResponseEntity(iAuthService.setPassword(setpassworddto, token), HttpStatus.OK);
    }

}

