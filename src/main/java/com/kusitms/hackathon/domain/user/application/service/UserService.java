package com.kusitms.hackathon.domain.user.application.service;

import com.kusitms.hackathon.domain.user.domain.Provider;
import com.kusitms.hackathon.domain.user.domain.User;
import com.kusitms.hackathon.domain.user.domain.oauth.OAuthHandler;
import com.kusitms.hackathon.domain.user.domain.oauth.OAuthProcessingData;
import com.kusitms.hackathon.domain.user.domain.oauth.OAuthTransactionResult;
import com.kusitms.hackathon.domain.user.domain.service.UserAppender;
import com.kusitms.hackathon.domain.user.domain.service.UserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Map<Provider, OAuthHandler> oAuthHandlerMap;
    private final UserFactory userFactory;

    public void oAuthExecuting(String accessToken, Provider provider){
        final OAuthHandler oAuthHandler = oAuthHandlerMap.get(provider);
        OAuthTransactionResult oAuthTransactionResult = oAuthHandler.retrieveOAuthDetail(new OAuthProcessingData(accessToken));
        User user = userFactory.createUser(oAuthTransactionResult.sub(), oAuthTransactionResult.email());
        // jwt user 정보를 통해 생성
    }
}
