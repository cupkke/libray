package com.java.expressage.privilege;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.java.expressage.exception.NoPrivilegeException;
import com.java.expressage.po.User;
import com.java.expressage.uilt.E;
import com.java.expressage.uilt.Global;
import com.java.expressage.uilt.SessionManager;
import com.java.expressage.web.MyFilter;
import com.java.expressage.web.MyFilterChain;
import com.sun.javafx.collections.MappingChange.Map;

/**
 * 用来验证用户是否登录
 * @author Dell
 *
 */
public class LoginFilter implements MyFilter{

	private static Logger log = LogManager.getLogger(Logger.class);
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {
		log.info("登陆验证");
		Enumeration<String> headers = req.getHeaderNames();
		while (headers.hasMoreElements()) {
			String key = (String) headers.nextElement();
			System.out.println(key + ":" + req.getHeader(key));
		}
		//获取token
		String token = req.getParameter("token");
		
		if(token == null) {
			Cookie[] cookies = req.getCookies();
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("JSESSIONID")) {
						token = cookie.getValue();
						break;
					}
				}
			}
			
		}
		log.info("获取token="+token);
		//验证token的合法性
		if(token == null) {
			throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE, "请登录...");
		}
	   HttpSession session = SessionManager.getSession(token);
		if(session == null) {
			throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE, "请登录...");
		}
		//验证session是否过期
		long LastAccessedTime =	session.getLastAccessedTime();
		long cueTime = System.currentTimeMillis();
		if((cueTime - LastAccessedTime) >= (session.getMaxInactiveInterval() * 1000)) {
			SessionManager.remove(token);
			throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE, "请登录...");
		}
		
		User user = (User) session.getAttribute("userinfo");
		if(user == null) {
			throw new NoPrivilegeException(E.SELF_DEFINE_ERROR_CODE, "登录过期，请重新登录...");
		}
		//存储token
		req.setAttribute("token",token);
				
		String role = user.getRole();
		if(Global.ROLE_ADMIN.equals(role)) {
			log.info("管理员权限，无需验证");
			return ;
		}
		
		//调用后继过滤器
		chain.doFilter(req, resp);
	}
	
	
}
