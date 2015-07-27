package com.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.bean.BeanUtil;
import com.bean.ReflectUtil;

public class Test {

	/**
	 * 获取当前日期所在周
	 * @param day
	 * @return
	 */
	public static String parseWeek(String day) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
	}
	
	/**
	 * 获取给定日期月
	 * @param day
	 * @return
	 */
	public static String parseMonth(String day){
		return day.substring(0,day.lastIndexOf("-"));
	}
	
	/**
	 * 获取给定日期年
	 * @param day
	 * @return
	 */
	public static String parseYear(String day){
		return day.substring(0,day.indexOf("-"));
	}
	

	/**
	 * 转换日期 为 周 、 月 、 年
	 * @param date
	 * @return
	 */
	public Map<String, String> parseDate(List<String> days, int type) {

		Map<String, String> map = new HashMap<String, String>();
		
		for(String day:days){
			// 周
			if (type == 1) {
				map.put(day,parseWeek(day));
			}

			// 月
			if (type == 2) {
				map.put(day, parseMonth(day));
			}

			// 年
			if (type == 3) {
				map.put(day, parseYear(day));
			}
		}
		
		return map;

	}
	
	
	/**
	 * 求和
	 * @param v1
	 * @param v2
	 * @return
	 */
	public String sum(Class<?> cls,String field,Object v1,Object v2){
		Method getMethod = BeanUtil.getGetMethod(cls,field);
		Class<?> returnType = getMethod.getReturnType();
		String str = returnType.toString() ;
		if (str.indexOf("Integer") > -1 || str.indexOf("int") > -1) {
			int tmpV1 = Integer.parseInt(String.valueOf(v1));
			int tmpV2 = Integer.parseInt(String.valueOf(v2));
			int sum = tmpV1 + tmpV2 ;
			return String.valueOf(sum) ;
		}
		if (str.indexOf("Double") > -1 || str.indexOf("double") > -1) {
			double tmpV1 = Double.parseDouble(String.valueOf(v1));
			double tmpV2 = Double.parseDouble(String.valueOf(v2));
			double sum = tmpV1 + tmpV2 ;
			return String.valueOf(sum) ;
		}

		return "0" ;
	}

	// 在list 汇总 日期是 date fileds reqNum,finishNum
	public <T> List<T> aggregate(List<T> param, int type, String fields) {

		List<T> newList = new ArrayList<T>();

		List<String> days = new ArrayList<String>();
		
		String [] fieldArray = fields.split(",");
		
		String dayField = "date" ;

		Map<String, String> dateMap = new HashMap<String, String>();

		for (T t : param) {
			days.add(String.valueOf(ReflectUtil.getFieldValue(t, dayField)));
		}

		dateMap = parseDate(days,type);
		
		Map<String,T> resultMap = new LinkedHashMap<String,T>();

		for (T t : param) {
			String day = String.valueOf(ReflectUtil.getFieldValue(t,dayField));
			String value = dateMap.get(day); // day 转换后的 周、月、 年
			if(resultMap.containsKey(value)){
				T tmp = resultMap.get(value) ;
				for(String field:fieldArray){ 
					Object v1 = ReflectUtil.getFieldValue(t, field);
					Object v2 = ReflectUtil.getFieldValue(tmp,field);
					String v3 = sum(t.getClass(),field,v1,v2); // 求和 以后再说
					BeanUtil.assignment(tmp, field, v3);
				}
			}else{
				T tmp = null ;
				try {
					tmp = (T)t.getClass().newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				try {
					BeanUtils.copyProperties(tmp,t);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				ReflectUtil.setFieldValue(tmp,dayField,value);
				resultMap.put(value, tmp);
			}
		}

		for(T t : resultMap.values()){
			 newList.add(t);
		}
		
		return newList;
	}

	public static void main(String[] args) throws Exception {

		Student s1 = new Student("2015-07-01", 20, 18);
		Student s2 = new Student("2015-07-02", 20, 18);
		Student s3 = new Student("2015-07-03", 20, 18);
		Student s4 = new Student("2015-07-04", 20, 18);
		Student s5 = new Student("2015-07-05", 20, 18);
		Student s6 = new Student("2015-08-06", 20, 18);
		Student s7 = new Student("2015-08-07", 20, 18);
		Student s8 = new Student("2015-08-07", 20, 18);
		List<Student> list = new ArrayList<Student>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		list.add(s6);
		list.add(s7);
		List<Student> newList = new Test().aggregate(list,1, "reqNum,finishNum");
		
		for(Student s:newList){
			System.out.println(s);
		}

	}
}
