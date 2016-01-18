package com.thebo.framework.spring.property;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * spring读取配置文件读取，通过propertyPlaceholderConfigurer读取
 */
public class ConstantsPlaceholderConfigurer extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {
    private static Map<String,String> ctxPropertiesMap;
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        super.processProperties(beanFactoryToProcess,props);
        ctxPropertiesMap = new HashMap();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }
    public static String getContextProperty(String name) {
        return ctxPropertiesMap.get(name);
    }
}
