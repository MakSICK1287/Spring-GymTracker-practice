package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.exception.UsernameNotFoundException;
import org.example.gymtrackerspring.repository.UserRepository;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username){
        return repository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
    }
}
