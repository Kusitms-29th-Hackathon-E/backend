package com.kusitms.hackathon.global.client.oauth;

import com.kusitms.hackathon.domain.user.domain.oauth.OAuthHandler;
import com.kusitms.hackathon.domain.user.domain.oauth.OAuthProcessingData;
import com.kusitms.hackathon.domain.user.domain.oauth.OAuthTransactionResult;
import org.springframework.stereotype.Component;

@Component
public class NaverOAuthHandler implements OAuthHandler {
    @Override
    public OAuthTransactionResult retrieveOAuthDetail(OAuthProcessingData request) {
        return null;
    }
}
