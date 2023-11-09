package com.TddSportsApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private Boolean official;

    @NotBlank
    private Long time;

    @NotBlank
    private Integer position;

    @NotBlank
    private Boolean acceptedByAthlete;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;
}
