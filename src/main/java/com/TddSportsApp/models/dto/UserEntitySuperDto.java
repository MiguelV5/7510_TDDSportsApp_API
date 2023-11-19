package com.TddSportsApp.models.dto;

import com.TddSportsApp.models.Comment;
import com.TddSportsApp.models.Inscription;
import lombok.Data;

import java.util.List;

@Data
public class UserEntitySuperDto {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String role;
    private List<Comment> comments;
    private List<Inscription> inscriptions;
    private List<ResultWithEventDetailsDto> results;

    public UserEntitySuperDto(Long id, String email, String username, String password, String role, List<Comment> comments, List<Inscription> inscriptions, List<ResultWithEventDetailsDto> results) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.comments = comments;
        this.inscriptions = inscriptions;
        this.results = results;
    }
}
