package org.example.gymtrackerspring.repository;

import org.example.gymtrackerspring.entity.Exercise;
import org.example.gymtrackerspring.entity.User;
import org.example.gymtrackerspring.entity.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Long> {
    List<WorkoutSet> findByExercise(Exercise exercise);
    Optional<WorkoutSet> findByIdAndExerciseWorkoutUser(Long id, User user);
}