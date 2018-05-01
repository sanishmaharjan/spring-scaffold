package com.scaffold.application.dao;

import com.scaffold.application.constant.UserRole;
import com.scaffold.application.model.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = "classpath:TestingApplicationContex.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleDaoTest {

    @Autowired
    RoleDao roleDao;

    @Before
    public void setupData() throws Exception {

    }

    @Test
    public void shouldReturnAllRoles() throws Exception {
        List<Role> roles = roleDao.findAll();
        Assert.assertEquals(2, roles.size());
        Assert.assertEquals("user", roles.get(0).getRole());
        Assert.assertEquals("admin", roles.get(1).getRole());
    }

    @Test
    public void shouldRoleById() throws Exception {
        Role role = roleDao.findById(2);
        Assert.assertNotNull(role);
        Assert.assertEquals("admin", role.getRole());
    }

    @Test
    public void shouldRoleByUserRole() {
        Role role = roleDao.findByUserRole(UserRole.USER);
        Assert.assertNotNull(role);
        Assert.assertEquals("user", role.getRole());
    }
}
