package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.entity.Workout;
import org.example.gymtrackerspring.exception.WorkoutNotFoundException;
import org.example.gymtrackerspring.repository.WorkoutRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutServiceTest {

    @Mock
    private WorkoutRepository repository;

    @InjectMocks
    private WorkoutService service;

    @Test
    void shouldReturnWorkoutWhenWorkoutExists() {

        Workout workout = new Workout(LocalDate.of(2026, 7, 30));
        workout.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(workout));

        Workout result = service.getWorkout(1L);

        assertEquals(workout, result);
    }

    @Test
    void shouldThrowWhenWorkoutDoesNotExist() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        WorkoutNotFoundException exception =
                assertThrows(
                        WorkoutNotFoundException.class,
                        () -> service.getWorkout(1L)
                );

        assertEquals(
                "Тренировка с id 1 не найдена",
                exception.getMessage()
        );
    }

    @Test
    void deleteVerifyTest(){
        Workout workout = new Workout(LocalDate.now());
        workout.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(workout));
        service.deleteWorkout(1L);
        verify(repository).delete(workout);
    }

    @Test
    void shouldDeleteWorkoutWhenWorkoutExists(){
        Workout workout = new Workout(LocalDate.now());
        workout.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(workout));
        service.deleteWorkout(1L);
        verify(repository).findById(1L);
        verify(repository).delete(workout);
        verifyNoMoreInteractions(repository);
    }
}