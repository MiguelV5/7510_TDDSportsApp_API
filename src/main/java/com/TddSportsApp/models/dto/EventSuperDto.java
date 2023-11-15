package com.TddSportsApp.models.dto;

import com.TddSportsApp.models.Comment;
import com.TddSportsApp.models.Event;
import com.TddSportsApp.models.Inscription;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EventSuperDto {
    private Long id;

    private String name;

    private String description;

    private String location;

    private String category;

    private Long distance;

    private Integer edition;

    private Date date;

    private List<ResultWithUsernameDto> results;

    private List<Comment> comments;

    private List<Inscription> inscriptions;

    public EventSuperDto(Long id, String name, String description, String location, String category, Long distance, Integer edition, Date date, List<ResultWithUsernameDto> results, List<Comment> comments, List<Inscription> inscriptions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
        this.distance = distance;
        this.edition = edition;
        this.date = date;
        this.results = results;
        this.comments = comments;
        this.inscriptions = inscriptions;
    }
}
