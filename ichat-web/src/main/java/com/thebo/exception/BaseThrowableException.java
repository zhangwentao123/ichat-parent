package com.thebo.exception;

import com.thebo.framework.spring.property.PropertyPlaceholderConfigurer;

import javax.annotation.Resource;

/**
 * 异常封装类
 * Created by hebo on 2016-1-12.
 */
public class BaseThrowableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * spring导入properties文件内容
     */
    @Resource
    protected static PropertyPlaceholderConfigurer configurer;

    private String errorCode;

    public BaseThrowableException() {
        super();
    }


    /**
     *  throw new CustomRuntimeException("40017");
     *  throw new BaseThrowableException("1000000", new Object[]{"100","1000"});
     * @param errorCode
     * @param args
     */
    public BaseThrowableException(String errorCode, Object... args){
        super(String.format("【%s】%s", errorCode, String.format(configurer.getContextProperty(errorCode), args)));
        this.errorCode = errorCode;
    }

    public BaseThrowableException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseThrowableException(Throwable cause) {
        super(cause);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
