package com.imanolortiz.users.controller.impl;

import com.imanolortiz.users.commons.dtos.UpdateUserDto;
import com.imanolortiz.users.commons.dtos.UserDto;
import com.imanolortiz.users.controller.UserApi;
import com.imanolortiz.users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    private UserService userService;

    UserController(UserService userService){
        this.userService= userService;
    }

    @Override
    public ResponseEntity<UserDto> getUser(Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateUser(Long userId, UpdateUserDto dto) {
        userService.updateUser(userId, dto);
        return ResponseEntity.noContent().build();
    }
}
