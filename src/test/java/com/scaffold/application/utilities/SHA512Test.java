package com.scaffold.application.utilities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:TestingApplicationContex.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SHA512Test {

    @Test
    public void ShouldGenerateRandomSalt() throws Exception{
        String salt1 = SHA512.generateSalt();
        String salt2 = SHA512.generateSalt();
        String salt3 = SHA512.generateSalt();
        System.out.println(salt1);

        Assert.assertFalse(salt1.equals(salt2));
        Assert.assertFalse(salt1.equals(salt3));
        Assert.assertFalse(salt2.equals(salt3));
    }

    @Test
    public void ShouldHashPasswordWithSalt() throws Exception {
        String planPassword = "password";
        String salt1 = "rr+CDeT3R6V3hXd5zMqvCjPI1zKDkAdfkUgc581eskk=";
        String salt2 = "emQMbzEBTbixbjeBEWf5JH3DbfbN/YpiqQhOtQ2w9Hc=";
        String hashPassword1 = SHA512.hashPassword(planPassword, salt1);
        String hashPassword2 = SHA512.hashPassword(planPassword, salt2);

        Assert.assertEquals("98be32e153d0067044542cc150405687da2b5704bce2b24ced326c4725c775db64511b40f21dce69e0115b143c43eac86e9f89b738c0c7125d0e1df78879141c", hashPassword1);
        Assert.assertEquals("d2b5dd122f689b28638f67467cbe0a6c2e4fa03aa7bb6a88c674384f91779f967571c22739540363a16aa7fe07ebd6807996e0f51e1fbc3c804c401773a657f8", hashPassword2);
    }

    @Test
    public void SouldComparePassword() throws Exception {
        String hashPassword = "98be32e153d0067044542cc150405687da2b5704bce2b24ced326c4725c775db64511b40f21dce69e0115b143c43eac86e9f89b738c0c7125d0e1df78879141c";
        String salt = "rr+CDeT3R6V3hXd5zMqvCjPI1zKDkAdfkUgc581eskk=";
        String actualPassword = "password";

        Assert.assertTrue(SHA512.comparePassword(actualPassword, hashPassword, salt));
    }
}
