package com.auth.template.demo.scopes.general;


import com.auth.template.demo.scopes.blog.entities.Article;
import com.auth.template.demo.scopes.blog.entities.Comment;
import com.auth.template.demo.scopes.blog.entities.TestArticle;
import com.auth.template.demo.scopes.blog.services.ArticleService;
import com.auth.template.demo.scopes.user.entities.TestUser;
import com.auth.template.demo.scopes.user.entities.User;

import com.auth.template.demo.scopes.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    private static final Logger LOG = LoggerFactory.getLogger(Initializer.class);

    public UserService userService;
    public ArticleService articleService;

    @Autowired
    public Initializer(UserService userService,
                       ArticleService articleService,
                       final @Value("${spring.jpa.hibernate.ddl-auto}") String database ,
                       final @Value("${spring.profiles.active}") String profile){

        this.userService = userService;
        this.articleService = articleService;
        if(setupDemoUsers(database)){
            setupTestUsers();
            setupDemoArticles();
        }
    }

    private void setupTestUsers() {
        LOG.info("Creating default Test Users.");
        User user ;
        for (TestUser testUser : TestUser.values()) {
            user = new User(testUser.firstname , testUser.lastname , testUser.email, testUser.nickname,
                    testUser.password , testUser.grantedAuthorities);

            userService.generateAndSaveNewValidationTokenForUser(user);
            userService.rehashPassword(testUser.password , user);
        }
    }

    private void setupDemoArticles() {
        LOG.info("Creating default Test Articles.");
        Article article ;

        for (TestArticle testArticle : TestArticle.values()) {
            article = new Article(testArticle.title, testArticle.subtitle, testArticle.image, testArticle.text, testArticle.author);
            articleService.saveArticle(article);
        }

        Comment comment = new Comment("Anderson", 4L , "HALLO");
        Comment comment2 = new Comment("Anderson2", 4L , "HALLO2");
        articleService.addComment(comment);
        articleService.addComment(comment2);
    }

    private boolean setupDemoUsers(String database){
        return "create".equals(database);
    }

}
