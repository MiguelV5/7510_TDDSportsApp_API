package com.TddSportsApp.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
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

    @NotBlank
    private String location;

    @NotBlank
    private String type;

    @NotBlank
    private String modality;

    @NotBlank
    private Long distance;

    @NotBlank
    private Integer edition;

    @OneToMany(mappedBy = "event")
    private List<Result> results;
}
