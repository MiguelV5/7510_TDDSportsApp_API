package com.TddSportsApp.controller;

import com.TddSportsApp.controller.dto.CreateCommentDto;
import com.TddSportsApp.models.Comment;
import com.TddSportsApp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("")
    public Comment createComment(@RequestBody CreateCommentDto commentDto){
        return commentService.createComment(commentDto);
    }
}
