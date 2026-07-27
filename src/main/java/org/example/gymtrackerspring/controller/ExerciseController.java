package org.example.gymtrackerspring.controller;

import org.example.gymtrackerspring.dto.CreateExerciseRequest;
import org.example.gymtrackerspring.entity.Exercise;
import org.example.gymtrackerspring.service.ExerciseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExerciseController {

    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @PostMapping("/workouts/{workoutId}/exercises")
    public Exercise addExercise(
            @PathVariable Long workoutId,
            @RequestBody CreateExerciseRequest request) {

        return service.addExercise(workoutId, request.getName());
    }
}
