package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.entity.User;
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
    private final CurrentUserService currentUserService;

    public WorkoutService(WorkoutRepository repository, CurrentUserService currentUserService) {
        this.repository = repository;
        this.currentUserService = currentUserService;
    }

    public List<Workout> getAllWorkouts() {
        return repository.findAllByUser(currentUserService.getCurrentUser());
    }

    @Transactional
    public void deleteWorkout(Long id){
        Workout workout = getWorkout(id);
        repository.delete(workout);
    }

    public Workout createWorkout(LocalDate date){
        Workout workout = new Workout(date);
        workout.setUser(currentUserService.getCurrentUser());
        return repository.save(workout);
    }

    public Workout getWorkout(Long id) {
        return repository.findByIdAndUser(id,currentUserService.getCurrentUser())
                .orElseThrow(() -> new WorkoutNotFoundException(id));
    }

    @Transactional
    public Workout updateWorkout(Long id, LocalDate newDate){
        Workout workout = repository.findByIdAndUser(id, currentUserService.getCurrentUser()).orElseThrow(()->new WorkoutNotFoundException(id));
        workout.setDate(newDate);
        return workout;
    }


}