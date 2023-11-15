package com.TddSportsApp.controller;

import com.TddSportsApp.models.dto.CreateCommentDto;
import com.TddSportsApp.models.dto.UpdateCommentDto;
import com.TddSportsApp.models.Comment;
import com.TddSportsApp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("")
    public Comment createComment(@RequestBody CreateCommentDto createComment){
        return commentService.createComment(createComment);
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id){
        return commentService.getCommentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody UpdateCommentDto commentDto){
        return commentService.updateComment(id, commentDto);
    }
}
