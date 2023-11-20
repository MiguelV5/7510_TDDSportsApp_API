package com.TddSportsApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="event")
public class Event {
    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 80)
    private String name;

    private String description;

    @NotBlank
    private String location;

    @NotBlank
    private String category;

    @NotBlank
    private Long distance;

    @NotBlank
    private Integer edition;

    @NotBlank
    private Date date;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Result> results;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inscription> inscriptions;
}
