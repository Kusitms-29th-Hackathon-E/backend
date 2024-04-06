package com.kusitms.hackathon.domain.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Long id;
    private String email;
    private String sub;


    public static User createNewUser(String email, String sub){
        return new User(null, email, sub);
    }
}
