package com.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;


public class BeanUtil {
	
	/**
	 * 获取 DateField 的 注解
	 * @param cls
	 * @return
	 */
	public static String getPropertyWithAnnotation(Class<?> cls){
		Field[] fields =  cls.getDeclaredFields() ;
		String property = null ;
		for(Field field:fields){
			boolean flag = field.isAnnotationPresent(DateField.class);
			if(flag){
				property = field.getName();
				break ;
			}
		}
		
		return property ;
	}


	/**
	 * 根据 类、属性 获得 set 方法
	 * 
	 * @param cls
	 * @param name
	 * @return
	 */
	public static Method getSetMethod(Class<?> cls, String name) {
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			if (method.getName().equalsIgnoreCase("set" + name)) {
				return method;
			}
		}
		return null;
	}

	/**
	 * 根据 类、属性 获得 get方法
	 * 
	 * @param cls
	 * @param name 
	 * @return
	 */
	public static Method getGetMethod(Class<?> cls, String name) {
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			if (method.getName().equalsIgnoreCase("get" + name)) {
				return method;
			}
		}
		return null;
	}

	/**
	 * 获得所有属性的set方法
	 * 
	 * @param cls
	 * @return
	 */
	public static Map<String, Method> getSetMethod(Class<?> cls) {
		Map<String, Method> map = new HashMap<String, Method>();
		Method[] methods = cls.getMethods();
		if (methods == null || methods.length == 0) {
			return null;
		} else {
			for (Method method : methods) {
				String methodName = method.getName();
				if (methodName.substring(0, 3).equals("set")) {
					String name = methodName.substring(3).toLowerCase();
					String paramType = method.getParameterTypes()[0].getName();
					paramType = paramType.substring(paramType.lastIndexOf(".") + 1);
					map.put(name + "#" + paramType, method);
				}
			}
			return map;
		}
	}

	/**
	 * 获得所有属性的get方法
	 * 
	 * @param cls
	 * @return
	 */
	public static Map<String, Method> getGetMethod(Class<?> cls) {
		Map<String, Method> map = new HashMap<String, Method>();
		Method[] methods = cls.getMethods();
		if (methods == null || methods.length == 0) {
			return null;
		} else {
			for (Method method : methods) {
				String methodName = method.getName();
				if (methodName.substring(0, 3).equals("get")) {
					String name = methodName.substring(3).toLowerCase();
					String returnType = method.getReturnType().getName();
					returnType = returnType.substring(returnType.lastIndexOf(".") + 1);
					if (!returnType.equals("Class")) {
						map.put(name + "#" + returnType, method);
					}
				}
			}
			return map;
		}

	}

	/**
	 * 根据给定的类型,返回 obj 对应类型的值
	 * 
	 * @param cls
	 * @param object
	 * @return
	 */
	public static Object getTypeDefault(Class<?> cls, Object obj) {

		String str = cls.toString();
		if (str.indexOf("String") > -1) {
			return (obj == null ? null : obj.toString());
		}
		if (str.indexOf("Integer") > -1 || str.indexOf("int") > -1) {
			return Integer.valueOf((obj == null ? "0" : obj.toString()));
		}
		if (str.indexOf("Double") > -1 || str.indexOf("double") > -1) {
			return Double.valueOf((obj == null ? "0.0" : obj.toString()));
		}
		if (str.indexOf("Long") > -1 || str.indexOf("long") > -1) {
			return Long.valueOf((obj == null ? "0" : obj.toString()));
		}
		if (str.indexOf("Float") > -1 || str.indexOf("float") > -1) {
			return Float.valueOf((obj == null ? "0.0" : obj.toString()));
		}
		if (str.indexOf("Boolean") > -1 || str.indexOf("boolean") > -1) {
			return Boolean.valueOf((obj == null ? "flase" : obj.toString()));
		}
		if (str.indexOf("Date") > -1) {
			if (obj == null) {
				return new Date();
			} else {
				try {
					return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(obj.toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		throw new RuntimeException("输入的类型非 java 类型");
	}

	/**
	 * 把 map 中 对应的值 赋值到 对象中 
	 * 1. 对应的object 有个 无参的构造方法
	 * 
	 * @param cls
	 * @param params
	 */
	public static Object assignment(Class<?> cls, Map<String, String> params) {
		Assert.notNull(params);
		Object obj = null;
		try {
			obj = cls.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		Set<String> keys = params.keySet();
		for (String key : keys) {
			Method setMethod = getSetMethod(cls, key);
			Method getMethod = getGetMethod(cls, key);
			Class<?> returnType = getMethod.getReturnType();
			try {
				setMethod.invoke(obj, BeanUtil.getTypeDefault(returnType, params.get(key)));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return obj;

	}
	
	/**
	 * 给 对象赋值 , 区分类型
	 * @param obj
	 * @param field
	 * @param value
	 * @return
	 */
	public static Object assignment(Object obj,String field,String value){
		Method setMethod = getSetMethod(obj.getClass(),field);
		Method getMethod = getGetMethod(obj.getClass(),field);
		Class<?> returnType = getMethod.getReturnType();
		try {
			setMethod.invoke(obj, BeanUtil.getTypeDefault(returnType,value));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj ;

	}
	
	

	public static void main(String[] args) throws Exception {

		Map<String, String> params = new HashMap<String, String>();
		params.put("a", "a");
		params.put("b", "1");


	}

}
