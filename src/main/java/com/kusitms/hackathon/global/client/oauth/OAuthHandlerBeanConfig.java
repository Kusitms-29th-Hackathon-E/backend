package com.kusitms.hackathon.global.client.oauth;

import com.kusitms.hackathon.domain.user.domain.Provider;
import com.kusitms.hackathon.domain.user.domain.oauth.OAuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import javax.sound.sampled.Port;
import java.util.Map;

@Configurable
@RequiredArgsConstructor
public class OAuthHandlerBeanConfig {
    private final GoogleOAuthHandler googleOAuthHandler;
    private final KakaoOAuthHandler kakaoOAuthHandler;
    private final NaverOAuthHandler naverOAuthHandler;


    @Bean
    public Map<Provider, OAuthHandler> oAuthHandlerMap(){
        return Map.of(
                Provider.GOOGLE, googleOAuthHandler,
                Provider.NAVER, naverOAuthHandler,
                Provider.KAKAO, kakaoOAuthHandler
        );
    }
}
