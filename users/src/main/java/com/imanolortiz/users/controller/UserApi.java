package com.imanolortiz.users.controller;

import com.imanolortiz.users.commons.constants.ApiPathConstants;
import com.imanolortiz.users.commons.dtos.UpdateUserDto;
import com.imanolortiz.users.commons.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.USER_ROUTE)
public interface UserApi {

    @GetMapping(value = "/user-info")
    ResponseEntity<UserDto> getUser(@RequestAttribute("X-User-Id") Long userId);

    @DeleteMapping(value = "/delete")
    ResponseEntity<Void> deleteUser(@RequestAttribute("X-User-Id") Long userId);

    @PutMapping(value = "/update")
    ResponseEntity<Void> updateUser(@RequestAttribute("X-User-Id") Long userId, @RequestBody UpdateUserDto dto);

}

