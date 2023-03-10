package com.java.expressage.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤链
 * @author Dell
 *
 */
public class MyFilterChain {
	private List<MyFilter> filterChains;
	private int cur;
	public MyFilterChain(){
		this.filterChains =new ArrayList<MyFilter>();
		this.cur = 0;
	}
	/**
	 * 执行过滤链的地方
	 * @param req
	 * @param resp
	 */
	public void doFilter(HttpServletRequest req, HttpServletResponse resp) {
		if(cur < filterChains.size()) {
			MyFilter filter = filterChains.get(cur);
			cur++;
			filter.doFilter(req, resp,this);
		}
	}
	/**
	 * 添加过滤器
	 * @param filter
	 */
	public MyFilterChain addFilter(MyFilter filter) {
		this.filterChains.add(filter);
		return this;
		
	}
}
