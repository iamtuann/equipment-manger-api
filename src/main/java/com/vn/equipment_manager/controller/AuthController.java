package com.vn.equipment_manager.controller;

import com.vn.equipment_manager.entity.User;
import com.vn.equipment_manager.model.AuthUserResponse;
import com.vn.equipment_manager.model.request.LoginDto;
import com.vn.equipment_manager.model.request.RegisterDto;
import com.vn.equipment_manager.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<AuthUserResponse> login(@RequestBody LoginDto loginDto) {
        AuthUserResponse userResponse = authService.login(loginDto);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }
}
