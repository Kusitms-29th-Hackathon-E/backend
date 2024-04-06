package com.kusitms.hackathon.global.security.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JWTUtil {
    private final JwtProperties jwtProperties;

    public SecretKey generateSecretKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
    }

    public Long getUserId(String token) {

        return Jwts.parser().verifyWith(generateSecretKey()).build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id", Long.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(generateSecretKey()).build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
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
}
