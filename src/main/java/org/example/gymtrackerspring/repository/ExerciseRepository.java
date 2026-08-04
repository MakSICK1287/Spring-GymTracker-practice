package org.example.gymtrackerspring.repository;

import org.example.gymtrackerspring.entity.Exercise;
import org.example.gymtrackerspring.entity.User;
import org.example.gymtrackerspring.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByWorkout(Workout workout);
    Optional<Exercise> findByIdAndWorkoutUser(Long id, User user);

}