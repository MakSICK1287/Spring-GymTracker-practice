package org.example.gymtrackerspring.dto;


import lombok.Getter;

import java.time.LocalDate;

@Getter
public class WorkoutResponse {

    private final Long id;

    private final LocalDate date;

    public WorkoutResponse(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

}
