package org.example.gymtrackerspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<WorkoutSet> sets = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "workout_id")
    @JsonIgnore
    private Workout workout;


    public Exercise(String name){
        this.name = name;
        this.sets = new ArrayList<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void addSet(WorkoutSet set){
        this.sets.add(set);
    }

    public void setId(Long id) {
        this.id = id;
    }

}
