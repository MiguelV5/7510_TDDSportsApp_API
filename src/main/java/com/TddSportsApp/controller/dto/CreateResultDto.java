package com.TddSportsApp.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateResultDto {
    @NotBlank
    private Boolean official;

    @NotBlank
    private Long time;

    @NotBlank
    private Integer position;

    @NotBlank
    private Boolean acceptedByAthlete;
}