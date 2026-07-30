package org.example.gymtrackerspring.repository;

import org.example.gymtrackerspring.entity.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Long> {
    List<WorkoutSet> findByExerciseId(Long exerciseId);
}