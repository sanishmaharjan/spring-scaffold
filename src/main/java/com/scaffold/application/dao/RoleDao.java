package com.scaffold.application.dao;

import com.scaffold.application.constant.UserRole;
import com.scaffold.application.model.Role;

import java.util.List;

public interface RoleDao {
    /**
     * Find Role by Id
     *
     * @param id {@link Integer}
     * @return {@link Role}
     */
    Role findById(Integer id);

    /**
     * Find Role by UserRole
     *
     * @param userRole {@link UserRole}
     * @return {@link Role}
     */
    Role findByUserRole(UserRole userRole);

    /**
     * Return all Roles
     *
     * @return {@link List<Role>}
     */
    List<Role> findAll();
}
