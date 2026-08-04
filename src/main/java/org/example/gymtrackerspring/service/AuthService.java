package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.entity.Role;
import org.example.gymtrackerspring.entity.User;
import org.example.gymtrackerspring.exception.EmailAlreadyExistsException;
import org.example.gymtrackerspring.exception.UsernameAlreadyExistsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.example.gymtrackerspring.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(JwtService jwtService, UserRepository repository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public String login(String username,String password){
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                username,
                                password
                        )
                );

        User user = (User) authentication.getPrincipal();

        return jwtService.generateToken(user);

    }

    public String register(String username,String password,String email){
        if(userRepository.existsByUsername(username)){
            throw new UsernameAlreadyExistsException(username);
        }
        if(userRepository.existsByEmail(email)){
            throw new EmailAlreadyExistsException(email);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole(Role.USER);
        user = userRepository.save(user);
        return jwtService.generateToken(user);
    }
}
