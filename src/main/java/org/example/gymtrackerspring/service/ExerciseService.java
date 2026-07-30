package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.entity.Exercise;
import org.example.gymtrackerspring.entity.Workout;
import org.example.gymtrackerspring.exception.ExerciseNotFoundException;
import org.example.gymtrackerspring.exception.WorkoutNotFoundException;
import org.example.gymtrackerspring.repository.ExerciseRepository;
import org.example.gymtrackerspring.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExerciseService {


    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public ExerciseService(ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    public List<Exercise> findAllExercises(Long workoutId){
        return exerciseRepository.findByWorkoutId(workoutId);
    }

    public Exercise getExerciseById(Long id){
        return exerciseRepository.findById(id).orElseThrow(()-> new ExerciseNotFoundException(id));
    }

    public Exercise addExercise(Long workoutId, String name) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new WorkoutNotFoundException(workoutId));

        Exercise exercise = new Exercise(name);

        exercise.setWorkout(workout);

        return exerciseRepository.save(exercise);
    }

    @Transactional
    public Exercise updateExercise(Long id, String name){
        Exercise exercise = getExerciseById(id);
        exercise.setName(name);
        return exercise;
    }

    @Transactional
    public void deleteExercise(Long id){
        Exercise exercise = getExerciseById(id);
        exerciseRepository.delete(exercise);
    }

}
