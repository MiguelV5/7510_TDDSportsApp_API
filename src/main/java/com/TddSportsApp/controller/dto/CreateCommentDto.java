package com.TddSportsApp.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCommentDto {
    @NotBlank
    private String commentText;

    @NotNull
    private Long eventId;

    @NotNull
    private Long userId;
}
