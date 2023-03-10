package com.java.expressage.uilt;

public class E {
	//请求路径错误
	public static final int PATH_ERROR_CODE=1002;
	public static final String PATH_ERROR_INFO="请求路径错误";
	//自定义异常信息
	public static final int SELF_DEFINE_ERROR_CODE=1003;
	//参数不合法
	public static final int INVALID_PARAM_ERROR_CODE=1000;
	public static final String INVALID_PARAM_ERROR_INFO="参数不合法";
	//用户名或密码不正确
	public static final int USER_INFO_ERROR_CODE=1001;
	public static final String USER_INFO_ERROR_INFO="用户名或密码不正确";
	//非法访问
	public static final int NO_PRIVILEGE_ERROR_CODE=1004;
	public static final String NO_PRIVILEGE_ERROR_INFO="没有访问权限";
}
