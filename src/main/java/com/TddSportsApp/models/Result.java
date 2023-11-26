package com.TddSportsApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
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
    @JsonIgnore
    private Event event;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private UserEntity user;
}
