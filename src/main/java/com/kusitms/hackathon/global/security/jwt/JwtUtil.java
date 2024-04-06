package com.kusitms.hackathon.global.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;

    public SecretKey generateSecretKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
    }

    public String createAccessToken(PrivateClaims.UserClaims userClaims){
        return createJwt(userClaims, TokenType.ACCESS_TOKEN, jwtProperties.getAccessTokenExpiredTime());
    }

    private String createJwt(PrivateClaims.UserClaims userClaims, TokenType tokenType, Long expiredMs) {
        final Date now = new Date();
        return Jwts.builder()
                .claims(userClaims.convertToPrivateClaims(tokenType).convertClaimsMap())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiredMs))
                .signWith(generateSecretKey())
                .compact();
    }

    //토큰이 유효한지 verifyWith로 검증하고 토큰 내용 추출
    public Jws<Claims> parseJwt(String token) {
        return Jwts.parser().verifyWith(generateSecretKey()).build()
                .parseSignedClaims(token);
    }

    public PrivateClaims.UserClaims extractUserClaim(String token) {
        return parseJwt(token)
                .getPayload()
                .get(ClaimsConsts.USER_CLAIMS, PrivateClaims.UserClaims.class);
    }
}
