package com.TddSportsApp.models.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDto {
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
}