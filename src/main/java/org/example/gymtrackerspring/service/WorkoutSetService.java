package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.entity.Exercise;
import org.example.gymtrackerspring.entity.WorkoutSet;
import org.example.gymtrackerspring.repository.ExerciseRepository;
import org.example.gymtrackerspring.repository.WorkoutSetRepository;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RuntimeException("Упражнение не найдено"));

        WorkoutSet set = new WorkoutSet(weight,reps);

        set.setExercise(exercise);

        return workoutSetRepository.save(set);
    }
}
