package com.java.expressage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义过滤器接口
 * @author Dell
 *
 */
public interface MyFilter {
	public void doFilter(HttpServletRequest req,HttpServletResponse resp,MyFilterChain chain);
}