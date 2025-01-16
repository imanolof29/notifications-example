package com.imanolortiz.auth.controller;

import com.imanolortiz.auth.commons.constants.ApiPathConstants;
import com.imanolortiz.auth.commons.dtos.AuthResponseDto;
import com.imanolortiz.auth.commons.dtos.LoginRequestDto;
import com.imanolortiz.auth.commons.dtos.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {

    @PostMapping(value = "/register")
    ResponseEntity<AuthResponseDto> createUser(
            @RequestBody @Valid UserRequest userRequest
    );

    @PostMapping(value = "/login")
    ResponseEntity<AuthResponseDto> loginUser(
            @RequestBody @Valid LoginRequestDto loginRequest
    );


}
