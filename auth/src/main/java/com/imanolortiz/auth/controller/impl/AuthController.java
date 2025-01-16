package com.imanolortiz.auth.controller.impl;

import com.imanolortiz.auth.commons.dtos.AuthResponseDto;
import com.imanolortiz.auth.commons.dtos.LoginRequestDto;
import com.imanolortiz.auth.commons.dtos.UserRequest;
import com.imanolortiz.auth.controller.AuthApi;
import com.imanolortiz.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @Override
    public ResponseEntity<AuthResponseDto> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }

    @Override
    public ResponseEntity<AuthResponseDto> loginUser(LoginRequestDto loginRequest) {
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }


}

