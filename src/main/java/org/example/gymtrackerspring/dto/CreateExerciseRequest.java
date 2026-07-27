package org.example.gymtrackerspring.dto;

public class CreateExerciseRequest {

    private String name;

    public CreateExerciseRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}