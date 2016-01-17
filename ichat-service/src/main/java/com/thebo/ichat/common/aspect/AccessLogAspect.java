package com.thebo.ichat.common.aspect;

import com.thebo.ichat.entity.LogAccess;
import com.thebo.ichat.service.LogAccessService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * Created by HB on 16/1/16.
 */
@Aspect
@Component
public class AccessLogAspect {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private long start;
    private long end;

    protected LogAccess logAccess;

    @Resource
    protected LogAccessService logAccessService;

    /**
     * Pointcut
     * 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数
     * 该方法就是一个标识，不进行调用
     */
    @Pointcut("execution(* com.thebo..service*..*(..))")
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
        start = System.currentTimeMillis();
        logAccess.setRequestTime(Calendar.getInstance().getTime());
    }


    /**
     * After
     * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
     *
     * @param joinPoint
     */
    @After(value = "doLogger()")
    public void afterAdvice(JoinPoint joinPoint) {
        Object targetObject = joinPoint.getTarget();
        Signature signature = joinPoint.getSignature();
        String signatureName = signature.getName();

        end = System.currentTimeMillis();
        logger.debug("=======>执行{}方法共耗时{}ms", signature, end-start);

        logAccess.setSignature(signature.toString());
        logAccess.setTime(Integer.valueOf(""+(end - start)));

        logAccessService.save(logAccess);
    }


    /**
     * AfterReturning
     * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
     * @param joinPoint
     */
    @AfterReturning(value = "doLogger()", returning = "retVal")
    public void afterReturningAdvice(JoinPoint joinPoint, String retVal) {
        logAccess.setStatus(1);
        logAccessService.save(logAccess);
    }


    /**
     * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
     * <p/>
     * 注意：执行顺序在Around Advice之后
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "doLogger()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        logAccess.setStatus(1);
        logAccessService.save(logAccess);
    }




}
