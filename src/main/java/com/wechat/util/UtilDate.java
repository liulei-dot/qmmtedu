package com.wechat.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/* *
 *类名：UtilDate
 *功能：自定义订单类
 *详细：工具类，可以用作获取系统日期、订单编号等
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class UtilDate {
	
	public static GregorianCalendar gcalendar = new GregorianCalendar();
	
	public static SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");
	
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmssSSS";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
	
    /** 年月日(有下划线) yyyy-MM-dd */
    public static final String dtString                 = "yyyy-MM-dd";
    
    /** 时分秒 HH:mm:ss */
    public static final String timeString                 = "HH:mm:ss";
    
    
	/**
	 * 私有构造函数，避免DateUtil被实例化
	 */
	private UtilDate(){}
	
    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyy-MM-dd
	 * @return
	 */
	public static String getDateString(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtString);
		return df.format(date);
	}

	/**
	 * 获取系统明天的时间(精确到天)，格式：yyyy-MM-dd
	 * @Title: getNextDate
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getNextDate(){
		Calendar calendar = Calendar.getInstance();		// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, -1); 				// 得到前一天
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}
	/**
	 * 获取系统当前时分秒，格式：HH:mm:ss
	 * @return
	 */
	public static String getTimeString(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(timeString);
		return df.format(date);
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyy-MM-dd
	 * @return
	 */
	public static String convertDate(String dateStr, String format) {
		java.util.Date date = null;
		DateFormat df=new SimpleDateFormat(dtString);
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			date = simpleDateFormat.parse(dateStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return df.format(date);
	}
	
	/**
	 * @Title: contrastDate
	 * @Description: 时间对比
	 * @param dateStr
	 * @return
	 * @return: boolean
	 */
	public static boolean contrastDate(String dateStr) {
		try {
			DateFormat df=new SimpleDateFormat(dtString);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dtString);
			Date date = simpleDateFormat.parse(dateStr);
			if (df.format(date).compareTo(df.format(new Date())) == 0) {
				return true;
			}
		} catch (ParseException e) {
			return false;
		}
		return false;
	}
	
	public static String contrastDateStr(String dateStr) {
		String date1 = null;
		try {
			DateFormat df=new SimpleDateFormat(dtString);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dtString);
			Date date = simpleDateFormat.parse(dateStr);
			date1 = String.valueOf(df.format(date).compareTo(df.format(new Date())));
		} catch (ParseException e) {
			return "";
		}
		return date1;
	}
	
	public static Date stringToDate(String dateStr, String format) {
		Date date1 = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			date1 = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;
	}
	/**
	 * @Title: getTwoDay
	 * @Description: 获取两个时间间隔
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 * @return: String
	 */
	public static long timeInterval(Date dateStr1, Date dateStr2, String format) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(format);
		DateFormat df = new SimpleDateFormat(format);
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(df.format(dateStr1));
			java.util.Date mydate = myFormatter.parse(df.format(dateStr2));
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000) + 1;
		} catch (Exception e) {
			return 0;
		}
		return day;
	}
    
    public static int calculateCarUseYears(String startDateStr,String enrollDateStr){
    	int mm = calculateCarUseMonth(startDateStr, enrollDateStr);
		int year = mm/12;
		return year;
	}
    
	@SuppressWarnings("deprecation")
	public static int calculateCarUseMonth(String startDateStr, String enrollDateStr) {
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date startDate = new Date();
		java.util.Date enrollDate = new Date();
		try {
			startDate = format.parse(startDateStr);
			enrollDate = format.parse(enrollDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int mm = 0; // 车辆已使用总月数
		int sYear = startDate.getYear();
		int eYear = enrollDate.getYear();
		int sMonth = startDate.getMonth();
		int eMonth = enrollDate.getMonth();
		int sDate = startDate.getDate();
		int eDate = enrollDate.getDate();
		if (sDate >= eDate) {
			mm = (sYear - eYear) * 12 + sMonth - eMonth;
		} else {
			mm = (sYear - eYear) * 12 + sMonth - eMonth - 1;
		}
		return mm;
	}
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}
	
	public static long formatDateTimeToLong(String dateTime)
			throws Exception {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sd.parse(dateTime);
		return date.getTime();
	}

	public static Date formatDateTimeToDate(String dateTime) throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.parse(dateTime);
	}

	public static Timestamp getSqlTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String getDate(Date date) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		return sd.format(date);
	}
	
	public static String getDate(long time) {
		Date d = new Date(time);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		return sd.format(d);
	}
	
	public static String getDate(int n) {
		long time = System.currentTimeMillis();
		time += 86400000L * n;
		Date d = new Date(time);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		return sd.format(d);
	}
	
	public static int getDateIntValue(int n) {
		long time = System.currentTimeMillis();
		time += 86400000L * n;
		Date d = new Date(time);
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(sd.format(d));
	}
	
	/**
	 * 时间的long格式转换成yyyy-MM-dd HH:mm:ss格式
	 * @Title: getDateTime
	 * @Description: TODO
	 * @param time
	 * @return
	 * @return: String
	 */
	public static String getDateTime(long time) {
		Date d = new Date(time);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.format(d);
	}
	
	/**
	 * 时间的long时间转换 yyyy-MM-dd HH:mm
	 * @Title: getDateTimeWithoutSecond
	 * @Description: TODO
	 * @param time
	 * @return
	 * @return: String
	 */
	public static String getDateTimeWithoutSecond(long time) {
		Date d = new Date(time);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sd.format(d);
	}
	
	/**
	 * 获得今天的 yyyy-MM-dd HH:mm:ss 格式时间
	 * @Title: getDateTime
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getDateTime() {
		Date d = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.format(d);
	}
	
	/**
	 * xx月xx日,星期x,(上午/下午/晚上)xx点xx分
	 * @param dateTime
	 * @return
	 */
	public static String formatDateTimeToTTS(Date date) {
		StringBuffer buf = new StringBuffer();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int years = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int week = c.get(Calendar.DAY_OF_WEEK);
		int am_pm = c.get(Calendar.AM_PM);
		int hour = c.get(Calendar.HOUR);
		int minute = c.get(Calendar.MINUTE);
		buf.append(years).append("年");
		buf.append(month).append("月");
		buf.append(day).append("日");
		switch (week) {
		case Calendar.SUNDAY:
			buf.append(",星期日");
			break;
		case Calendar.MONDAY:
			buf.append(",星期一");
			break;
		case Calendar.TUESDAY:
			buf.append(",星期二");
			break;
		case Calendar.WEDNESDAY:
			buf.append(",星期三");
			break;
		case Calendar.THURSDAY:
			buf.append(",星期四");
			break;
		case Calendar.FRIDAY:
			buf.append(",星期五");
			break;
		case Calendar.SATURDAY:
			buf.append(",星期六");
			break;
		}
		if (am_pm == Calendar.AM) {
			buf.append(",上午").append(hour).append("点");
		} else {
			if(hour < 1){
				buf.append(",中午").append("12点");
			}else if (hour < 6) {
				buf.append(",下午").append(hour).append("点");
			} else {
				buf.append(",晚上").append(hour).append("点");
			}
		}
		buf.append(minute).append("分");
		return buf.toString();
	}
	
	/**
	 * destDate 大于 srcDate多少天
	 * @param srcDate
	 * @param destDate
	 * @return
	 */
	public static int dayCompare(Date srcDate,Date destDate){
		Calendar c = Calendar.getInstance();
		c.setTime(srcDate);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		Calendar destC = Calendar.getInstance();
		destC.setTime(destDate);
		destC.set(destC.get(Calendar.YEAR), destC.get(Calendar.MONTH), destC.get(Calendar.DAY_OF_MONTH));
		Long temp = (destC.getTimeInMillis() - c.getTimeInMillis())/(1000*60*60*24); 
		return temp.intValue();
	}
	
	/**
	 * 当前日期加减n天后的日期，返回String (yyyy-mm-dd)
	 * @Title: nDaysAftertoday
	 * @Description: TODO
	 * @param n
	 * @return
	 * @return: String
	 */
	public static String nDaysAftertoday(int n) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance();
		// rightNow.add(Calendar.DAY_OF_MONTH,-1);
		rightNow.add(Calendar.DAY_OF_MONTH, +n);
		return df.format(rightNow.getTime());
	}
 
	/**
	 * 当前日期加减n天后的日期，返回String (yyyy-mm-dd)
	 * @Title: nDaysAfterNowDate
	 * @Description: TODO
	 * @param n
	 * @return
	 * @return: Date
	 */
	public static Date nDaysAfterNowDate(int n) {
		Calendar rightNow = Calendar.getInstance();
		// rightNow.add(Calendar.DAY_OF_MONTH,-1);
		rightNow.add(Calendar.DAY_OF_MONTH, +n);
		return rightNow.getTime();
	}
    
	/**
	 * 获得传人时间的一年后时间
	 * @Title: getNextYears
	 * @Description: TODO
	 * @param strDate
	 * @return
	 * @return: String
	 */
	public static String getNextYears(String strDate) {
		try {
			Date date = sformat.parse(strDate);
			gcalendar.setTime(date);
			gcalendar.add(1, +1);
			gcalendar.set(gcalendar.get(Calendar.YEAR), gcalendar.get(Calendar.MONTH), gcalendar.get(Calendar.DATE) - 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sformat.format(gcalendar.getTime());
	}

	/**
	 * @Title: getHalfYear
	 * @Description: TODO
	 * @param strDate
	 * @param num
	 * @return
	 * @return: String
	 */
	public String getHalfYear(String strDate, int num) {
		try {
			Date date = sformat.parse(strDate);
			gcalendar.setTime(date);
			gcalendar.add(2, + num);
			gcalendar.set(gcalendar.get(Calendar.YEAR), gcalendar.get(Calendar.MONTH), gcalendar.get(Calendar.DATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sformat.format(gcalendar.getTime());
	}
	
	/**
	 * 获取本年的第一天
	 * @return
	 */
	public static String getFirstDayOfCurrentYear() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, 1);// 本年第一天
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(c.getTime());
	}

	/**
	 * 获取本年的最后一天
	 * @return
	 */
	public static String getLastDayOfCurrentYear() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 1);
		c.set(Calendar.DAY_OF_YEAR, 1);// 本年最后一天
		c.add(Calendar.DAY_OF_YEAR, -1);
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(c.getTime());
	}
	
    /**
     * 获取一年后的日期 -1 天
     * @param dateStr
     * @return
     * @throws ParseException
     */
	@SuppressWarnings("unused")
	public static String getOneYearLater(String dateStr) throws ParseException {
		  java.util.Calendar c = java.util.Calendar.getInstance(); 
		  java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		  Date date =formatter.parse(dateStr);
		  c.setTime(date);
		  date = c.getTime(); 
		  String beginDate = formatter.format(date);
		  c.add(Calendar.YEAR, +1);
		  c.add(Calendar.DATE, -1);
		  java.util.Date newdate = c.getTime();
		  String endDate = formatter.format(newdate);
		  return endDate;
	}

    public static float calculateCarUseYearsD(String startDateStr,String enrollDateStr){
    	float mm = calculateCarUseMonth(startDateStr, enrollDateStr);
    	float year = mm/12;
		return year;
	}
    
	public static void main(String[] args) {
		//System.out.println(UtilDate.timeInterval(new Date(), new Date(), "yyyy-MM-dd HH:mm:ss"));
//		System.out.println("year1 = "+UtilDate.calculateCarUseYears("2020-01-01","2015-01-02"));
		System.out.println(System.currentTimeMillis());
	}
}