package com.scaffold.application.service;

import com.scaffold.application.constant.UserRole;
import com.scaffold.application.model.Role;

import java.util.List;

public interface RoleService {
    /**
     * Get Role by Id
     *
     * @param id {@link Integer}
     * @return {@link Role}
     */
    Role getRoleById(Integer id);

    /**
     * Get Role by userRole
     *
     * @param userRole {@link UserRole}
     * @return {@link Role}
     */
    Role getRoleByUserRole(UserRole userRole);

    /**
     * Return all roles
     *
     * @return {@link List<Role>}
     */
    List<Role> getAllRole();
}
