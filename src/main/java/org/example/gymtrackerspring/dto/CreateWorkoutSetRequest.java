package org.example.gymtrackerspring.dto;

public class CreateWorkoutSetRequest {
    private double weight;
    private int reps;

    public CreateWorkoutSetRequest(){
    }

    public double getWeight(){
        return weight;
    }

    public int getReps(){
        return reps;
    }
}
