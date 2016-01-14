package com.thebo.exception;

import com.thebo.common.utils.ConfigReader;
import com.thebo.framework.mybatis.Property;
import com.thebo.framework.spring.property.PropertyPlaceholderConfigurer;

import javax.annotation.Resource;

/**
 *
 * Created by hebo on 2016-1-12.
 */
@Deprecated
public class CustomRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static ConfigReader _cr;

    @Resource
    protected static PropertyPlaceholderConfigurer configurer;

//    static {
//        _cr = new ConfigReader("/errorcode.properties");
//    }

    private String errorCode;

    public CustomRuntimeException() {
        super();
    }

//    public CustomRuntimeException(String message) {
//        super(message);
//    }

    public CustomRuntimeException(String errorCode) {
//        super(_cr.get(errorCode));
        super(String.format("【%s】%s",errorCode,configurer.getContextProperty(errorCode)));
        this.errorCode = errorCode;
    }

    public CustomRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomRuntimeException(Throwable cause) {
        super(cause);
    }

    public CustomRuntimeException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
