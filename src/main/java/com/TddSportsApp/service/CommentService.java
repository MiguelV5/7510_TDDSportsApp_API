package com.TddSportsApp.service;

import com.TddSportsApp.controller.dto.CreateCommentDto;
import com.TddSportsApp.exceptions.EventNotFoundException;
import com.TddSportsApp.exceptions.UserNotFoundException;
import com.TddSportsApp.models.Comment;
import com.TddSportsApp.models.Event;
import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.repositories.CommentRepository;
import com.TddSportsApp.repositories.EventRepository;
import com.TddSportsApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Comment createComment(Event event, UserEntity userEntity, String commentText){
        Comment comment = Comment.builder()
                .commentText(commentText)
                .event(event)
                .user(userEntity)
                .build();

        commentRepository.save(comment);
        return comment;
    }

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }

    public Comment updateComment(Long id, String commentText){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Comment not found with ID: " + id));

        comment.setCommentText(commentText);
        commentRepository.save(comment);
        return comment;
    }
}
