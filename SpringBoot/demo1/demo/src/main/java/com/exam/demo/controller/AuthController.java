package com.exam.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.demo.model.User;
import com.exam.demo.service.AuthService;

@CrossOrigin(origins = "http://localhost:4300")// Angular dev server
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> loginData){
        String userId = loginData.get("userId");
        String password = loginData.get("password");

        User user = authService.validateUser(userId, password);

        if(user != null){
            return ResponseEntity.ok(Map.of("message","Login successful","userId", user.getUserId()));
        }else{
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (authService.userExists(user.getUserId())) {
            return ResponseEntity.status(400).body(Map.of("error", "User ID already exists"));
        }

        User savedUser = authService.registerUser(user);
        return ResponseEntity.ok(Map.of("message", "User registered successfully", "userId", savedUser.getUserId()));
    }

}
