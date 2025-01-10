package com.salesapp.sales_app.controller;

import com.salesapp.sales_app.entity.User;
import com.salesapp.sales_app.security.JwtUtil;
import com.salesapp.sales_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Register Kasir
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody User newUser) {
        userService.register(newUser);
        return ResponseEntity.ok("User registered successfully, please wait for activation by admin.");
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtUtil.generateToken(loginRequest.getUsername());

        return ResponseEntity.ok("Bearer " + jwtToken); // JWT token as response
    }
}
