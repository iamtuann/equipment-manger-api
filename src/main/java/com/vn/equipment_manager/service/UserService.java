package com.vn.equipment_manager.service;

import com.vn.equipment_manager.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);
}
