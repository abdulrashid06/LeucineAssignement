package com.leucine.collegedirectory.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leucine.collegedirectory.model.Users;
import com.leucine.collegedirectory.repository.UsersRepository;

import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsersRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/logIn")
    public ResponseEntity<?> logInUserHandler(@RequestBody LoginRequest loginRequest) {
        Optional<Users> opt = userRepository.findByUsername(loginRequest.getUsername());
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        Users user = opt.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        // Generate token
        String token = JwtToken.generateToken(user);

        // Prepare response with user details and token
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);

        return ResponseEntity.ok(response);
    }


//    @PostMapping("/signup")
//    public ResponseEntity<?> signUpUserHandler(@RequestBody Users user) throws Exception {
//        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
//        }
//        
//        // Encode password before saving
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        Users createdUser = userRepository.saveUser(user);
//        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//    }

    // Define LoginRequest class
    @Getter
    @Setter
    public static class LoginRequest {
        private String username;
        private String password;
        

    }
}