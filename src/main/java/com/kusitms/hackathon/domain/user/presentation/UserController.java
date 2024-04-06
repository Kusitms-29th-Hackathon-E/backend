package com.kusitms.hackathon.domain.user.presentation;

import com.kusitms.hackathon.domain.user.application.service.UserService;
import com.kusitms.hackathon.domain.user.domain.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private UserService userService;


    @PostMapping("/oauth/{provider}")
    public void processingOAuth(
            @RequestHeader("OAuth-AccessToken") String accessToken,
            @PathVariable Provider provider
    ) {
        userService.oAuthExecuting(accessToken, provider);
    }
}
