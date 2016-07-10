package daily.test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	public static final String PATTERN_HOUR_SECOND = "HH:mm";
	public static final String PATTERN_YEAR_HOUR_SECOND = "yyyy-MM-dd HH:mm";
	public static final String PATTERN_MONTH_DAY = "MM-dd" ;

//	public static SimpleDateFormat global_sf = new SimpleDateFormat("yyyy-MM-dd");

//	public static SimpleDateFormat global_full_sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String DATA_SEPERATOR = "-";

	/*
	 * 计算N天的日期，精确到天
	 */
	public static Date addDay(Date date, int dateNum) {
		if(date == null){
			date = getCurrentDate();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dateNum);
		return calendar.getTime();
	}

	/**
	 * 计算N月的日期，精确到天
	 * @param monthNum
	 * @return
	 */
	public static Date addMonth(Date date, int monthNum) {
		if(date == null){
			date = getCurrentDate();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthNum);
		return calendar.getTime();
	}

	/**
	 * 计算分钟的日期
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		if(date == null){
			date = getCurrentDate();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 计算分钟的日期
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		if(date == null){
			date = getCurrentDate();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}

	/**
	 * 获取服务器系统当前日期
	 * @return
	 */
	public static Date getCurrentDate(){
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
	
	/**
	 * 获取服务器当前时间，如果是 5*x 获取前一分钟
	 * @return
	 */
	public static Date getCurrentDateWithCondition(){
		Calendar calendar = Calendar.getInstance() ;
		int minute = calendar.get(Calendar.MINUTE);
		if(minute % 5 == 0){
			calendar.add(Calendar.MINUTE,-1);
		}
		return calendar.getTime() ;
	}

	public static String getFormatCurrentDateSecond(){
		return dateFormatSecond(null);
	}
	/**
	 * 获取日期 所在月 第N天 日期
	 * @param date
	 * @param monthNum
	 * @return
	 */
	public static Date getDayOfMonth(Date date, int monthNum){
		if(date == null){
			date = getCurrentDate();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, monthNum);
		return calendar.getTime();
	}

	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static String dateFormatDay(Date date){
		if(date == null){
			date = getCurrentDate();
		}
		SimpleDateFormat global_sf = new SimpleDateFormat("yyyy-MM-dd");
		return global_sf.format(date);
	}

	/**
	 * 格式化日期
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateFormatDay(Date date, String pattern){
		if(date == null){
			date = getCurrentDate();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date parseDateSecond(String dataStr){
		Date date = null;
		SimpleDateFormat global_full_sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = global_full_sf.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	public static Date parseDateDay(String dataStr){
		Date date = null;
		SimpleDateFormat global_sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = global_sf.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	public static Date parseDate(String dataStr, String pattern){
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			date = sdf.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 获取当日的n点
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date getSpecificTime(Date date, int hour){
		if(date == null){
			date = getCurrentDate();
		}
		String now0 = dateFormatDay(date); //截取到日
		Date today0 = parseDateDay(now0);
		return addHour(today0, hour);
	}

	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static String dateFormatSecond(Date date){
		SimpleDateFormat global_full_sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(date == null){
			date = getCurrentDate();
		}
		return global_full_sf.format(date);
	}

	/**
	 * 获取一年第n周 最大52
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date){
		if(date == null){
			return Integer.MIN_VALUE;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取n天内的 所有时间段list
	 *
	 * @param date 开始时间
	 * @param dayNum 天数 计算出结束时间
	 * @param minuteInterval 时间间隔 分钟值
	 * @return
	 */
	public static List<Date> getDayMinuteList(Date date, int dayNum, int minuteInterval) {
		Date start = date;
		Date nextDate = DateUtil.addDay(start, dayNum);

		List<Date> result = new ArrayList<>();
		while (start.compareTo(nextDate) < 0) {
			result.add(start);
			start = DateUtil.addMinute(start, minuteInterval);
		}

		return result;
	}

	/**
	 * 计算两个时间间隔 所有时间段list
	 *
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @param hourInterval 时间间隔 分钟值/小时值
	 * @return
	 */
	public static List<Date> getBetweenDateList(Date beginDate, Date endDate, int hourInterval) {
		Date start = beginDate;
		Date nextDate = endDate;

		List<Date> result = new ArrayList<>();
		while (start.compareTo(nextDate) < 0) {
			result.add(start);
			start = DateUtil.addHour(start, hourInterval);
		}

		return result;
	}

	/**
	 * 确定查询的日期，当前日期前一天
	 * @param end
	 * @return
	 */
	public static String getEndDate(String end) {
		
		Date current = getCurrentDate() ;
		current = addDay(current,-1) ;
		Date endDate = null ;
		if(StringUtils.isEmpty(end)|| StringUtils.isBlank(end)){
			endDate = current ;
		}else{
			endDate = parseDateDay(end);
		}
		
		if(endDate.after(current)){
			return  dateFormatDay(current) ;
		}else{
			return dateFormatDay(endDate) ;
		}
	}
	
	/**
	 * 确定查询日期
	 * @param start
	 * @return
	 */
	public static String getStartDate(String start) {
		if(StringUtils.isBlank(start) || StringUtils.isEmpty(start)){
			Date current = getCurrentDate() ;
			current = addDay(current,-14) ;
			start = dateFormatDay(current);
		}
		return start;
	}

	/**
	 *  2015-07-17 -> 07-17
	 * @param dstring
	 * @return
	 */
	public static String formatMonth(String dstring) {
		return dateFormatDay(parseDate(dstring,"yyyy-MM-dd"),PATTERN_MONTH_DAY);
	}



	public static void main(String[] args) throws ParseException {
		System.out.println();
		for(Date d : getBetweenDateList(new Date(), addDay(new Date(), 1), 1)){
			System.out.println(dateFormatSecond(d));
		}
	}

	
	
	


}
