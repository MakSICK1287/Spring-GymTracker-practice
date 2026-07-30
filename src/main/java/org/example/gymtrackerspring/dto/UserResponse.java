package org.example.gymtrackerspring.dto;

import lombok.Getter;
import org.example.gymtrackerspring.entity.Role;

@Getter
public class UserResponse {

    private final Long id;
    private final String username;
    private final String password;
    private final String email;
    private final Role role;

    public UserResponse(Long id,String username,String password,String email,Role role){

        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
