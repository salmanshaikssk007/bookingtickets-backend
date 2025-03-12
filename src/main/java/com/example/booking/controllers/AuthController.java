package com.example.booking.controllers;

import com.example.booking.DTO.AuthResponse;
import com.example.booking.DTO.LoginRequest;
import com.example.booking.DTO.RegisterRequest;
import com.example.booking.DTO.UserRole;
import com.example.booking.model.Users;
import com.example.booking.security.JwtUtil;
import com.example.booking.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthController {


    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private UserService userService;

    private  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Users user = userService.findUserByEmail(loginRequest.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Invalid email or password"));
        }

        System.out.println("Entered Password: " + loginRequest.getPassword());
        System.out.println("Stored Hashed Password: " + user.getPassword());

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            System.out.println("Passwords do not match!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("password"));
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            System.out.println("Authentication successful!");
            return ResponseEntity.ok(new AuthResponse("Login success"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Invalid email or password"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        // Check if email already exists
        if (userService.findUserByEmail(registerRequest.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Email is already in use"));
        }

        // Create new user
        Users newUser = new Users();
        newUser.setName(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());

        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());
        System.out.println("Plaintext Password: " + registerRequest.getPassword());
        System.out.println("Hashed Password (Stored in DB): " + hashedPassword);

        newUser.setPassword(hashedPassword); // Hash before saving
        newUser.setRole(UserRole.USER); // Default role

        userService.saveUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("User registered successfully"));
    }
}