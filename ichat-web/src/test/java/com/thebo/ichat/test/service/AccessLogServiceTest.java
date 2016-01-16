package com.thebo.ichat.test.service;

import com.thebo.ichat.entity.AccessLog;
import com.thebo.ichat.service.AccessLogService;
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
public class AccessLogServiceTest {

    @Resource
    protected AccessLogService accessLogService;

    @Test
    public void testInsert() {
        AccessLog accessLog = new AccessLog();
        accessLog.setSignature("com.thebo.service.hi()");
        accessLog.setRequestTime(Calendar.getInstance().getTime());
        accessLog.setTime(1000000);
        accessLogService.save(accessLog);
    }

}
