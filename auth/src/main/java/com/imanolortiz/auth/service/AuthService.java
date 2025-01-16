package com.imanolortiz.auth.service;

import com.imanolortiz.auth.commons.dtos.AuthResponseDto;
import com.imanolortiz.auth.commons.dtos.LoginRequestDto;
import com.imanolortiz.auth.commons.dtos.UserRequest;

public interface AuthService {

    AuthResponseDto createUser(UserRequest userRequest);

    AuthResponseDto loginUser(LoginRequestDto loginRequestDto);

}
