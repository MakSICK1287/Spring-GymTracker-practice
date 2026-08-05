package org.example.gymtrackerspring.service;

import org.example.gymtrackerspring.entity.User;
import org.example.gymtrackerspring.entity.Workout;
import org.example.gymtrackerspring.exception.WorkoutNotFoundException;
import org.example.gymtrackerspring.repository.WorkoutRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutServiceTest {

    User createUser(){
        User user = new User();
        user.setId(1L);
        user.setUsername("max");
        return user;
    }

    Workout createWorkout(LocalDate date, User user){
        Workout workout = new Workout(date);
        workout.setUser(user);
        workout.setId(1L);
        return workout;
    }

    @Mock
    private WorkoutRepository repository;

    @Mock
    private CurrentUserService currentUserService;

    @InjectMocks
    private WorkoutService service;

    @Test
    void createWorkout_shouldCreateWorkout(){
        User user = createUser();

        LocalDate date = LocalDate.of(2026, 8, 5);

        when(currentUserService.getCurrentUser())
                .thenReturn(user);
        when(repository.save(any(Workout.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Workout workout = service.createWorkout(date);
        assertEquals(date, workout.getDate());
        assertEquals(user, workout.getUser());
        verify(currentUserService).getCurrentUser();
        verify(repository).save(any(Workout.class));
    }

    @Test
    void getWorkout_shouldReturnWorkout(){
        User user = createUser();
        LocalDate date = LocalDate.of(2026, 8, 5);
        Workout workout = createWorkout(date,user);

        when(currentUserService.getCurrentUser())
                .thenReturn(user);
        when(repository.findByIdAndUser(1L,user))
                .thenReturn(Optional.of(workout));

        Workout getWorkout = service.getWorkout(1L);
        assertSame(getWorkout,workout);
        verify(currentUserService).getCurrentUser();
        verify(repository).findByIdAndUser(1L,user);

    }

    @Test
    void getWorkout_shouldNotReturnWorkout(){
        User user = createUser();

        when(currentUserService.getCurrentUser())
                .thenReturn(user);
        when(repository.findByIdAndUser(1L,user))
                .thenReturn(Optional.empty());


        assertThrows(WorkoutNotFoundException.class, () -> service.getWorkout(1L));
        verify(repository).findByIdAndUser(1L,user);
        verify(currentUserService).getCurrentUser();
    }

    @Test
    void deleteWorkout_shouldDeleteWorkout(){
        User user = createUser();
        LocalDate date = LocalDate.of(2026, 8, 5);
        Workout workout = createWorkout(date, user);

        when(currentUserService.getCurrentUser())
                .thenReturn(user);
        when(repository.findByIdAndUser(1L,user))
                .thenReturn(Optional.of(workout));

        service.deleteWorkout(1L);

        verify(currentUserService).getCurrentUser();
        InOrder inorder = inOrder(repository);
        inorder.verify(repository).findByIdAndUser(1L,user);
        inorder.verify(repository).delete(workout);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void updateWorkout_shouldUpdateWorkout(){
        User user = createUser();
        LocalDate oldDate = LocalDate.of(2026,8,5);
        LocalDate newDate = LocalDate.of(2026,8,4);
        Workout workout = createWorkout(oldDate,user);

        when(currentUserService.getCurrentUser())
                .thenReturn(user);
        when(repository.findByIdAndUser(1L,user))
                .thenReturn(Optional.of(workout));


        Workout updatedWorkout = service.updateWorkout(1L,newDate);

        assertSame(workout,updatedWorkout);
        assertEquals(newDate,updatedWorkout.getDate());
        verify(repository).findByIdAndUser(1L,user);
        verify(repository, never()).save(any());
    }































































//    @Test
//    void shouldReturnWorkoutWhenWorkoutExists() {
//
//        Workout workout = new Workout(LocalDate.of(2026, 7, 30));
//        workout.setId(1L);
//
//        when(repository.findById(1L))
//                .thenReturn(Optional.of(workout));
//
//        Workout result = service.getWorkout(1L);
//
//        assertEquals(workout, result);
//    }
//
//    @Test
//    void shouldThrowWhenWorkoutDoesNotExist() {
//
//        when(repository.findById(1L))
//                .thenReturn(Optional.empty());
//
//        WorkoutNotFoundException exception =
//                assertThrows(
//                        WorkoutNotFoundException.class,
//                        () -> service.getWorkout(1L)
//                );
//
//        assertEquals(
//                "Тренировка с id 1 не найдена",
//                exception.getMessage()
//        );
//    }
//
//    @Test
//    void deleteVerifyTest(){
//        Workout workout = new Workout(LocalDate.now());
//        workout.setId(1L);
//        when(repository.findById(1L)).thenReturn(Optional.of(workout));
//        service.deleteWorkout(1L);
//        verify(repository).delete(workout);
//    }
//
//    @Test
//    void shouldDeleteWorkoutWhenWorkoutExists(){
//        Workout workout = new Workout(LocalDate.now());
//        workout.setId(1L);
//        when(repository.findById(1L)).thenReturn(Optional.of(workout));
//        service.deleteWorkout(1L);
//        verify(repository).findById(1L);
//        verify(repository).delete(workout);
//        verifyNoMoreInteractions(repository);
//    }
}