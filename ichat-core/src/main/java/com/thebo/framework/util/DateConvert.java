package com.thebo.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author qiuyangjun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DateConvert implements Converter{
    public static final Logger LOGGER = LoggerFactory.getLogger(DateConvert.class);
    private static String dateFormatStr = "yyyy-MM-dd";  
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(dateFormatStr);  
      
    private static String dateLongFormatStr = dateFormatStr+" HH:mm:ss";  
    private static SimpleDateFormat dateTimeLongFormat = new SimpleDateFormat(dateLongFormatStr);  
  
    public Object convert(Class arg0, Object arg1) {  
        if(arg1==null){
            return null;
        }
        LOGGER.info(arg1.getClass().getName()+"="+arg1.toString());
        String className = arg1.getClass().getName();  
        //java.sql.Timestamp,java.util.Date,java.sql.Date
        if ("java.sql.Timestamp".equalsIgnoreCase(className)
                ||"java.util.Date".equalsIgnoreCase(className)
                ||"java.sql.Date".equalsIgnoreCase(className)) {  
            try {  
                SimpleDateFormat df = new SimpleDateFormat(dateFormatStr + " HH:mm:ss");  
                return df.parse(dateTimeLongFormat.format(arg1));  
            } catch (Exception e) {  
                try {  
                    SimpleDateFormat df = new SimpleDateFormat(dateFormatStr);  
                    return df.parse(dateTimeFormat.format(arg1));  
                } catch (ParseException ex) {  
                    e.printStackTrace();  
                    return null;  
                }  
            }  
        }else{//Java.lang.String
            String p = (String) arg1;  
            if (p == null || p.trim().length() == 0) {  
                return null;  
            }  
            try {  
                SimpleDateFormat df = new SimpleDateFormat(dateFormatStr + " HH:mm:ss");  
                return df.parse(p.trim());  
            } catch (Exception e) {  
                try {  
                    SimpleDateFormat df = new SimpleDateFormat(dateFormatStr);  
                    return df.parse(p.trim());  
                } catch (ParseException ex) {  
                    e.printStackTrace();  
                    return null;  
                }  
            }  
        }  
    }  
      
    public static String formatDateTime(Object obj) {  
        if (obj != null)  
            return dateTimeFormat.format(obj);  
        else  
            return "";  
    }  
      
    public static String formatLongDateTime(Object obj) {  
        if (obj != null)  
            return dateTimeLongFormat.format(obj);  
        else  
            return "";  
    }  

}
