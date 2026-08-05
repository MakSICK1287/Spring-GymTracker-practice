package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.entity.Role;
import org.example.gymtrackerspring.entity.User;
import org.example.gymtrackerspring.exception.EmailAlreadyExistsException;
import org.example.gymtrackerspring.exception.UsernameAlreadyExistsException;
import org.example.gymtrackerspring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    User createUser(){
        User user = new User();
        user.setId(1L);
        user.setUsername("max");
        return user;
    }

    @Mock
    private JwtService jwtService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService service;

    @Test
    void registerTest_usernameAlreadyExists(){
        User user = createUser();
        String password = "12345678";
        String username = "max";
        String email = "max@gmail.com";

        when(userRepository.existsByUsername(username))
                .thenReturn(true);

        assertThrows(UsernameAlreadyExistsException.class,
                ()->service.register(username,password,email));

        verify(passwordEncoder,never()).encode(password);
        verify(userRepository,never()).save(any(User.class));
        verify(jwtService, never())
                .generateToken(any());
    }

    @Test
    void registerTest_emailAlreadyExists(){
        User user = createUser();
        String password = "12345678";
        String username = "max";
        String email = "max@gmail.com";

        when(userRepository.existsByUsername(username))
                .thenReturn(false);
        when(userRepository.existsByEmail(email))
                .thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class,
                ()->service.register(username,password,email));

        verify(passwordEncoder,never()).encode(password);
        verify(userRepository,never()).save(any(User.class));
        verify(jwtService, never())
                .generateToken(any());
    }

    @Test
    void registerTest_shouldRegisterUser(){
        String password = "12345678";
        String username = "max";
        String email = "max@gmail.com";

        when(userRepository.existsByUsername(username))
                .thenReturn(false);
        when(userRepository.existsByEmail(email))
                .thenReturn(false);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(jwtService.generateToken(any(User.class))).thenReturn("jwt");


        String token = service.register(username,password,email);
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);


        User savedUser = captor.getValue();
        assertEquals(username, savedUser.getUsername());
        assertEquals("encodedPassword",savedUser.getPassword());
        assertEquals(email,savedUser.getEmail());
        assertEquals(Role.USER,savedUser.getRole());
        assertEquals("jwt",token);

        verify(jwtService).generateToken(savedUser);
        verify(userRepository).save(captor.capture());
    }
}
