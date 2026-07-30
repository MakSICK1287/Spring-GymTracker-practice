package org.example.gymtrackerspring.controller;


import jakarta.validation.Valid;
import org.example.gymtrackerspring.dto.CreateWorkoutSetRequest;
import org.example.gymtrackerspring.dto.UpdateWorkoutSetRequest;
import org.example.gymtrackerspring.dto.WorkoutSetResponse;
import org.example.gymtrackerspring.entity.WorkoutSet;
import org.example.gymtrackerspring.mapper.WorkoutSetMapper;
import org.example.gymtrackerspring.service.WorkoutSetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/exercises/{exerciseId}/sets")
public class WorkoutSetController {

    private final WorkoutSetService service;
    private final WorkoutSetMapper mapper;

    public WorkoutSetController(WorkoutSetService service, WorkoutSetMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @GetMapping("{id}")
    public WorkoutSetResponse getSet(@PathVariable Long id){
        WorkoutSet set = service.getSetById(id);
        return mapper.toResponse(set);
    }

    @GetMapping
    public List<WorkoutSetResponse> getAllSets(@PathVariable Long exerciseId){
        return service.getAllSets(exerciseId).stream().map(mapper::toResponse).toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutSetResponse> updateSet(@Valid @RequestBody UpdateWorkoutSetRequest request, @PathVariable Long id){
        WorkoutSet set = service.updateSet(id,request.getWeight(),request.getReps());
        return ResponseEntity.ok(mapper.toResponse(set));
    }

    @PostMapping
    public ResponseEntity<WorkoutSetResponse> createSet(
            @PathVariable Long exerciseId,
            @Valid @RequestBody CreateWorkoutSetRequest request) {
        WorkoutSet set = service.addSet(exerciseId,request.getWeight(),request.getReps());
        return ResponseEntity.created(URI.create("/sets/"+ set.getId())).body(mapper.toResponse(set));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSet(@PathVariable Long id){
        service.deleteSet(id);
        return ResponseEntity.noContent().build();
    }
}
