package org.example.gymtrackerspring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExerciseRequest {

    @NotNull
    private String name;

    public CreateExerciseRequest() {
    }
}