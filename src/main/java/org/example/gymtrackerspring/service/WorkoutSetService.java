package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.entity.Exercise;
import org.example.gymtrackerspring.entity.User;
import org.example.gymtrackerspring.entity.WorkoutSet;
import org.example.gymtrackerspring.exception.ExerciseNotFoundException;
import org.example.gymtrackerspring.exception.WorkoutSetNotFoundException;
import org.example.gymtrackerspring.repository.ExerciseRepository;
import org.example.gymtrackerspring.repository.WorkoutSetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkoutSetService {

    private final WorkoutSetRepository  workoutSetRepository;
    private final ExerciseService exerciseService;
    private final CurrentUserService currentUserService;

    public WorkoutSetService(WorkoutSetRepository workoutSetRepository, ExerciseService exerciseService, CurrentUserService currentUserService) {
        this.workoutSetRepository = workoutSetRepository;
        this.exerciseService = exerciseService;
        this.currentUserService = currentUserService;
    }

    public WorkoutSet addSet(Long exerciseId, double weight, int reps){
        WorkoutSet set = new WorkoutSet(weight,reps);
        set.setExercise(exerciseService.getExerciseById(exerciseId));
        return workoutSetRepository.save(set);
    }

    public WorkoutSet getSetById(Long id){
        return workoutSetRepository.findByIdAndExerciseWorkoutUser(id,currentUserService.getCurrentUser())
                .orElseThrow(()-> new WorkoutSetNotFoundException(id));
    }

    public List<WorkoutSet> getAllSets(Long exerciseId){
        return workoutSetRepository.findByExercise(exerciseService.getExerciseById(exerciseId));
    }

    @Transactional
    public void deleteSet(Long id){
        WorkoutSet set = getSetById(id);
        workoutSetRepository.delete(set);
    }

    @Transactional
    public WorkoutSet updateSet(Long id, double weight,int reps){
        WorkoutSet set = getSetById(id);
        set.setWeight(weight);
        set.setReps(reps);
        return set;
    }
}
