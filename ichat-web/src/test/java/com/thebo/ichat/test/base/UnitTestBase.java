package com.thebo.ichat.test.base;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

public class UnitTestBase {
    private ClassPathXmlApplicationContext context;

    private String springXmlPath;

    public UnitTestBase(String springXmlPath){
        this.springXmlPath = springXmlPath;
    }

    @Before
    public void before(){
        if (StringUtils.isEmpty(springXmlPath)){
//            springXmlPath = "classpath*:applicationContext.xml";
        }
        try {
            context = new ClassPathXmlApplicationContext(springXmlPath.split("[,\\s]+"));
            context.start();
        } catch (BeansException e) {
             e.printStackTrace();
        }
    }

    @After
    public void after(){
        context.destroy();
    }

    protected  <T extends Object> T getBean(String beanId){
        return (T)context.getBean(beanId);
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    protected <T extends Object> T getBean(Class<T> clazz){
        return (T)context.getBeansOfType(clazz);
    }
}
