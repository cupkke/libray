package com.java.expressage.privilege;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.java.expressage.uilt.Global;
import com.java.expressage.web.MyFilter;
import com.java.expressage.web.MyFilterChain;


/**
 * 白名单验证
 * @author Dell
 *
 */
public class WhiteFilter implements MyFilter{
	private static final List<String> whiltList;
	private Logger log = LogManager.getLogger(WhiteFilter.class);
	static {
		whiltList = new ArrayList<String>();
		whiltList.add("user/login");
	}

	public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {
		String path = (String) req.getAttribute(Global.REQ_PATH);
		if(!whiltList.contains(path)) {
			chain.doFilter(req, resp);
		}else {
			log.info("通过白名单验证...");
		}
	}

}
