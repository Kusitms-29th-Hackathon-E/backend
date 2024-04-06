package com.kusitms.hackathon.domain.user.domain.oauth;

public interface OAuthHandler {
    OAuthTransactionResult retrieveOAuthDetail(OAuthProcessingData request);
}
