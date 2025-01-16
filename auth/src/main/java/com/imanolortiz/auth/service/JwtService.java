package com.imanolortiz.auth.service;

import com.imanolortiz.auth.commons.dtos.AuthResponseDto;
import io.jsonwebtoken.Claims;

public interface JwtService {
    AuthResponseDto generateToken(Long id);
    Claims getClaims(String token);
    boolean isExpired(String token);
    Integer extractUserId(String token);
}
