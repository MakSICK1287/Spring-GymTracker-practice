package org.example.gymtrackerspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "workout_sets")
public class WorkoutSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "weight")
    private double weight;
    @Column(name = "reps")
    private int reps;
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    @JsonIgnore
    private Exercise exercise;

    public WorkoutSet(double weight, int reps) {
        this.weight = weight;
        this.reps = reps;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public double getWeight() {
        return weight;
    }


    public void setWeight(double weight) {
        this.weight = weight;
    }


    public int getReps() {
        return reps;
    }


    public void setReps(int reps) {
        this.reps = reps;
    }
}