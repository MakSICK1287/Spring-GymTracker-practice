package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.entity.Role;
import org.example.gymtrackerspring.entity.User;
import org.example.gymtrackerspring.exception.EmailAlreadyExistsException;
import org.example.gymtrackerspring.exception.UsernameAlreadyExistsException;
import org.example.gymtrackerspring.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String username,String password,String email){
        if(repository.existsByUsername(username)){
            throw new UsernameAlreadyExistsException(username);
        }
        if(repository.existsByEmail(email)){
            throw new EmailAlreadyExistsException(email);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole(Role.USER);
        return repository.save(user);
    }
}
