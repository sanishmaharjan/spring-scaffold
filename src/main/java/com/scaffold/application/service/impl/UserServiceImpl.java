package com.scaffold.application.service.impl;

import com.scaffold.application.constant.UserRole;
import com.scaffold.application.dao.PersonDao;
import com.scaffold.application.dao.RoleDao;
import com.scaffold.application.dao.UserDao;
import com.scaffold.application.model.Person;
import com.scaffold.application.model.Role;
import com.scaffold.application.model.User;
import com.scaffold.application.service.UserService;
import com.scaffold.application.utilities.SHA512;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    private PersonDao personDao;

    private RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, PersonDao personDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.personDao = personDao;
        this.roleDao = roleDao;
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User saveUser(Integer userId, Person person, Role role, String userName, String password) {
        String salt = SHA512.generateSalt();
        String hashPassword = SHA512.hashPassword(password, salt);
        User user = new User(person, role, userName, hashPassword, salt, true);
        return userDao.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public User getUserByPerson(Person person) {
        return userDao.findByPerson(person);
    }

    @Override
    public User getUserByPersonId(Integer personId) {
        Person person = personDao.findById(personId);
        return person != null ? userDao.findByPerson(person) : null;
    }

    @Override
    public Boolean isActiveUser(String userName) {
        return userDao.isActive(userName);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public List<User> getAllUsersByRole(UserRole userRole) {
        Role role = roleDao.findByUserRole(userRole);
        return userDao.getAllByRole(role);
    }

    @Override
    public Boolean isValidUserCredential(String userName, String password) {
        User user = userDao.findByUserName(userName);
        return user != null ? SHA512.comparePassword(password, user.getPassword(), user.getSalt()) : false;
    }
}
