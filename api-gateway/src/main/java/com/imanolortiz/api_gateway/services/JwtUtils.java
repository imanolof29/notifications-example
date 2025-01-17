package com.imanolortiz.api_gateway.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtils {

    private final String secretKey = "isugv9egrcieubviugewfivbckwefbviuwebrvywgcuybiugeyucgiqeguyiewgicugeuwygcyuevwuycvuyewvfcuyfgqwuyefdguqwyefgduyqwfgeiuywqhecofibqwiuegiuyqwegfuiqwegfiugqweiufygq2uyefuyqwrgfiuyqwegvfuewvgfuy";

    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isExpired(String token){
        try{
            return getClaims(token).getExpiration().before(new Date());
        }catch(Exception e){
            return true;
        }
    }

    public Integer extractUserId(String token){
        try{
            return Integer.parseInt(getClaims(token).getSubject());
        }catch(Exception e){
            return null;
        }
    }

}
