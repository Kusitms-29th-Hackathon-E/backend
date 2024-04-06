package com.kusitms.hackathon.domain.user.domain.oauth;

public record OAuthTransactionResult(
        String email,
        String sub
) {
}
