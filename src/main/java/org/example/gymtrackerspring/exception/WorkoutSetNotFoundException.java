package org.example.gymtrackerspring.exception;

public class WorkoutSetNotFoundException extends RuntimeException{
    public WorkoutSetNotFoundException(Long id){super("Подход с id "+ id +" не найден");}
}
