package com.scaffold.application.dao;


import com.scaffold.application.model.Person;

import java.util.List;

public interface PersonDao {

    /**
     * Save / Update Person
     *
     * @param person {@link Person}
     * @return {@link Person}
     */
    Person save(Person person);

    /**
     * Find Person by Id, return null if not exist
     *
     * @param id {@link Integer}
     * @return {@link Person}
     */
    Person findById(Integer id);

    /**
     * Return all Persons
     *
     * @return {@link List<Person>}
     */
    List<Person> getAll();
}
