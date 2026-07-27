package org.example.gymtrackerspring.controller;

import org.example.gymtrackerspring.entity.Workout;
import org.example.gymtrackerspring.service.WorkoutService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutService service;

    public WorkoutController(WorkoutService service) {
        this.service = service;
    }

    @GetMapping
    public List<Workout> getAll(){
        return service.getAllWorkouts();
    }
}
