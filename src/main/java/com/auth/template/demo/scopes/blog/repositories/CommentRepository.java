package com.auth.template.demo.scopes.blog.repositories;

import com.auth.template.demo.scopes.blog.entities.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Long> {

    List<Comment> findAll();
}
