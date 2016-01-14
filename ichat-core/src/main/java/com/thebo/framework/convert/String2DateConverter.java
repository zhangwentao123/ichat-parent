package com.thebo.framework.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class String2DateConverter implements Converter<String, Date>{
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public Date convert(String source) {
		logger.debug("=====================String2DateConverter InIt===============");
		if(StringUtils.isBlank(source)){
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
	    dateFormat.setLenient(false);   
	    try {   
	        return dateFormat.parse(source);   
	    } catch (ParseException e) {   
	    	logger.error("String2DateConverter Error",e);   
	    }          
	    return null;
	}

}
