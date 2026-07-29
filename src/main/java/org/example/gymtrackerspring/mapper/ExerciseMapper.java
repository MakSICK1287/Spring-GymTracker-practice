package org.example.gymtrackerspring.mapper;

import org.example.gymtrackerspring.dto.ExerciseResponse;
import org.example.gymtrackerspring.entity.Exercise;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {
    public ExerciseResponse toResponse(Exercise exercise){
        return new ExerciseResponse(exercise.getId(),exercise.getName());
    }
}
