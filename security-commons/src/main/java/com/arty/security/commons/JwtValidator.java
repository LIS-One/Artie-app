package com.arty.security.commons;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class JwtValidator {
    @Value("${jwt.public-key}")
    private String publicKeyBase64;

    private PublicKey publicKey;


    public Claims getClaimsFromToken(String token) {
        Claims claims;
        claims = Jwts.parser().verifyWith(publicKey).build().parseSignedClaims(token).getPayload();
        return claims;
    }
    @PostConstruct
    void init() throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(
                new X509EncodedKeySpec(Decoders.BASE64.decode(publicKeyBase64)));
    }
}
