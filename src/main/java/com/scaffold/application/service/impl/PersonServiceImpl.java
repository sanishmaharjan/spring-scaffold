package com.scaffold.application.service.impl;

import com.scaffold.application.constant.Gender;
import com.scaffold.application.dao.PersonDao;
import com.scaffold.application.model.Person;
import com.scaffold.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonDao personDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao){
        this.personDao = personDao;
    }

    @Override
    public Person savePerson(Person person) {
        return personDao.save(person);
    }

    @Override
    public Person savePerson(Integer id, Map<String, Object> attributes) {
        Person person = id != null ? personDao.findById(id) : new Person();
        person.setDeleted(false);
        for (String key: attributes.keySet()) {
            if(key.equals("firstName")){
                person.setFirstName((String) attributes.get(key));
            } else if(key.equals("middleName")){
                person.setMiddleName((String) attributes.get(key));
            } else if(key.equals("lastName")){
                person.setLastName((String) attributes.get(key));
            } else if(key.equals("address")){
                person.setAddress((String) attributes.get(key));
            } else if(key.equals("email")){
                person.setEmail((String) attributes.get(key));
            } else if(key.equals("gender")){
                Gender gender = (Gender) attributes.get(key);
                person.setGender(gender.getGender());
            }
        }

        return personDao.save(person);
    }

    @Override
    public void deletePerson(Person person, String deleteReason) {
        person.setDeleted(true);
        person.setDeleteDate(new Date());
        person.setDeleteReason(deleteReason);

        personDao.save(person);
    }

    @Override
    public List<Person> getAllPerson() {
        return personDao.getAll();
    }

    @Override
    public Person getPersonById(Integer id) {
        return personDao.findById(id);
    }
}
