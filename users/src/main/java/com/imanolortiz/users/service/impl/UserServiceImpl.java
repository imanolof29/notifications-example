package com.imanolortiz.users.service.impl;

import com.imanolortiz.users.commons.dtos.UpdateUserDto;
import com.imanolortiz.users.commons.dtos.UserDto;
import com.imanolortiz.users.commons.entities.UserModel;
import com.imanolortiz.users.repository.UserRepository;
import com.imanolortiz.users.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getUser(Long id) {
        return Optional.of(id)
                .flatMap(userRepository::findById)
                .map(this::mapToEntity)
                .orElseThrow(() -> new RuntimeException("Error retrieving user with id "+id));
    }

    @Override
    public void updateUser(Long id, UpdateUserDto dto) {
        Optional.of(id)
                .map(this::getUserById)
                .map(user -> updateUserFields(user, dto))
                .map(userRepository::save)
                .orElseThrow(() -> new RuntimeException("Error updating user with id "+id));
    }

    @Override
    public void deleteUser(Long id) {
        Optional.of(id)
                .map(this::getUserById)
                .ifPresent(userRepository::delete);
    }

    private UserModel updateUserFields(UserModel user, UpdateUserDto dto){
        user.setName(dto.getName());
        return user;
    }

    private UserModel getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error retrieving user with ID "+id));
    }

    private UserDto mapToEntity(UserModel user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}

