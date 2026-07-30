package org.example.gymtrackerspring.mapper;


import org.example.gymtrackerspring.dto.WorkoutSetResponse;
import org.example.gymtrackerspring.entity.WorkoutSet;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSetMapper {
    public WorkoutSetResponse toResponse(WorkoutSet set){
        return new WorkoutSetResponse(set.getId(),set.getWeight(),set.getReps());
    }
}
