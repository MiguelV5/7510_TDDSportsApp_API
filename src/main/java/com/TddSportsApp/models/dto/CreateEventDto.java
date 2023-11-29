package com.TddSportsApp.models.dto;
import com.TddSportsApp.models.Event;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CreateEventDto {
    @Size(min = 3, max = 80, message = "Name should be between 3 and 80 characters")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Distance is required")
    private Long distance;

    @NotNull(message = "Edition is required")
    private Integer edition;

    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Event toEvent() {
        return Event.builder()
                .name(this.name)
                .description(this.description)
                .location(this.location)
                .category(this.category)
                .distance(this.distance)
                .edition(this.edition)
                .date(this.date)
                .build();
    }
}