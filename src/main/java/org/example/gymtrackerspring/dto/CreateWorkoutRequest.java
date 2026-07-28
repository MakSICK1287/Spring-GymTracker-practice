package org.example.gymtrackerspring.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class CreateWorkoutRequest {

    @NotNull
    @PastOrPresent
    private LocalDate date;

}
