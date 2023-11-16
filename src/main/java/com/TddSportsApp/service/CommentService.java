package com.TddSportsApp.service;

import com.TddSportsApp.models.dto.CreateCommentDto;
import com.TddSportsApp.exceptions.EventNotFoundException;
import com.TddSportsApp.models.Comment;
import com.TddSportsApp.models.dto.UpdateCommentDto;
import com.TddSportsApp.models.Event;
import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.repositories.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Transactional
    public Comment createComment(CreateCommentDto createComment){
        Event event = eventService.getEventById(createComment.getEventId());
        UserEntity user = userService.getLoggedUser();

        Comment comment = Comment.builder()
                .commentText(createComment.getCommentText())
                .event(event)
                .user(user)
                .commentDate(new Date())
                .build();

        commentRepository.save(comment);
        return comment;
    }

    public Comment getCommentById(Long id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Comment not found with ID: " + id));
    }

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }

    public Comment updateComment(Long id, UpdateCommentDto commentDto) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Comment not found with ID: " + id));

        comment.setCommentText(commentDto.getCommentText());
        commentRepository.save(comment);
        return comment;
    }
}
