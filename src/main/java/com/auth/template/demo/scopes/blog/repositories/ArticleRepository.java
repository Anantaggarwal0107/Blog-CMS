package com.auth.template.demo.scopes.blog.repositories;

import com.auth.template.demo.scopes.blog.entities.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article,Long> {
    @Override
    List<Article> findAll();
}
