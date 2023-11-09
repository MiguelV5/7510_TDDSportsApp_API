package com.TddSportsApp.repositories;

import com.TddSportsApp.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
