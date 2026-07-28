package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.dto.CreateWorkoutRequest;
import org.example.gymtrackerspring.entity.Workout;
import org.example.gymtrackerspring.exception.WorkoutNotFoundException;
import org.example.gymtrackerspring.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository repository;

    public WorkoutService(WorkoutRepository repository) {
        this.repository = repository;
    }

    public List<Workout> getAllWorkouts() {
        return repository.findAll();
    }

    public void deleteWorkout(Long id){
        repository.deleteById(id);
    }

    public Workout saveWorkout(LocalDate date){
        Workout workout = new Workout(date);
        return repository.save(workout);
    }

    public Workout getWorkoutByDate(LocalDate date){
        return repository.findByDate(date).orElseThrow(() -> new WorkoutNotFoundException(date));
    }

    public Workout getWorkout(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new WorkoutNotFoundException(id));
    }

    public Workout updateWorkoutDate(Long id, LocalDate newDate){
        Workout workout = repository.findById(id).orElseThrow(()->new WorkoutNotFoundException(id));
        workout.setDate(newDate);
        return repository.save(workout);
    }


}