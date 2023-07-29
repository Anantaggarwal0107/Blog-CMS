package com.auth.template.demo.scopes.blog.controller;

import com.auth.template.demo.scopes.auth.payload.response.MessageResponse;
import com.auth.template.demo.scopes.blog.entities.Article;
import com.auth.template.demo.scopes.blog.entities.Comment;
import com.auth.template.demo.scopes.blog.forms.NoteDto;
import com.auth.template.demo.scopes.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/blog")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public List<Article> getAllArticles(){
        return articleService.getArticles();
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<?> getArticle(@PathVariable Optional<Long> id){
        if(id.isEmpty())
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Id not present"));

        Optional<Article> article = articleService.getArticleById(id.get());

        if(article.isEmpty())
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Article not found"));

        return ResponseEntity.ok(article.get());
    }

    @GetMapping("/article/delete/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Optional<Long> id){
        if(id.isEmpty())
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Id not present"));

        Optional<Article> article = articleService.getArticleById(id.get());
        if(article.isEmpty())
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Article not found"));

        articleService.deleteArticleById(id.get());
        return ResponseEntity.ok(articleService.getArticles());
    }

    @PostMapping("/article/add")
    public ResponseEntity<?> addArticle(@RequestBody Article article){


        if(article == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Article invalid"));

        article.setDate(LocalDate.now());
        articleService.saveArticle(article);
        return ResponseEntity.ok(new MessageResponse("Article was added"));
    }

    @PostMapping("/article/addComment")
    public ResponseEntity<?> addComment(@RequestBody Comment comment){

        if(comment == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Article invalid"));
        articleService.addComment(comment);

        return ResponseEntity.ok(articleService.getArticleById(comment.getArticleId()));
    }

    @GetMapping("/article/deleteComment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Optional<Long> commentId){

        if(commentId.isEmpty())
            return ResponseEntity.badRequest().body(new MessageResponse("Error: comment invalid"));

        articleService.deleteCommentById(commentId.get());
        return ResponseEntity.ok(articleService.getArticleById(articleService.getCommentById(commentId.get()).get().getArticleId()));
    }

    @PostMapping("/addNote")
    public ResponseEntity<?> addNote(@RequestBody NoteDto noteDto){

        if(noteDto == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: NoteDto not present"));
        if(noteDto.mail.isEmpty())
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Author mail not present"));
        if(noteDto.articleId == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Article id not present"));
        if(noteDto.note == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Note is not present"));

        articleService.updateNote(noteDto);

        return ResponseEntity.ok(articleService.getArticleById(noteDto.articleId));
    }
}
