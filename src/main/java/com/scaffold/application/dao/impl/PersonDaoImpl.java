package com.scaffold.application.dao.impl;

import com.scaffold.application.dao.PersonDao;
import com.scaffold.application.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@EnableTransactionManagement
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Person save(Person person) {
        em.persist(person);
        return person;
    }

    @Override
    public Person findById(Integer id) {
        return em.find(Person.class, id);
    }

    @Override
    public List<Person> getAll() {
        return em.createNamedQuery(Person.FIND_ALL, Person.class).getResultList();
    }
}
