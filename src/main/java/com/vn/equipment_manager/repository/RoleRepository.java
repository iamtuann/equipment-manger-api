package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
}
