package org.example.gymtrackerspring.mapper;

import org.example.gymtrackerspring.dto.WorkoutResponse;
import org.example.gymtrackerspring.entity.Workout;
import org.springframework.stereotype.Component;

@Component
public class WorkoutMapper {
    public WorkoutResponse toResponse(Workout workout){
        return new WorkoutResponse(workout.getId(),workout.getDate());
    }
}
