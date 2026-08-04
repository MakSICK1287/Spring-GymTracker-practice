package org.example.gymtrackerspring.repository;

import org.example.gymtrackerspring.entity.User;
import org.example.gymtrackerspring.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    Optional<Workout> findByDate(LocalDate date);
    Optional<Workout> findByIdAndUser(Long id, User user);
}