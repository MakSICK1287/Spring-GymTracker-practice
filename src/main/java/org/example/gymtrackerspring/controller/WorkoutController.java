package org.example.gymtrackerspring.controller;

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

    @PostMapping
    public Workout createWorkout(@RequestBody Workout workout){
        return service.saveWorkout(workout);
    }

}
