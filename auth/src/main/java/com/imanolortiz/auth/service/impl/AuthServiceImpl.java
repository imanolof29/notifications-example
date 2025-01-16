package com.imanolortiz.auth.service.impl;

import com.imanolortiz.auth.commons.dtos.AuthResponseDto;
import com.imanolortiz.auth.commons.dtos.LoginRequestDto;
import com.imanolortiz.auth.commons.dtos.UserRequest;
import com.imanolortiz.auth.commons.entities.UserModel;
import com.imanolortiz.auth.repository.UserRepository;
import com.imanolortiz.auth.service.AuthService;
import com.imanolortiz.auth.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDto createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getId()))
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }

    @Override
    public AuthResponseDto loginUser(LoginRequestDto loginRequest) {
        return Optional.of(loginRequest.getEmail())
                .map(userRepository::findByEmail)
                .filter(user -> passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword()))
                .map(user -> jwtService.generateToken(user.get().getId()))
                .orElseThrow(() -> new RuntimeException("Login error"));
    }


    private UserModel mapToEntity(UserRequest userRequest){
        return UserModel
                .builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName())
                .role("USER")
                .build();
    }
}

