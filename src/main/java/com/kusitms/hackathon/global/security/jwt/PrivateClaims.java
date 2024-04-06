package com.kusitms.hackathon.global.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;

import java.util.Map;

@RequiredArgsConstructor
public class PrivateClaims {
    private final TokenType tokenType;
    private final UserClaims userClaims;



    public static Map<String, Class<?>> retrieveClaimsMap(){
        return Map.of(
                ClaimsConsts.TOKEN_TYPE, TokenType.class,
                ClaimsConsts.USER_CLAIMS, UserClaims.class
        );
    }

    public Map<String, Object> convertClaimsMap(){
        return Map.of(
                ClaimsConsts.TOKEN_TYPE, this.tokenType,
                ClaimsConsts.USER_CLAIMS, this.userClaims
        );
    }

    @Getter
    public static class UserClaims{
        private final Long userId;

        public UserClaims(Long userId){
            this.userId = userId;
        }

        public PrivateClaims convertToPrivateClaims(TokenType tokenType){
            return new PrivateClaims(tokenType, this);
        }
    }
}
