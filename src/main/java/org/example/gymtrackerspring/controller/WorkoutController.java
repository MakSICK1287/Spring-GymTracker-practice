package org.example.gymtrackerspring.controller;

import jakarta.validation.Valid;
import org.example.gymtrackerspring.dto.CreateWorkoutRequest;
import org.example.gymtrackerspring.entity.Workout;
import org.example.gymtrackerspring.service.WorkoutService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/date/{date}")
    public Workout getWorkoutByDate(@PathVariable LocalDate date) {
        return service.getWorkoutByDate(date);
    }

    @GetMapping("/{id}")
    public Workout getWorkout(@PathVariable Long id) {
        return service.getWorkout(id);
    }

    @PostMapping
    public Workout createWorkout(@Valid @RequestBody CreateWorkoutRequest request){
        return service.saveWorkout(request.getDate());
    }

}
