package com.thebo.ichat.common.aspect;

import com.thebo.ichat.entity.AccessLog;
import com.thebo.ichat.service.AccessLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by HB on 16/1/16.
 */
@Aspect
@Component
public class AccessLogAspect {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private long start;
    private long end;

    @Resource
    protected AccessLogService accessLogService;

    /**
     * Pointcut
     * 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数
     * 该方法就是一个标识，不进行调用
     */
    @Pointcut("execution(* com.thebo..web*..*(..)) || execution(* com.thebo..service*..*(..)) || execution(* com.thebo..dao*..*(..))")
    private void doLogger() {
    }

    /**
     * Before
     * 在核心业务执行前执行，不能阻止核心业务的调用。
     *
     * @param joinPoint
     */
    @Before("doLogger()")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.debug("=======>进入Service访问");
        Object targetObject = joinPoint.getTarget();
        Signature signature = joinPoint.getSignature();
        String signatureName = signature.getName();
        Object[] args = joinPoint.getArgs();
        logger.debug("before exec class:{},method:{},args:{}", targetObject, signatureName, args);
        start = System.currentTimeMillis();
    }


    /**
     * After
     * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
     *
     * @param joinPoint
     */
    @After(value = "doLogger()")
    public void afterAdvice(JoinPoint joinPoint) {
        logger.debug("=======>退出方法记录日志切面开始");
        Object targetObject = joinPoint.getTarget();
        Signature signature = joinPoint.getSignature();
        String signatureName = signature.getName();
        logger.debug("after exec class:{},method:{}", targetObject, signatureName);
        logger.debug("=======>退出方法记录日志切面结束");

        end = System.currentTimeMillis();
        logger.debug("=======>执行{}方法共耗时{}ms", signature, end-start);

        AccessLog log = new AccessLog();

        log.setSignature(signature.toString());
        log.setTime(Integer.valueOf(""+(end - start)));

        accessLogService.save(log);
    }





}
