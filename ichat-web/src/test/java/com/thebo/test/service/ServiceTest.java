package com.thebo.test.service;

import com.thebo.exception.BaseThrowableException;
import com.thebo.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring-core.xml")
public class ServiceTest {

    @Resource
    protected TestService testService;

    @Test
    public void testService() {
        testService.test();
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
