package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.entity.Exercise;
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
    private final ExerciseRepository exerciseRepository;

    public WorkoutSetService(WorkoutSetRepository workoutSetRepository, ExerciseRepository exerciseRepository) {
        this.workoutSetRepository = workoutSetRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public WorkoutSet addSet(Long exerciseId, double weight, int reps){
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ExerciseNotFoundException(exerciseId));
        WorkoutSet set = new WorkoutSet(weight,reps);
        set.setExercise(exercise);
        return workoutSetRepository.save(set);
    }

    public WorkoutSet getSetById(Long id){
        return workoutSetRepository.findById(id)
                .orElseThrow(()-> new WorkoutSetNotFoundException(id));
    }

    public List<WorkoutSet> getAllSets(Long exerciseId){
        return workoutSetRepository.findByExerciseId(exerciseId);
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
