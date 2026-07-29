package org.example.gymtrackerspring.dto;


import lombok.Getter;

@Getter
public class ExerciseResponse {

    private final Long id;
    private final String name;

    public ExerciseResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
