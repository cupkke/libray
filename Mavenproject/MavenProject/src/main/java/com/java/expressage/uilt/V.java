package com.java.expressage.uilt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.expressage.exception.lnvaildParamException;
import com.java.expressage.uilt.E;
import com.java.expressage.uilt.NameUtil;



public class V {
public static void valid(HttpServletRequest req, String[] keys) {
	for(String key : keys) {
		String value = req.getParameter(key);
		if(value == null || value.length() == 0) {
			throw new lnvaildParamException(E.SELF_DEFINE_ERROR_CODE,key+"不能为空");
		}
	}
}
public static <T> T entity(HttpServletRequest req,Class<?>cls,String[] keys) {
	T instance = null;
	try {
		instance = (T) cls.newInstance();
		for(String key : keys) {
			String value = req.getParameter(key);
			String param = NameUtil.convert2Caml(key);
			//获取指定字段
			Field field = cls.getDeclaredField(param);
			param = NameUtil.firstUpper(param);
			String optName = "set" + param;
			
			//获取字段的数据类型
			Class<?> dataType = field.getType();
			String typeName = dataType.getTypeName();
			Object setValue = null;
			if(typeName.equals("java.lang.Integer")||typeName.equals("int")) {
				setValue = Integer.parseInt(value);
			}else if(typeName.equals("java.lang.Long")||typeName.equals("long")) {
				setValue = Integer.parseInt(value);
			}else if(typeName.equals("java.util.Date")) {
				//转化为日期类型
			}else {
				setValue = dataType.cast(value);
			}
			Method method = cls.getMethod(optName,dataType );
			method.invoke(instance, setValue);
		}
	} catch (Exception e) {
		e.printStackTrace();
		throw new lnvaildParamException(E.SELF_DEFINE_ERROR_CODE,"实例化参数失败");
	}
	
	return instance;
}
/*
 * 获取指定参数
 */
public static String getValue(HttpServletRequest req,String param,String defaultValue) {
	return req.getParameter(param) == null ?  defaultValue : req.getParameter(param);
}
}
