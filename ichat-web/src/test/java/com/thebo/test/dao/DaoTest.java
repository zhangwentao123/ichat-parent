package com.thebo.test.dao;


import com.thebo.dao.TestDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DaoTest {

    @Resource
    protected TestDao testDao;

    @Test
    public void testDao(){
        testDao.test();
    }

}
