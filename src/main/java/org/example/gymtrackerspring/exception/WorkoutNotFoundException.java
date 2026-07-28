package org.example.gymtrackerspring.exception;

import java.time.LocalDate;

public class WorkoutNotFoundException extends RuntimeException {

    public WorkoutNotFoundException(Long id) {
        super("Тренировка с id " + id + " не найдена");
    }
    public WorkoutNotFoundException(LocalDate date) {
        super("Тренировка с датой " + date + " не найдена");
    }
}