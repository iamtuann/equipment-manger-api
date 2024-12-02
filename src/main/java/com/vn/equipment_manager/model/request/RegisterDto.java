package com.vn.equipment_manager.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Set<Long> roleIds;
}
