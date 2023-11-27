package com.TddSportsApp.controller;

import com.TddSportsApp.exceptions.CommentNotFoundException;
import com.TddSportsApp.exceptions.EventNotFoundException;
import com.TddSportsApp.exceptions.ForbiddenActionException;
import com.TddSportsApp.models.dto.CreateCommentDto;
import com.TddSportsApp.models.dto.UpdateCommentDto;
import com.TddSportsApp.models.Comment;
import com.TddSportsApp.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("")
    public ResponseEntity<Comment> createComment(@Valid @RequestBody CreateCommentDto createComment){
        try {
            return ResponseEntity.ok(commentService.createComment(createComment));
        } catch (EventNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(commentService.getCommentById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id){
        try {
            commentService.deleteComment(id);
            return ResponseEntity.ok().build();
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenActionException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @Valid @RequestBody UpdateCommentDto commentDto){
        try {
            return ResponseEntity.ok(commentService.updateComment(id, commentDto));
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenActionException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
