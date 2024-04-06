package com.kusitms.hackathon.domain.user.presentation;

import com.kusitms.hackathon.domain.user.application.service.UserService;
import com.kusitms.hackathon.domain.user.domain.Provider;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/oauth/{provider}")
    public void processingOAuth(
            @Parameter(description = """
                    Header: OAuth-AccessToken
                                        
                    description: OAuth Access Token, Header에 OAuth-AccessToken으로 값을 전달
                            """
            ) @RequestHeader("OAuth-AccessToken") String accessToken,
            @Parameter(description = """
                    PathVariable: Provider
                                        
                    description: 소셜 로그인 플랫폼
                    """) @PathVariable Provider provider
    ) {
        userService.oAuthExecuting(accessToken, provider);
    }
}
