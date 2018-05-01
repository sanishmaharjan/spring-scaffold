package com.scaffold.application.service.impl;

import com.scaffold.application.constant.UserRole;
import com.scaffold.application.dao.RoleDao;
import com.scaffold.application.model.Role;
import com.scaffold.application.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleDao.findById(id);
    }

    @Override
    public Role getRoleByUserRole(UserRole userRole) {
        return roleDao.findByUserRole(userRole);
    }

    @Override
    public List<Role> getAllRole() {
        return roleDao.findAll();
    }
}
