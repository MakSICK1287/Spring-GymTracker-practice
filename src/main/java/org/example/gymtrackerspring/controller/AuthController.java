package org.example.gymtrackerspring.controller;

import jakarta.validation.Valid;
import org.example.gymtrackerspring.dto.LoginRequest;
import org.example.gymtrackerspring.dto.AuthResponse;
import org.example.gymtrackerspring.dto.RegisterRequest;
import org.example.gymtrackerspring.entity.User;
import org.example.gymtrackerspring.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService service) {
        this.authService = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        String token = authService.register(request.getUsername(),request.getPassword(),request.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        String token = authService.login(
                request.getUsername(),
                request.getPassword());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/me")
    public ResponseEntity<String> hello(@AuthenticationPrincipal User user){
        return ResponseEntity.ok("Привет" + user.getUsername());
    }


}
