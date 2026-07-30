package org.example.gymtrackerspring.mapper;

import org.example.gymtrackerspring.dto.UserResponse;
import org.example.gymtrackerspring.entity.Role;
import org.example.gymtrackerspring.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(User user){
        return new UserResponse(user.getId(),user.getUsername(),user.getPassword(),user.getEmail(), user.getRole());
    }
}
