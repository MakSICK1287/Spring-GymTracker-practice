package org.example.gymtrackerspring.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateWorkoutSetRequest {

    @NotNull
    @Positive
    double weight;
    @NotNull
    @Positive
    int reps;
}
