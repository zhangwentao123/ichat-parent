package com.thebo.framework.convert;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class Date2StringConverter implements Converter<Date,String>{
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public String convert(Date source) {
		logger.debug("=====================Date2StringConverter InIt===============");
		if(source==null){
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
	    dateFormat.setLenient(false);   
	    return dateFormat.format(source) ;          
	}

}
