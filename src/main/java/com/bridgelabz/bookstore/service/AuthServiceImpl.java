package com.bridgelabz.bookstore.service;


import com.bridgelabz.bookstore.config.PasswordConfig;
import com.bridgelabz.bookstore.dto.EmailDto;
import com.bridgelabz.bookstore.dto.SetPasswordDto;
import com.bridgelabz.bookstore.model.ERole;
import com.bridgelabz.bookstore.model.Role;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.payload.request.LoginRequest;
import com.bridgelabz.bookstore.payload.request.SignupRequest;
import com.bridgelabz.bookstore.payload.response.JwtResponse;
import com.bridgelabz.bookstore.payload.response.MessageResponse;
import com.bridgelabz.bookstore.payload.response.Response;
import com.bridgelabz.bookstore.repository.RoleRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.security.jwt.JwtUtils;
import com.bridgelabz.bookstore.utility.RabbitMq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    RabbitMq rabbitMq;
    @Autowired
    private EmailDto emailDto;
    @Autowired
    RabbitTemplate template;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    PasswordConfig passwordConfig;


    @Override
    public ResponseEntity registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        String strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            throw new RuntimeException("Error: Role is not found.");
        } else {
                switch (strRoles) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
        }
        user.setRoles(roles);
        userRepository.save(user);
        
        emailDto.setTo(user.getEmail());
        emailDto.setFrom("ragu.bodke@gmail.com");
        emailDto.setSubject("Welcome to Book Store");
        emailDto.setBody("Click this link to verify your account " +user.getId());
        rabbitMq.sendingMsgToQueue(emailDto);
     //   sendEmailNotification(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Override
    public ResponseEntity authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        System.out.println(jwt);
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public String verifyUserAccount(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.get().setVerified(true);
        userRepository.save(user.get());
        return "Congratulations!! Your account is verified.";
    }

    @Override
    public String forgotPassword(String email) {
    	User user = userRepository.findByEmail(email);   // find by user email id
    	if (user == null) {								// if user email id it null response to user not register it
    		throw new  RuntimeException("User not existing");
    	}else {
    		String token = jwtUtils.getJwtTokenFromUserId(user.getId());
    		// Sending mail to reset password
    		sendEmailToResetPassword(email, token);
    		return "User Email found" + token ;
    		}	
    	}
    private void sendEmailToResetPassword(String email, String token) {
        emailDto.setTo(email);
        emailDto.setFrom("ragu.bodke@gmail.com");
        emailDto.setSubject("Hello");
        emailDto.setBody("Please click this link to verify your account " + "http://localhost:4200/resetpassword " + token);
        rabbitMq.sendingMsgToQueue(emailDto);
    }

    @Override
    public String setPassword(SetPasswordDto setpassworddto, String token) {
    	long userId = jwtUtils.getUserIdFromToken(token);
    	Optional<User> user = userRepository.findById(userId); 
    	user.get().setPassword(encoder.encode(setpassworddto.getConfirmPassword()));
    	userRepository.save(user.get());
    	return "Password changed";
    }
    
}
