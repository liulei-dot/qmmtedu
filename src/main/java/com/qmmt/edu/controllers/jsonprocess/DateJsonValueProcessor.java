package com.qmmt.edu.controllers.jsonprocess;

import java.text.SimpleDateFormat;


public class DateJsonValueProcessor   {
	
	private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Object processArrayValue(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object processObjectValue(String key, Object value
			 ) {
		// TODO Auto-generated method stub
		return process(value);
	}

	private Object process(Object obj) {
		if (obj == null) {
			return null;
		} else {
			return sd.format(obj);
		}
	}

}
