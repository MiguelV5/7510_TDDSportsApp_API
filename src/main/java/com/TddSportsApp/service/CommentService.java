package com.TddSportsApp.service;

import com.TddSportsApp.controller.dto.CreateCommentDto;
import com.TddSportsApp.models.Comment;
import com.TddSportsApp.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    public Comment createComment(CreateCommentDto commentDto){
        Comment comment = Comment.builder()
                .text(commentDto.getText())
                .build();

        commentRepository.save(comment);
        return comment;
    }
}
