package com.vn.equipment_manager.model;

import com.vn.equipment_manager.entity.Role;
import com.vn.equipment_manager.entity.User;
import com.vn.equipment_manager.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthUserResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String token;
    private List<String> roles;

    public AuthUserResponse(Authentication authentication, String token, Set<Role> roles) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        this.id = userDetails.getId();
        this.username = userDetails.getUsername();
        this.firstName = userDetails.getFirstName();
        this.lastName = userDetails.getLastName();
        this.roles = roles.stream().map(Role::getDisplayName).toList();
        this.token = token;
    }

    public AuthUserResponse(User user, String token) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        this.token = token;
    }
}
