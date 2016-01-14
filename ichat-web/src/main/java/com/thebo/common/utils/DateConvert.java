//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.thebo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateConvert implements Converter {
    public static final Logger LOGGER = LoggerFactory.getLogger(DateConvert.class);
    private static String dateFormatStr = "yyyy-MM-dd";
    private static SimpleDateFormat dateTimeFormat;
    private static String dateLongFormatStr;
    private static SimpleDateFormat dateTimeLongFormat;

    public DateConvert() {
    }

    public Object convert(Class arg0, Object arg1) {
        if(arg1 == null) {
            return null;
        } else {
            LOGGER.info(arg1.getClass().getName() + "=" + arg1.toString());
            String className = arg1.getClass().getName();
            SimpleDateFormat e;
            if(!"java.sql.Timestamp".equalsIgnoreCase(className) && !"java.util.Date".equalsIgnoreCase(className) && !"java.sql.Date".equalsIgnoreCase(className)) {
                String p1 = (String)arg1;
                if(p1 != null && p1.trim().length() != 0) {
                    try {
                        e = new SimpleDateFormat(dateFormatStr + " HH:mm:ss");
                        return e.parse(p1.trim());
                    } catch (Exception var9) {
                        try {
                            SimpleDateFormat ex = new SimpleDateFormat(dateFormatStr);
                            return ex.parse(p1.trim());
                        } catch (ParseException var7) {
                            var9.printStackTrace();
                            return null;
                        }
                    }
                } else {
                    return null;
                }
            } else {
                try {
                    SimpleDateFormat p = new SimpleDateFormat(dateFormatStr + " HH:mm:ss");
                    return p.parse(dateTimeLongFormat.format(arg1));
                } catch (Exception var10) {
                    try {
                        e = new SimpleDateFormat(dateFormatStr);
                        return e.parse(dateTimeFormat.format(arg1));
                    } catch (ParseException var8) {
                        var10.printStackTrace();
                        return null;
                    }
                }
            }
        }
    }

    public static String formatDateTime(Object obj) {
        return obj != null?dateTimeFormat.format(obj):"";
    }

    public static String formatLongDateTime(Object obj) {
        return obj != null?dateTimeLongFormat.format(obj):"";
    }

    public static String convertLongToDateTime(Long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dt = new Date(time.longValue() * 1000L);
        String sDateTime = sdf.format(dt);
        return sDateTime;
    }

    public static Long formatStrDateTime2Long(String dateTime, String format) throws Exception {
        if(StringUtils.isBlank(dateTime)) {
            throw new Exception("����ʱ�䲻��Ϊ��!");
        } else if(StringUtils.isBlank(format)) {
            throw new Exception("���ڸ�ʽ����Ϊ��!");
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            Date date = null;

            try {
                date = df.parse(dateTime);
            } catch (ParseException var5) {
                throw new Exception(var5.toString());
            }

            return Long.valueOf(date.getTime() / 1000L);
        }
    }

    public static void main(String[] args) {
        String dateTime = formatLongDateTime(new Date());

        try {
            Long e = formatStrDateTime2Long(dateTime, "yyyy-MM-dd HH:mm:ss");
            System.out.println(formatLongDateTime(new Date(e.longValue())));
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    static {
        dateTimeFormat = new SimpleDateFormat(dateFormatStr);
        dateLongFormatStr = dateFormatStr + " HH:mm:ss";
        dateTimeLongFormat = new SimpleDateFormat(dateLongFormatStr);
    }
}
