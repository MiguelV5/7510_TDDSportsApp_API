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
@Table(name="comment")
public class Comment {
    // NO SE SI FALTA AGREGARLE ALGO MAS
    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String text;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;

    @OneToOne
    @JoinColumn(name="user_id")
    private UserEntity userEntity;
}
