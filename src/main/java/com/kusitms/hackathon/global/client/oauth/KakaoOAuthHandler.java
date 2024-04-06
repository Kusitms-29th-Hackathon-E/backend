package com.kusitms.hackathon.global.client.oauth;

import com.kusitms.hackathon.domain.user.domain.oauth.OAuthHandler;
import com.kusitms.hackathon.domain.user.domain.oauth.OAuthProcessingData;
import com.kusitms.hackathon.domain.user.domain.oauth.OAuthTransactionResult;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Slf4j
@Component
public class KakaoOAuthHandler implements OAuthHandler {

    private final WebClient webClient = WebClient.create("https://kapi.kakao.com");

    @Override
    public OAuthTransactionResult retrieveOAuthDetail(OAuthProcessingData request) {
        KakaoUserInfo kakaoUserInfo = getKakaoUserInfo(request.accessToken());
        log.info("kakaoUserInfo: {}", kakaoUserInfo);
        return new OAuthTransactionResult( kakaoUserInfo.id);
    }


    private KakaoUserInfo getKakaoUserInfo(String accessToken) {
        return webClient.get()
                .uri("/v2/user/me")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(KakaoUserInfo.class)
                .block();
    }

    @Getter
    @ToString
    private static class KakaoUserInfo {
        private String id;
    }
}
