package com.TddSportsApp.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentWithUsernameDto {
    private Long id;

    private String commentText;

    private Date commentDate;

    private String username;

    public CommentWithUsernameDto(Long id, String commentText, Date commentDate, String username) {
        this.id = id;
        this.commentText = commentText;
        this.commentDate = commentDate;
        this.username = username;
    }
}
