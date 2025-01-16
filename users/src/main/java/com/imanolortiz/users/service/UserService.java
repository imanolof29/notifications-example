package com.imanolortiz.users.service;

import com.imanolortiz.users.commons.dtos.UpdateUserDto;
import com.imanolortiz.users.commons.dtos.UserDto;

public interface UserService {
    UserDto getUser(Long id);
    void updateUser(Long id, UpdateUserDto dto);
    void deleteUser(Long id);
}
