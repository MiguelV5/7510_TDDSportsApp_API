package com.TddSportsApp.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateResultDto {
    @NotNull(message = "Official is required")
    private Boolean official;

    @NotNull(message = "Time is required")
    private Long time;

    @NotNull(message = "Position is required")
    private Integer position;

    @NotNull(message = "AcceptedByAthlete is required")
    private Boolean acceptedByAthlete;

    @NotNull(message = "EventId is required")
    private Long eventId;

    @NotNull(message = "UserId is required")
    private Long userId;
}