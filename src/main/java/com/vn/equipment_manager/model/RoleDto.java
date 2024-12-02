package com.vn.equipment_manager.model;

import com.vn.equipment_manager.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDto {
    private Long id;
    private String displayName;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.displayName = role.getDisplayName();
    }
}
