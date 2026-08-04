package org.example.gymtrackerspring.controller;

import jakarta.validation.Valid;
import org.example.gymtrackerspring.dto.CreateWorkoutRequest;
import org.example.gymtrackerspring.dto.UpdateWorkoutRequest;
import org.example.gymtrackerspring.dto.WorkoutResponse;
import org.example.gymtrackerspring.entity.Workout;
import org.example.gymtrackerspring.mapper.WorkoutMapper;
import org.example.gymtrackerspring.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutService service;
    private final WorkoutMapper mapper;

    public WorkoutController(WorkoutService service, WorkoutMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<WorkoutResponse> getAll(){
        return service.getAllWorkouts().stream().map(mapper::toResponse).toList();
    }


    @GetMapping("/{id}")
    public WorkoutResponse getWorkout(@PathVariable Long id) {
        Workout workout = service.getWorkout(id);
        return mapper.toResponse(workout);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutResponse> updateWorkout(@Valid @RequestBody UpdateWorkoutRequest request,@PathVariable Long id){
        Workout workout = service.updateWorkout(id,request.getDate());
        return ResponseEntity.ok(mapper.toResponse(workout));
    }

    @PostMapping
    public ResponseEntity<WorkoutResponse> createWorkout(@Valid @RequestBody CreateWorkoutRequest request){
        Workout workout = service.createWorkout(request.getDate());
        return ResponseEntity.created(URI.create("/workouts/" + workout.getId())).body(mapper.toResponse(workout));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id){
        service.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }

}
