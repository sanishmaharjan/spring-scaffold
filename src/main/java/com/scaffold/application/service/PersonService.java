package com.scaffold.application.service;

import com.scaffold.application.constant.Gender;
import com.scaffold.application.model.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {
    /**
     * Save / Update person
     *
     * @param person {@link Person}
     * @return {@link Person}
     */
    Person savePerson(Person person);

    /**
     * Save / Update person
     *
     * @param id {@link Integer} if null add new person else update existing person
     * @param attributes {@link Map}
     * @return {@link Person}
     */
    Person savePerson(Integer id, Map<String, Object> attributes);

    /**
     * Soft delete person
     *
     * @param  person {@link Person}
     * @param deleteReason {@link String}
     */
    void deletePerson(Person person, String deleteReason);

    /**
     * Return all persons
     *
     * @return {@link List<Person>}
     */
    List<Person> getAllPerson();

    /**
     * Return person by person Id
     * @param id {@link Integer}
     * @return {@link Person}
     */
    Person getPersonById(Integer id);
}
