package com.auth.template.demo.scopes.blog.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String authorName;

    @Column
    private Long articleId;

    @Column(length=10485760)
    private String comment;

    @SuppressWarnings("unused")
    public Comment() { }

    public Comment(String authorName, Long articleId ,String comment) {
        this.authorName = authorName;
        this.articleId = articleId;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", articleId=" + articleId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
