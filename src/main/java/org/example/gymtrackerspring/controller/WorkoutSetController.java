package org.example.gymtrackerspring.controller;


import org.example.gymtrackerspring.dto.CreateWorkoutSetRequest;
import org.example.gymtrackerspring.entity.WorkoutSet;
import org.example.gymtrackerspring.service.WorkoutSetService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/exercises")
public class WorkoutSetController {

    private final WorkoutSetService service;

    public WorkoutSetController(WorkoutSetService service) {
        this.service = service;
    }

    @PostMapping("/{exerciseId}/sets")
    public WorkoutSet addSet(
            @PathVariable Long exerciseId,
            @RequestBody CreateWorkoutSetRequest request) {

        return service.addSet(exerciseId,request.getWeight(),request.getReps());
    }
}
