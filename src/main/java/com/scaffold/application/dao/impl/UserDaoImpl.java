package com.scaffold.application.dao.impl;

import com.scaffold.application.dao.UserDao;
import com.scaffold.application.model.Person;
import com.scaffold.application.model.Role;
import com.scaffold.application.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@EnableTransactionManagement
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User findById(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByUserName(String userName) {
        try {
            return em.createNamedQuery(User.FIND_BY_USER_NAME, User.class).setParameter("userName", userName).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Boolean isActive(String userName) {
        try {
            User user = em.createNamedQuery(User.IS_ACTIVE_USER, User.class).setParameter("userName", userName).getSingleResult();
            return (user != null);
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public User findByPerson(Person person) {
        try {
            return em.createNamedQuery(User.FIND_BY_PERSON, User.class).setParameter("person", person).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.FIND_ALL, User.class).getResultList();
    }

    @Override
    public List<User> getAllByRole(Role role) {
        return em.createNamedQuery(User.FIND_All_BY_ROLE, User.class).setParameter("role", role).getResultList();
    }
}
