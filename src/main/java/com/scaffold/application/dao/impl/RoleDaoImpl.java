package com.scaffold.application.dao.impl;

import com.scaffold.application.constant.UserRole;
import com.scaffold.application.dao.RoleDao;
import com.scaffold.application.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@EnableTransactionManagement
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Role findById(Integer id) {
        return em.find(Role.class, id);
    }

    @Override
    public Role findByUserRole(UserRole userRole) {
        try {
            return em.createNamedQuery(Role.FIND_BY_ROLE, Role.class).setParameter("userRole", userRole.getUserRole()).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Role> findAll() {
        return em.createNamedQuery(Role.FIND_ALL, Role.class).getResultList();
    }
}
