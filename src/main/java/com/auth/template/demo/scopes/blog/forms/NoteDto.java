package com.auth.template.demo.scopes.blog.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NoteDto {

    @NotBlank
    public Long articleId;


    @NotBlank
    public String mail;

    @NotBlank
    public Long note;


    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getNote() {
        return note;
    }

    public void setNote(Long note) {
        this.note = note;
    }


    @Override
    public String toString() {
        return "NoteForm{" +
                "articleId=" + articleId +
                ", mail='" + mail + '\'' +
                ", note=" + note +
                '}';
    }
}
