package com.scaffold.application.dao;

import com.scaffold.application.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration(locations = "classpath:TestingApplicationContex.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonDaoTest {

    @Autowired
    PersonDao personDao;

    @Test
    @Transactional
    @Rollback(value = true)
    public void shouldSavePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Pradeep");
        person.setLastName("Maharjan");
        person.setEmail("pradeepmjz@gmail.com");
        person.setGender("Male");
        person.setDeleted(false);
        person = personDao.save(person);

        Assert.assertNotNull(person.getId());
        Assert.assertFalse(person.getDeleted());
        Assert.assertEquals("Pradeep", person.getFirstName());
    }

    @Test
    public void shouldReturnAllPersons() throws Exception {
        List<Person> persons = personDao.getAll();
        Assert.assertEquals(3, persons.size());
        Assert.assertEquals("Sanish", persons.get(0).getFirstName());
        Assert.assertEquals(false, persons.get(0).getDeleted());
        Assert.assertEquals("Rasna", persons.get(2).getFirstName());
        Assert.assertEquals(false, persons.get(2).getDeleted());
    }

    @Test
    public void shouldFindPersonById() throws Exception {
        Person person = personDao.findById(2);

        Assert.assertNotNull(person);
        Assert.assertEquals(2, person.getId());
        Assert.assertEquals("Manish", person.getFirstName());
        Assert.assertFalse(person.getDeleted());
    }
}
