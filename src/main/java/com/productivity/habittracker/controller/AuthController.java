package com.productivity.habittracker.controller;

import com.productivity.habittracker.dto.AuthRequest;
import com.productivity.habittracker.dto.AuthResponse;
import com.productivity.habittracker.service.JwtService;
import com.productivity.habittracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest req) {
        var user = userService.register(req.getName(), req.getEmail(), req.getPassword());
        // TODO: send verification email with token (simulate or implement)
        return ResponseEntity.ok("Registered. Check email to verify.");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        var token = jwtService.generateToken(req.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String email) {
        // In production you'd use a signed token; for MVP we use email param for simplicity
        userService.enableUser(email);
        return ResponseEntity.ok("Account verified");
    }
}
