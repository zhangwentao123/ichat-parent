package com.thebo.ichat.test.service;

import com.thebo.ichat.entity.LogAccess;
import com.thebo.ichat.service.LogAccessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * 访问日志记录
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LogAccessServiceTest {

    @Resource
    protected LogAccessService logAccessService;

    @Test
    public void testInsert() {
        LogAccess logAccess = new LogAccess();
        logAccess.setSignature("com.thebo.service.hi()");
        logAccess.setRequestTime(Calendar.getInstance().getTime());
        logAccess.setTime(1000000);
        logAccessService.save(logAccess);
    }

}
