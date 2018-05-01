package com.scaffold.application.service;

import com.scaffold.application.constant.UserRole;
import com.scaffold.application.model.Person;
import com.scaffold.application.model.Role;
import com.scaffold.application.model.User;

import java.util.List;

public interface UserService {
    /**
     * Add / Update user
     *
     * @param user {@link User}
     * @return {@link User}
     */
    User saveUser(User user);

    /**
     * Add / Update user
     *
     * @param userId {@link Integer | null} if null add new user else update existing user
     * @param person {@link Person}
     * @param role {@link Role}
     * @param userName {@link String}
     * @param password {@link String}
     * @return {@link User}
     */

    User saveUser(Integer userId, Person person, Role role, String userName, String password);

    /**
     * Return user by Id
     *
     * @param id {@link Integer}
     * @return {@link User | null}
     */
    User getUserById(Integer id);

    /**
     * Return user by userName
     *
     * @param userName {@link String}
     * @return {@link User | null}
     */
    User getUserByUserName(String userName);

    /**
     * Return user by Person {@link Person}
     *
     * @param person {@link Person}
     * @return {@link User | null}
     */
    User getUserByPerson(Person person);

    /**
     * Return user by Person {@link Person} id
     * @param personId {@link String}
     * @return {@link User}
     */
    User getUserByPersonId(Integer personId);

    /**
     * Check is user active
     *
     * @param userName {@link String}
     * @return {@link User}
     */
    Boolean isActiveUser(String userName);

    /**
     * Return all Users
     *
     * @return {@link List<User>}
     */
    List<User> getAllUsers();

    /**
     * Return users by UserRole {@link UserRole}
     * @param userRole {@link UserRole}
     * @return {@link List<User>}
     */
    List<User> getAllUsersByRole(UserRole userRole);

    /**
     * Validate UserName & Password4
     *
     * @param userName {@link String}
     * @param password {@link String}
     * @return {@link Boolean}
     */
    Boolean isValidUserCredential(String userName, String password);
}
