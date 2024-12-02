package com.vn.equipment_manager.service.impl;

import com.vn.equipment_manager.entity.Role;
import com.vn.equipment_manager.entity.User;
import com.vn.equipment_manager.exception.APIException;
import com.vn.equipment_manager.model.AuthUserResponse;
import com.vn.equipment_manager.model.request.LoginDto;
import com.vn.equipment_manager.model.request.RegisterDto;
import com.vn.equipment_manager.repository.RoleRepository;
import com.vn.equipment_manager.repository.UserRepository;
import com.vn.equipment_manager.security.JwtTokenProvider;
import com.vn.equipment_manager.security.UserDetailsImpl;
import com.vn.equipment_manager.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthUserResponse login(LoginDto loginDto) {
        if (!userRepository.existsByUsername(loginDto.getUsername())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Username is not registered");
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);
            return new AuthUserResponse(authentication, token);
        } catch (BadCredentialsException ex) {
            throw new APIException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    @Override
    public User register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        for (Long id : registerDto.getRoleIds()) {
            roles.add(roleRepository.findRoleById(id));
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
