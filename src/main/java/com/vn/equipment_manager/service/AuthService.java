package com.vn.equipment_manager.service;

import com.vn.equipment_manager.entity.User;
import com.vn.equipment_manager.model.AuthUserResponse;
import com.vn.equipment_manager.model.request.LoginDto;
import com.vn.equipment_manager.model.request.RegisterDto;

public interface AuthService {
    AuthUserResponse login(LoginDto loginDto);

    User register(RegisterDto registerDto);
}
