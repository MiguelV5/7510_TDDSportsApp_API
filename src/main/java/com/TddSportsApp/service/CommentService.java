package com.TddSportsApp.service;

import com.TddSportsApp.controller.dto.CreateCommentDto;
import com.TddSportsApp.models.Comment;
import com.TddSportsApp.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(CreateCommentDto commentDto){
        Comment comment = Comment.builder()
                .commentText(commentDto.getCommentText())
                .build();

        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> getCommentsByEventId(Long eventId){
        return commentRepository.findCommentsByEventId(eventId);
    }
}
