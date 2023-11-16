package com.TddSportsApp.models.dto;

import lombok.Data;

@Data
public class ResultWithUsernameDto {
    private Long id;

    private Boolean official;

    private Long time;

    private Integer position;

    private Boolean acceptedByAthlete;

    private String username;

    public ResultWithUsernameDto(Long id, Boolean official, Long time, Integer position, Boolean acceptedByAthlete, String username) {
        this.id = id;
        this.official = official;
        this.time = time;
        this.position = position;
        this.acceptedByAthlete = acceptedByAthlete;
        this.username = username;
    }
}
