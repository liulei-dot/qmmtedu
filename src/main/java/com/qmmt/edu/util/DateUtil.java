package com.qmmt.edu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public static String getDateStr(Date date,String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		if (null == date) {
			return null;
		}
		return df.format(date);
	}
	
	public static boolean isSameDay(Date date1,Date date2) {
		if(date1.getYear() == date2.getYear() &&
				date1.getMonth() == date2.getMonth() &&
				date1.getDate() == date2.getDate())
			return true;
		else 
			return false;
	}
	
	public static Date str2Date(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		if (null == str || "".equals(str)) {
			return null;
		}
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String getWeekStr(Date d) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		String wkstr = "";
		int wd = calendar.get(Calendar.DAY_OF_WEEK);
		switch(wd){
			case 1:
				wkstr = "星期日";
				break;
			case 2:
				wkstr = "星期一";
				break;
			case 3:
				wkstr = "星期二";
				break;
			case 4:
				wkstr = "星期三";
				break;
			case 5:
				wkstr = "星期四";
				break;
			case 6:
				wkstr = "星期五";
				break;
			case 7:
				wkstr = "星期六";
				break;
		}
		return wkstr;
	}
}
