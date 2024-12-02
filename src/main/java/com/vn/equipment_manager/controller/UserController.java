package com.vn.equipment_manager.controller;

import com.vn.equipment_manager.model.UserDto;
import com.vn.equipment_manager.model.request.RegisterDto;
import com.vn.equipment_manager.service.AuthService;
import com.vn.equipment_manager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto users = userService.getUserById(id);
        return ResponseEntity.ok(users);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody RegisterDto request) {
        authService.register(request);
        return ResponseEntity.ok("Create User successfully!");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RegisterDto request) {
        authService.update(id, request);
        return ResponseEntity.ok("Update User successfully!");
    }
}
