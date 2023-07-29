package com.auth.template.demo.scopes.blog.entities;

public enum TestArticle {

    ARTICLE_ONE( "The 48 rules of Power" , "This book has de 48 Low of Power",
            "img/post-bg.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
            " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer " +
            "took a galley of type and scrambled it to make a type specimen book. It has survived not only five" +
            " centuries, but also the leap into electronic typesetting, remaining essentially unchanged." , "Michael Mboni"),
    ARTICLE_TWO( "The 50 Shade of Black" , "50 nuances de noir",
            "img/post-bg.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
            " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer " +
            "took a galley of type and scrambled it to make a type specimen book. It has survived not only five" +
            " centuries, but also the leap into electronic typesetting, remaining essentially unchanged." , "Michael Mboni"),
    ARTICLE_THREE( "The Equalizer" , "This a good film",
            "img/post-bg.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
            " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer " +
            "took a galley of type and scrambled it to make a type specimen book. It has survived not only five" +
            " centuries, but also the leap into electronic typesetting, remaining essentially unchanged." , "The Moderator");
    ;

    public String title ;
    public String subtitle;
    public String image;
    public String text;
    public String author;

    TestArticle(String title , String subtitle, String image, String text, String author){
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.text= text;
        this.author = author;
    }
}