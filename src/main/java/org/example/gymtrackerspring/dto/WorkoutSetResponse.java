package org.example.gymtrackerspring.dto;

import lombok.Getter;

@Getter
public class WorkoutSetResponse {

    private final Long id;
    private final double weight;
    private final int reps;

    public WorkoutSetResponse(Long id, double weight, int reps) {
        this.id = id;
        this.weight = weight;
        this.reps = reps;
    }
}
