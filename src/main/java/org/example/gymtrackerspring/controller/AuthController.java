package org.example.gymtrackerspring.controller;

import jakarta.validation.Valid;
import org.example.gymtrackerspring.dto.LoginRequest;
import org.example.gymtrackerspring.dto.RegisterRequest;
import org.example.gymtrackerspring.dto.UserResponse;
import org.example.gymtrackerspring.entity.User;
import org.example.gymtrackerspring.mapper.UserMapper;
import org.example.gymtrackerspring.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.Location;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    private final UserMapper mapper;

    public AuthController(AuthService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        User user = service.register(request.getUsername(),request.getPassword(),request.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @Valid @RequestBody LoginRequest request) {

        User user = service.login(
                request.getUsername(),
                request.getPassword());

        return ResponseEntity.ok(mapper.toResponse(user));
    }


}
