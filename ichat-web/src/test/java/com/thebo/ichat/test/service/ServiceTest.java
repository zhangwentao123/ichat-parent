package com.thebo.ichat.test.service;

import com.thebo.ichat.exception.BaseThrowableException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ServiceTest {


    @Test
    public void testService() {
        try {
            throw new BaseThrowableException("40017");
        } catch (BaseThrowableException e) {
            System.out.println(e.getMessage());
        }

        try {
            throw new BaseThrowableException("1000000", new Object[]{"100","1000"});
        } catch (BaseThrowableException e) {
            System.out.println(e.getMessage());
        }
    }
}
