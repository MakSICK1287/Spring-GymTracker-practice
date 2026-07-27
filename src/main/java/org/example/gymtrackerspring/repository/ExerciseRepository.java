package org.example.gymtrackerspring.repository;

import org.example.gymtrackerspring.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}