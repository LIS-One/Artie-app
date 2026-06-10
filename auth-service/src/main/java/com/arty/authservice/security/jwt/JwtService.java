package com.arty.authservice.security.jwt;

import com.arty.authservice.dto.JwtAuthenticationDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtService {
    @Value("")
    private String jwtSecret;

    public JwtAuthenticationDto generateAuthToken(String email) throws Exception {
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setToken(generateJwtToken(email));
        jwtDto.setRefreshToken(generateRefreshToken(email));
        return jwtDto;
    }
    public JwtAuthenticationDto refreshBaseToken(String refreshToken, String email) throws Exception {
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setToken(generateJwtToken(email));
        jwtDto.setRefreshToken(refreshToken);
        return jwtDto;
    }

//    public String getEmailFromToken(String token) throws Exception {
//        Claims claims = Jwts.parser()
//                .verifyWith()
//                .build()
//    }
    private String generateJwtToken(String email) throws Exception {
        Date date = Date.from(LocalDateTime.now().plusSeconds(60).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(email)
                .expiration(date)
                .signWith(getSignInKey(), Jwts.SIG.RS256)
                .compact();
    }
    private String generateRefreshToken(String email) throws Exception {
        Date date = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(email)
                .expiration(date)
                .signWith(getSignInKey(), Jwts.SIG.RS256)
                .compact();
    }

    private PrivateKey getSignInKey() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }
}
