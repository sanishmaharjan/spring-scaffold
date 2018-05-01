package com.scaffold.application.service;

import com.scaffold.application.constant.Gender;
import com.scaffold.application.dao.PersonDao;
import com.scaffold.application.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContextConfiguration(locations = "classpath:TestingApplicationContex.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceTest {
    @Autowired
    PersonService personService;

    @Autowired
    PersonDao personDao;

    @Test
    @Transactional
    @Rollback(value = true)
    public void shouldSaveNewPerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Pradeep");
        person.setLastName("Maharjan");
        person.setEmail("pradeepmjz@gmail.com");
        person.setGender("Male");
        person.setDeleted(false);
        personService.savePerson(person);

        Assert.assertNotNull(person.getId());
        Assert.assertFalse(person.getDeleted());
        Assert.assertEquals("Pradeep", person.getFirstName());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void shouldUpdateExistingPerson() throws Exception {
        Person person = personDao.findById(1);
        person.setFirstName("Dinesh");
        personService.savePerson(person);

        Person updatedPerson = personDao.findById(1);
        Assert.assertEquals("Dinesh", updatedPerson.getFirstName());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void shouldSaveNewPersonWithPersonAttributes() throws Exception {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("firstName", "Pradeep");
        attributes.put("lastName", "Maharjan");
        attributes.put("gender", Gender.MALE);
        attributes.put("address", "Panga");
        attributes.put("email", "pradeepMhz@gmail.com");
        Person person = personService.savePerson(null, attributes);

        Assert.assertNotNull(person.getId());
        Assert.assertFalse(person.getDeleted());
        Assert.assertEquals("Pradeep", person.getFirstName());
        Assert.assertEquals("pradeepMhz@gmail.com", person.getEmail());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void shouldUpdateExistingPersonWithPersonAttribures() throws Exception {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("firstName", "Rasna");
        attributes.put("lastName", "Shakya");
        attributes.put("gender", Gender.FEMALE);
        Person person = personService.savePerson(1, attributes);

        Assert.assertEquals(1, person.getId());
        Assert.assertFalse(person.getDeleted());
        Assert.assertEquals("Rasna", person.getFirstName());
        Assert.assertEquals("Shakya", person.getLastName());
        Assert.assertEquals("Female", person.getGender());
        Assert.assertEquals("Panga, Kirtipur", person.getAddress());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void shouldSoftDeletePerson() throws Exception {
        Person person = personService.getPersonById(2);
        personService.deletePerson(person, "Leave office");


        Assert.assertTrue(person.getDeleted());
        Assert.assertEquals("Leave office", person.getDeleteReason());
    }

    @Test
    public void shouldReturnAllPersons() throws Exception {
        List<Person> persons = personService.getAllPerson();

        Assert.assertEquals(3, persons.size());
        Assert.assertEquals("Sanish", persons.get(0).getFirstName());
        Assert.assertEquals(false, persons.get(0).getDeleted());
        Assert.assertEquals("Rasna", persons.get(2).getFirstName());
        Assert.assertEquals(false, persons.get(2).getDeleted());
    }

    @Test
    public void shouldReturnPersonById() throws Exception {
        Person person = personService.getPersonById(2);

        Assert.assertNotNull(person);
        Assert.assertEquals(2, person.getId());
        Assert.assertEquals("Manish", person.getFirstName());
        Assert.assertFalse(person.getDeleted());
    }
}
