package com.scaffold.application;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:TestingApplicationContex.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SampleTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setupData() throws Exception {
    }

    @Test
    public void testAssertTrue() {
        Assert.assertTrue(true);
    }
}
