package com.kusitms.hackathon.domain.user.application.service;

import com.kusitms.hackathon.domain.user.domain.Provider;
import com.kusitms.hackathon.domain.user.domain.User;
import com.kusitms.hackathon.domain.user.domain.oauth.OAuthHandler;
import com.kusitms.hackathon.domain.user.domain.oauth.OAuthProcessingData;
import com.kusitms.hackathon.domain.user.domain.oauth.OAuthTransactionResult;
import com.kusitms.hackathon.domain.user.domain.service.UserFactory;
import com.kusitms.hackathon.global.security.jwt.JwtUtil;
import com.kusitms.hackathon.global.security.jwt.PrivateClaims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final Map<Provider, OAuthHandler> oAuthHandlerMap;
    private final UserFactory userFactory;
    private final JwtUtil jwtUtil;

    public void oAuthExecuting(String accessToken, Provider provider){
        final OAuthHandler oAuthHandler = oAuthHandlerMap.get(provider);
        OAuthTransactionResult oAuthTransactionResult = oAuthHandler.retrieveOAuthDetail(new OAuthProcessingData(accessToken));
        User user = userFactory.createUser(oAuthTransactionResult.sub());
        // jwt user 정보를 통해 생성

        // jwt 만드는데, userId를 가지고 있는 토큰을 만들어주면 됩
        jwtUtil.createAccessToken(new PrivateClaims.UserClaims(user.getId()));
    }

    public void deleteUser(){

    }
}
