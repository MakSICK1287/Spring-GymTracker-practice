package org.example.gymtrackerspring.controller;

import jakarta.validation.Valid;
import org.example.gymtrackerspring.dto.CreateExerciseRequest;
import org.example.gymtrackerspring.dto.ExerciseResponse;
import org.example.gymtrackerspring.dto.UpdateExerciseRequest;
import org.example.gymtrackerspring.entity.Exercise;
import org.example.gymtrackerspring.mapper.ExerciseMapper;
import org.example.gymtrackerspring.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/workouts/{workoutId}/exercises")
public class ExerciseController {

    private final ExerciseService service;
    private final ExerciseMapper mapper;

    public ExerciseController(ExerciseService service, ExerciseMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ExerciseResponse getExercise(@PathVariable Long id){
        Exercise exercise = service.getExerciseById(id);
        return mapper.toResponse(exercise);
    }

    @GetMapping
    public List<ExerciseResponse> getAllExercises(@PathVariable Long workoutId){
        return service.findAllExercises(workoutId).stream().map(mapper::toResponse).toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponse> updateExercise(@Valid @RequestBody UpdateExerciseRequest request, @PathVariable Long id){
        Exercise exercise = service.updateExercise(id,request.getName());
        return ResponseEntity.ok(mapper.toResponse(exercise));
    }

    @PostMapping
    public ResponseEntity<ExerciseResponse> createExercise(
            @PathVariable Long workoutId,
            @Valid @RequestBody CreateExerciseRequest request) {
        Exercise exercise = service.addExercise(workoutId, request.getName());
        return ResponseEntity.created(URI.create("/exercises/" + exercise.getId())).body(mapper.toResponse(exercise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id){
        service.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}
