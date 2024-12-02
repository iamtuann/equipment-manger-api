package com.vn.equipment_manager.model;

import com.vn.equipment_manager.entity.User;
import com.vn.equipment_manager.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public AuthUserResponse(UserDetailsImpl userDetails, String token) {
        this.id = userDetails.getId();
        this.username = userDetails.getUsername();
        this.firstName = userDetails.getFirstName();
        this.lastName = userDetails.getLastName();
        this.token = token;
    }

    public AuthUserResponse(User user, String token) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.token = token;
    }
}
