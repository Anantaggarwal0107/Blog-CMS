package com.auth.template.demo.scopes.blog.services;

import com.auth.template.demo.scopes.blog.entities.Article;
import com.auth.template.demo.scopes.blog.entities.Comment;
import com.auth.template.demo.scopes.blog.forms.NoteDto;
import com.auth.template.demo.scopes.blog.repositories.ArticleRepository;
import com.auth.template.demo.scopes.blog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    public void saveArticle(Article article){
        if(article == null) throw new NullPointerException("Article must no be null");
        articleRepository.save(article);
    }

    public List<Article> getArticles(){
        return articleRepository.findAll();
    }

    public Optional<Article> getArticleById(long id){
        return articleRepository.findById(id);
    }

    public void addComment(Comment comment){
        if(comment == null) throw new NullPointerException("comment should not be null");
        if(comment.getArticleId() == null) throw new NullPointerException("articleIId should not be null");

        Optional<Article> article = getArticleById(comment.getArticleId());
        if(article.isEmpty()) throw new NullPointerException("article is not in database");

        commentRepository.save(comment);
        article.get().getComments().add((comment));
        saveArticle(article.get());
    }

    public void deleteArticleById(long id){

        Optional<Article> article = getArticleById(id);
        if(article.isEmpty()) throw new NullPointerException("article is not in database");

        deleteAllComments(article.get());
        articleRepository.deleteById(id);
    }

    public List<Comment> getCommentsByArticle(Long articleId){
        if(articleId == null) throw new NullPointerException("articleIId should not be null");

        Optional<Article> article = getArticleById(articleId);
        if(article.isEmpty()) throw new NullPointerException("article is not in database");

        return new ArrayList<>(article.get().getComments());
    }


    public Optional<Comment> getCommentById(Long commentId){
        return commentRepository.findById(commentId);
    }

        public void deleteCommentById(Long commentId){
        if(commentId == null) throw new NullPointerException("comment should not be null");

        Optional<Comment> comment = commentRepository.findById(commentId);

        if(comment.isEmpty()) throw new NullPointerException("comment should not be null");

        Optional<Article> article = getArticleById(comment.get().getArticleId());

        if(article.isEmpty()) throw new NullPointerException("article is not in database");

        article.get().getComments().removeIf(c -> c.getArticleId().equals(comment.get().getArticleId()) && c.getAuthorName().equals(comment.get().getAuthorName())
                && c.getComment().equals(comment.get().getComment()));

        saveArticle(article.get());
    }

    public void deleteAllComments(Article article){
        if(article == null) throw new NullPointerException("article must not be null");

        for(Comment comment : article.getComments()){
            article.getComments().remove(comment);
            commentRepository.delete(comment);
        }
    }

    public void updateNote(NoteDto noteDto) {
        Optional<Article> article = articleRepository.findById(noteDto.articleId);
        if (article.isEmpty()) throw new NullPointerException("article is not in database");
        if(article.get().getNote() == 0)
            article.get().setNote(noteDto.note);
        else{
            article.get().setNote(
                    (Long)((article.get().getNote() + noteDto.note) / 2)
            );
        }
        saveArticle(article.get());
    }
}
