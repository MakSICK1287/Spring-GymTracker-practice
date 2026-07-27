package org.example.gymtrackerspring.repository;

import org.example.gymtrackerspring.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}