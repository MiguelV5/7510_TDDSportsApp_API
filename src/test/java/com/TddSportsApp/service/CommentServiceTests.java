package com.TddSportsApp.service;


import com.TddSportsApp.exceptions.EventNotFoundException;
import com.TddSportsApp.exceptions.ForbiddenActionException;
import com.TddSportsApp.models.Comment;
import com.TddSportsApp.models.Event;
import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.models.dto.CreateCommentDto;
import com.TddSportsApp.models.dto.UpdateCommentDto;
import com.TddSportsApp.repositories.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTests {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private EventService eventService;

    @Mock
    private UserService userService;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void whenCreateComment_thenReturnComment() {
        // given
        CreateCommentDto createCommentDto = new CreateCommentDto();
        createCommentDto.setCommentText("commentText");
        createCommentDto.setEventId(1L);

        // when
        when(commentRepository.save(any(Comment.class))).thenReturn(new Comment());
        when(eventService.getEventById(any(Long.class))).thenReturn(new Event());
        when(userService.getLoggedUser()).thenReturn(new UserEntity());
        Comment comment = commentService.createComment(createCommentDto);

        // then
        assertThat(comment.getCommentText()).isEqualTo(createCommentDto.getCommentText());
        assertThat(eventService.getEventById(createCommentDto.getEventId())).isNotNull();
        assertThat(createCommentDto.getEventId()).isEqualTo(1L);
    }

    @Test
    public void whenGetCommentById_thenReturnComment() {
        // given
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setCommentText("commentText");
        comment.setEvent(new Event());
        comment.setUser(new UserEntity());

        // when
        when(commentRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(comment));
        Comment comment1 = commentService.getCommentById(1L);

        // then
        assertThat(comment1.getCommentText()).isEqualTo(comment.getCommentText());
        assertThat(comment1.getEvent()).isEqualTo(comment.getEvent());
        assertThat(comment1.getUser()).isEqualTo(comment.getUser());
    }

    @Test
    public void whenUpdateComment_thenReturnComment() {
        // given
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setCommentText("commentText");
        comment.setEvent(new Event());
        comment.setUser(new UserEntity());


        // when
        when(commentRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(comment));
        when(userService.isLoggedUserAdmin()).thenReturn(true);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
        Comment comment1 = commentService.updateComment(1L, new UpdateCommentDto());

        // then
        assertThat(comment1.getCommentText()).isEqualTo(comment.getCommentText());
        assertThat(comment1.getEvent()).isEqualTo(comment.getEvent());
        assertThat(comment1.getUser()).isEqualTo(comment.getUser());
    }

    @Test
    public void whenDeleteCommentAndGetComment_thenReturnException() {
        // given
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setCommentText("commentText");
        comment.setEvent(new Event());
        comment.setUser(new UserEntity());

        // when
        when(commentRepository.findById(any(Long.class))).thenReturn(Optional.of(comment));
        when(userService.isLoggedUserAdmin()).thenReturn(true);
        commentService.deleteComment(1L);
        when(commentRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // then
        assertThat(commentRepository.findById(1L)).isEmpty();
    }

    @Test
    public void whenValidateCommentOwner_thenReturnException() {
        // given
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setCommentText("commentText");
        comment.setEvent(new Event());
        comment.setUser(new UserEntity());

        // when
        when(commentRepository.findById(any(Long.class))).thenReturn(Optional.of(comment));
        when(userService.isLoggedUserAdmin()).thenReturn(false);
        when(userService.isLoggedUserOwnerOfComment(any(Long.class))).thenReturn(false);

        // then
        try {
            commentService.validateCommentOwner(1L);
        } catch (ForbiddenActionException e) {
            assertThat(e.getMessage()).isEqualTo("You are not allowed to delete this comment");
        }
        assertThat(commentRepository.findById(1L)).isNotEmpty();
    }

    @Test
    public void whenValidateCommentOwner_thenReturnNoException() {
        // given
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setCommentText("commentText");
        comment.setEvent(new Event());
        comment.setUser(new UserEntity());

        // when
        when(commentRepository.findById(any(Long.class))).thenReturn(Optional.of(comment));
        when(userService.isLoggedUserAdmin()).thenReturn(true);
        commentService.validateCommentOwner(1L);

        // then
        assertThat(commentRepository.findById(1L)).isNotEmpty();
    }




}
