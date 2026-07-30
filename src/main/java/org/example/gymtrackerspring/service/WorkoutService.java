package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.entity.Workout;
import org.example.gymtrackerspring.exception.WorkoutNotFoundException;
import org.example.gymtrackerspring.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void deleteWorkout(Long id){
        Workout workout = getWorkout(id);
        repository.delete(workout);
    }

    public Workout createWorkout(LocalDate date){
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

    @Transactional
    public Workout updateWorkout(Long id, LocalDate newDate){
        Workout workout = repository.findById(id).orElseThrow(()->new WorkoutNotFoundException(id));
        workout.setDate(newDate);
        return workout;
    }


}