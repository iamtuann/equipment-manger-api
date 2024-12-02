package com.vn.equipment_manager.service.impl;

import com.vn.equipment_manager.entity.User;
import com.vn.equipment_manager.enums.EUserStatus;
import com.vn.equipment_manager.exception.ResourceNotFoundException;
import com.vn.equipment_manager.model.UserDto;
import com.vn.equipment_manager.repository.UserRepository;
import com.vn.equipment_manager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAllByStatus(EUserStatus.ACTIVE.getValue());
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return new UserDto(user);
    }
}
