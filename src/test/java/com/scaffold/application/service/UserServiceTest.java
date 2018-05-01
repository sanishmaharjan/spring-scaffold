package com.scaffold.application.service;

import com.scaffold.application.constant.UserRole;
import com.scaffold.application.dao.PersonDao;
import com.scaffold.application.dao.RoleDao;
import com.scaffold.application.dao.UserDao;
import com.scaffold.application.model.Person;
import com.scaffold.application.model.Role;
import com.scaffold.application.model.User;
import com.scaffold.application.utilities.SHA512;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration(locations = "classpath:TestingApplicationContex.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Autowired
    PersonDao personDao;

    @Autowired
    RoleDao roleDao;

    @Test
    @Transactional
    @Rollback
    public void shouldSaveNewUser() throws Exception {
        Person person = personDao.findById(3);
        Role role = roleDao.findByUserRole(UserRole.USER);
        String salt = SHA512.generateSalt();
        String hashPassword = SHA512.hashPassword("password", salt);
        User user = new User(person, role, "sabimhz", hashPassword, salt, true);

        userService.saveUser(user);

        Assert.assertNotNull(user.getId());
        Assert.assertFalse(user.getDeleted());
        Assert.assertNotNull(user.getPerson());
        Assert.assertEquals("Sabina", person.getFirstName());
        Assert.assertEquals("user", user.getRole().getRole());
    }

    @Test
    @Transactional
    @Rollback
    public void shouldUpdateExistingUser() throws Exception {
        User user = userDao.findById(2);
        String hashPassword = SHA512.hashPassword("newPassword", user.getSalt());
        user.setPassword(hashPassword);

        userService.saveUser(user);

        Assert.assertEquals("c39cf80ffc7409446dff7daa77c4feff0584d0c7e898a767ff1766ccb83fd7de91d77ad2d84004ee314cb24deec0e4fbadf70eb264ce3c20a059b4e88b2ca503", user.getPassword());
    }

    @Test
    @Transactional
    @Rollback
    public void shouldSaveNewUserWithUserNameAndPassword() throws Exception {
        Person person = personDao.findById(3);
        Role role = roleDao.findByUserRole(UserRole.USER);
        User user = userService.saveUser(null, person, role, "sabimhz", "password");

        Assert.assertNotNull(user.getId());
        Assert.assertFalse(user.getDeleted());
        Assert.assertNotNull(user.getPerson());
        Assert.assertEquals("Sabina", person.getFirstName());
        Assert.assertEquals("user", user.getRole().getRole());
    }

    @Test
    public void shouldReturnUserById() throws Exception {
        User user = userService.getUserById(1);

        Assert.assertNotNull(user);
        Assert.assertEquals(1, user.getId());
    }

    @Test
    @Transactional
    public void shouldReturnUserByPerson() throws Exception {
        Person person = personDao.findById(2);
        User user = userService.getUserByPerson(person);

        Assert.assertNotNull(user);
        Assert.assertEquals(2, user.getId());
        Assert.assertEquals(2, user.getPerson().getId());
    }

    @Test
    @Transactional
    public void shouldReturnUserByPersonId() throws Exception {
        User user = userService.getUserByPersonId(2);

        Assert.assertNotNull(user);
        Assert.assertEquals(2, user.getId());
        Assert.assertEquals(2, user.getPerson().getId());
    }

    @Test
    public void shouldReturnUserByUserName() throws Exception {
        User user = userService.getUserByUserName("mjsanish");

        Assert.assertNotNull(user);
        Assert.assertEquals(1, user.getId());
        Assert.assertEquals("mjsanish", user.getUserName());
    }

    @Test
    public void shouldReturnIsUserActive() throws Exception {
        Assert.assertTrue(userService.isActiveUser("mjsanish"));
        Assert.assertTrue(userService.isActiveUser("mannu"));
    }

    @Test
    public void shouldReturnAllUsers() throws Exception {
        List<User> users = userService.getAllUsers();

        Assert.assertEquals(2, users.size());
        Assert.assertEquals(1, users.get(0).getId());
        Assert.assertEquals(2, users.get(1).getId());
        Assert.assertFalse(users.get(0).getDeleted());
        Assert.assertFalse(users.get(1).getDeleted());
    }

    @Test
    public void shouldReturnAllUsersByUserRole() throws Exception {
        List<User> users = userService.getAllUsersByRole(UserRole.USER);

        Assert.assertEquals(1, users.size());
        Assert.assertEquals(2, users.get(0).getId());
        Assert.assertEquals("mannu", users.get(0).getUserName());
        Assert.assertFalse(users.get(0).getDeleted());
    }

    @Test
    public void shouldValidUserCredential() throws Exception {
        Assert.assertTrue(userService.isValidUserCredential("mjsanish", "password"));
        Assert.assertFalse(userService.isValidUserCredential("mjsanish", "wrongPassword"));
    }
}
