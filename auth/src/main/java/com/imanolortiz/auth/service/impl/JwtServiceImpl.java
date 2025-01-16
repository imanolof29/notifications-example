package com.imanolortiz.auth.service.impl;

import com.imanolortiz.auth.commons.dtos.AuthResponseDto;
import com.imanolortiz.auth.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final String tokenSecret;

    JwtServiceImpl(@Value("${jwt.secret}") String tokenSecret){
        this.tokenSecret = tokenSecret;
    }

    @Override
    public AuthResponseDto generateToken(Long id) {
        Date expirationDate = new Date(Long.MAX_VALUE);
        String token = Jwts.builder()
                .setSubject(String.valueOf(id))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, this.tokenSecret)
                .compact();
        return AuthResponseDto.builder().accessToken(token).build();
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.tokenSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isExpired(String token) {
        try{
            return getClaims(token).getExpiration().before(new Date());
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Integer extractUserId(String token) {
        try{
            return Integer.parseInt(getClaims(token).getSubject());
        }catch(Exception e){
            return null;
        }
    }
}

