package org.example.gymtrackerspring.dto;

import org.example.gymtrackerspring.entity.WorkoutSet;


public class ExerciseProgress {
    
    private final WorkoutSet bestSet;

    private final double current1RM;

    private final double average1RM;

    public ExerciseProgress(
            WorkoutSet bestSet,
            double current1RM,
            double average1RM) {

        this.bestSet = bestSet;
        this.current1RM = current1RM;
        this.average1RM = average1RM;
    }

    public WorkoutSet getBestSet() {
        return bestSet;
    }

    public double getCurrent1RM() {
        return current1RM;
    }

    public double getAverage1RM() {
        return average1RM;
    }

    public double getDifference() {
        return current1RM - average1RM;
    }
}