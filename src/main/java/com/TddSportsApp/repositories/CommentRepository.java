package com.TddSportsApp.repositories;

import com.TddSportsApp.models.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    public List<Comment> findCommentsByEventId(Long eventId);
}
