package com.TddSportsApp.models.dto;

import lombok.Data;

@Data
public class ResultWithEventDetailsDto {
    private Long id;
    private Boolean official;
    private Long time;
    private Integer position;
    private Boolean acceptedByAthlete;
    private String eventName;
    private String eventDescription;
    private Long eventId;

    public ResultWithEventDetailsDto(Long id, Boolean official, Long time, Integer position, Boolean acceptedByAthlete, String eventName, String eventDescription, Long eventId) {
        this.id = id;
        this.official = official;
        this.time = time;
        this.position = position;
        this.acceptedByAthlete = acceptedByAthlete;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventId = eventId;
    }
}
