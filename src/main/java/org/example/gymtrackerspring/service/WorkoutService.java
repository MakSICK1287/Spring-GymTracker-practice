package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.entity.Workout;
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

    public Workout saveWorkout(Workout workout){
        return repository.save(workout);
    }

    public Workout getWorkoutByDate(LocalDate date){
        return repository.findByDate(date).orElseThrow(() -> new RuntimeException("Тренировка не найдена"));
    }


}