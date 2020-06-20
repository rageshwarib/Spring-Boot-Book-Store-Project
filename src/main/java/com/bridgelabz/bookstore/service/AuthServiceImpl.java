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
    RabbitMq rabbitmq;
    @Autowired
    private EmailDto rabbitMqDto;
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

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() ->
            throw new RuntimeException("Error: Role is not found.");
//            );
//            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
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
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        
        rabbitMqDto.setTo(user.getEmail());
        rabbitMqDto.setFrom("ragu.bodke@gmail.com");
        rabbitMqDto.setSubject("Welcome to Book Store");
        rabbitMqDto.setBody("Click this link to verify your account " +user.getId());
        rabbitmq.sendingMsgToQueue(rabbitMqDto);
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

   // public void sendEmailNotification(User user) throws MailException {
        
   // }
    @Override
    public Response findEmail(String email) {

    	User user = userRepository.findByEmail(email);   // find by user email id
    	if (user == null) {								// if user email id it null response to user not register it
    		throw new  RuntimeException("User not existing");
    	}else {

    		String token = jwtUtils.createTokenFromUserId(user.getId());
    		EmailDto emailDto = RabbitMq.getRabbitMq(email, token);
    		template.convertAndSend("userMessageQueue", rabbitMqDto);
    		javaMailSender.send(rabbitmq.verifyUserMail(email, token, "Your Id is " + user.getId())); // send email
    		return new Response(400, "user  email found",token);
    		}	
    	}


    @Override
    public Response setPassword(SetPasswordDto setpassworddto, String token) {

    	System.out.println("1");
    	System.out.println(token);
    	long userId = jwtUtils.getUserIdFromToken(token);
    	System.out.println("2"+userId);
    	String email = userRepository.findById(userId).get().getEmail(); // find user email present or not
    	User updatedUser = userRepository.findByEmail(email);
    	System.out.println("4");
    	System.out.println(updatedUser);

    	if(setpassworddto.getPassword().equals(setpassworddto.getConfirmPassword())) {

    		System.out.println(setpassworddto);
    		updatedUser.setPassword(passwordConfig.encoder().encode(setpassworddto.getPassword()));
    		updateuserByEmail(updatedUser, email);
    		return new Response(200, "Password changed Successfully", true);
    	} else {
    		System.out.println("3");
    		return new Response(200,"Password is not matching", true);
    	}

    }


    public String updateuserByEmail(User user, String email) {
    	User updatedUser = userRepository.findByEmail(email);
    	updatedUser = user;
    	userRepository.save(updatedUser);
    	return "User updated sucessfully";
    }
    @Override
    public Response validateUser(String token) {

    	long userid = jwtUtils.getUserIdFromToken(token); // get user id from user token.
    	if (userid == 0) {
    		throw new RuntimeException("Invalid Token");
    	}
    	User user = userRepository.findById(userid).get(); // check userid present or not
    	if (user != null) { // if userid is found validate should be true
    		user.setVerified(true);
    		userRepository.save(user);
    		return new Response(200, "Email Verified", true);
    	} else {
    		return new Response(200, "Email not verified", false);

    	}

    }

}
