package com.kusitms.hackathon.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {


    @PostMapping("/oauth")
    public void processingOAuth(
        @RequestHeader("OAuth-AccessToken") String accessToken
    ){

    }
}
