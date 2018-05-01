package com.scaffold.application.dao;

import com.scaffold.application.constant.UserRole;
import com.scaffold.application.model.Person;
import com.scaffold.application.model.Role;
import com.scaffold.application.model.User;
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
public class UserDaoTest {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    PersonDao personDao;

    @Test
    @Transactional
    @Rollback(value = true)
    public void shouldSaveNewUser() throws Exception {
        Person person = new Person("Ram", "", "Thapa", "Jhapa", "ramcha@gmail.com", "Male", false);
        personDao.save(person);
        Role role = roleDao.findById(1);
        User user = new User(person, role, "ramcha",
                "837ad70586cca7bc75d960d127a367679d6108ef9009be287059df015775c1cf3d361f84c59eacf7c09e0e87d671350d5797788b33bcb8670fd4d097638d94b6",
                "46bf7ca3e147e56cffd01f947abb8e3c77286b1b6a1f3a7398f325fe02e9dc0ceba63ea3a795c4444cd9982627c6837ac771d7e064a2d008aa8312d8338373c0a2109c7914b34058c80ee145d351c8fdbe41ca19f9dc11b4e58d732d38d7bcee4a1a2f3c703ee560d64a6c96ab6a12564451fc2267d638958f71bedbd0d49e1747180ffb9a64ae30dcd2d6a3e2572db606ff5d8881144aaf59080f630e6d6fa2a83374afbc5b952cc6ede5a3022c86d7912bdfb33a696dbc3f15e21e417d7eef373ba69bd5702918f7f2295c78b0b840401675201af07db7daced692e35feb40dccaf4426e46d0367f253f45f7f23fabe2e78531bfc8cd0fbd5ed72bbfc07daf",
                true);
        userDao.save(user);

        Assert.assertNotNull(user.getId());
        Assert.assertFalse(user.getDeleted());
        Assert.assertNotNull(user.getPerson().getId());
        Assert.assertEquals("Ram", person.getFirstName());
        Assert.assertEquals("user", user.getRole().getRole());
    }

    @Test
    public void shouldFindById() throws Exception {
        User user = userDao.findById(2);

        Assert.assertNotNull(user);
        Assert.assertEquals(2, user.getId());
        Assert.assertFalse(user.getDeleted());
    }

    @Test
    public void shouldFindByUserName() throws Exception {
        User user = userDao.findByUserName("mannu");

        Assert.assertNotNull(user);
        Assert.assertEquals("mannu", user.getUserName());
        Assert.assertFalse(user.getDeleted());

        User notExistUser = userDao.findByUserName("jptUser");
        Assert.assertNull(notExistUser);
    }

    @Test
    public void shouldCheckUserIsActive() throws Exception {
        Boolean isActive = userDao.isActive("mjsanish");

        Assert.assertTrue(isActive);
    }

    @Test
    @Transactional
    public void shouldReturnUserByPerson() throws Exception {
        Person person = personDao.findById(1);
        User user = userDao.findByPerson(person);

        Assert.assertNotNull(user);
        Assert.assertEquals("mjsanish", user.getUserName());
        Assert.assertEquals(1, user.getPerson().getId());
        Assert.assertFalse(user.getDeleted());
    }

    @Test
    public void shouldReturnAllUsers() throws Exception {
        List<User> users = userDao.getAll();

        Assert.assertEquals(2, users.size());
        Assert.assertEquals(1, users.get(0).getId());
        Assert.assertEquals(2, users.get(1).getId());
        Assert.assertFalse(users.get(0).getDeleted());
        Assert.assertFalse(users.get(1).getDeleted());
    }

    @Test
    public void shouldReturnUsersByRole() throws Exception {
        Role role = roleDao.findByUserRole(UserRole.ADMIN);
        List<User> users = userDao.getAllByRole(role);

        Assert.assertEquals(1, users.size());
        Assert.assertEquals(1, users.get(0).getId());
        Assert.assertEquals("mjsanish", users.get(0).getUserName());
        Assert.assertFalse(users.get(0).getDeleted());
    }
}
