package com.TddSportsApp.controller.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDto {
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
}