package com.scaffold.application.dao;

import com.scaffold.application.model.Person;
import com.scaffold.application.model.Role;
import com.scaffold.application.model.User;

import java.util.List;

public interface UserDao {
    /**
     * Save / Update user
     *
     * @param user {@link User}
     * @return {@link User}
     */
    User save(User user);

    /**
     * Find user by id
     *
     * @param id {@link Integer}
     * @return {@link User | null}
     */
    User findById(Integer id);

    /**
     * Find user by userName, Return null if not exist
     *
     * @param userName {@link String}
     * @return {@link User | null}
     */
    User findByUserName(String userName);

    /**
     * Check is user active By userName, return null if not exist
     *
     * @param userName {@link String}
     * @return {@link Boolean | null}
     */
    Boolean isActive(String userName);

    /**
     * Find user by Person
     *
     * @param person {@link Person}
     * @return {@link User}
     */
    User findByPerson(Person person);

    /**
     * Return all users
     *
     * @return {@link List<User>}
     */
    List<User>  getAll();

    /**
     * Return all Users having Role
     *
     * @param role {@link Role}
     * @return {@link List<User>}
     */
    List<User> getAllByRole(Role role);
}
