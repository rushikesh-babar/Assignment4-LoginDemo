package com.assignment4.assignment4_loginDemo.controller;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment4.assignment4_loginDemo.model.User;
import com.assignment4.assignment4_loginDemo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
	private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.saveUser(user);  // Hash and save user
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest, HttpSession session) {
        String email = loginRequest.getOrDefault("email", "");
        String password = loginRequest.getOrDefault("password", "");

        if (email.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email and password are required"));
        }

        boolean isAuthenticated = userService.authenticate(email, password);
        if (isAuthenticated) {
            Optional<User> userOptional = userService.getUserByEmail(email);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                session.setAttribute("user", user);  // ✅ Store user in session
                session.setAttribute("userType", user.getType());  // ✅ Store user type in session
                
                String redirectUrl = user.getType().equalsIgnoreCase("admin") ? "/seeMessages.html" : "/images.html";

                System.out.println("User logged in: " + user.getEmail() + ", Type: " + user.getType()); // ✅ Debug user details

                return ResponseEntity.ok(Map.of(
                    "message", "Login successful!",
                    "redirect", redirectUrl,
                    "user", Map.of("email", user.getEmail(), "type", user.getType()) // ✅ Send user details
                ));
            }
        }
        return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
    }
    
    @CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true") 
    @GetMapping("/getSessionUser")
    public ResponseEntity<?> getSessionUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).body(Map.of("error", "No user logged in"));
        }
        return ResponseEntity.ok(Map.of("email", user.getEmail(), "type", user.getType()));
    }




}

   
    
	

