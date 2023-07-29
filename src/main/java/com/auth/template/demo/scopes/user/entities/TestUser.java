package com.auth.template.demo.scopes.user.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum TestUser {

    USER_ONE("john" , "Doe" , "john@doe.com" ,
            "johnny" , "johnDoe007",
            new HashSet<UserRole>(Arrays.asList(UserRole.USER , UserRole.ADMIN , UserRole.MODERATOR))),

    USER_TWO("Michael" , "Jackson" , "mikejack@mail.com" ,
            "Mike237" , "mikeJack0",
            new HashSet<UserRole>(Arrays.asList(UserRole.USER , UserRole.MODERATOR))),
    USER_THREE("Donald" , "Williamson" , "dony@mail.com" ,
                     "donny023" , "donny023+",
                     new HashSet<UserRole>(Arrays.asList(UserRole.USER , UserRole.MODERATOR)));


    public final String firstname;
    public final String lastname;
    public final String email;
    public final String nickname;
    public final String password;
    public final Set<UserRole> grantedAuthorities;
    TestUser(String firstname , String lastname , String email , String nickname ,String password ,
             Set<UserRole> grantedAuthorities){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;

    }
}