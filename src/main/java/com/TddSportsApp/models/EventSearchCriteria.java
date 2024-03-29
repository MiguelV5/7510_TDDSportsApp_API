package com.TddSportsApp.models;

import jakarta.validation.constraints.Size;
import jdk.jfr.BooleanFlag;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
public class EventSearchCriteria {
    @Size(max = 80)
    private String location;

    @Size(max = 80)
    private String category;

    private Integer edition;
    private Integer startDistance;
    private Integer endDistance;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Boolean enrolled;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Integer getStartDistance() {
        return startDistance;
    }

    public Integer getEndDistance() {
        return endDistance;
    }
    public void setStartDistance(Integer startDistance) {
        this.startDistance = startDistance;
    }

    public void setEndDistance(Integer endDistance) {
        this.endDistance = endDistance;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getEnrolled() {
        return enrolled;
    }
}
