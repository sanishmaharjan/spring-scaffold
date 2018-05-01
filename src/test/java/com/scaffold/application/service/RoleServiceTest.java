package com.scaffold.application.service;

import com.scaffold.application.constant.UserRole;
import com.scaffold.application.model.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = "classpath:TestingApplicationContex.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleServiceTest {

    @Autowired
    RoleService roleService;

    @Test
    public void shouldReturnRoleById() throws Exception {
        Role role = roleService.getRoleById(2);

        Assert.assertNotNull(role);
        Assert.assertEquals("admin", role.getRole());
    }

    @Test
    public void shouldReturnRoleByUserRole() throws Exception {
        Role role = roleService.getRoleByUserRole(UserRole.USER);

        Assert.assertNotNull(role);
        Assert.assertEquals("user", role.getRole());
    }

    @Test
    public void shouldReturnAllRoles() throws Exception {
        List<Role> roles = roleService.getAllRole();

        Assert.assertEquals(2, roles.size());
        Assert.assertEquals("user", roles.get(0).getRole());
        Assert.assertEquals("admin", roles.get(1).getRole());
    }
}
