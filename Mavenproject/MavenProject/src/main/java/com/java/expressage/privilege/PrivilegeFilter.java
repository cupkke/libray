package com.java.expressage.privilege;

import java.util.ArrayList;
import java.util.List;

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



/**
 * 权限验证
 * @author Dell
 *
 */
public class PrivilegeFilter implements MyFilter{
	private static List<String> users;
	private  Logger log = LogManager.getLogger(PrivilegeFilter.class);
	public PrivilegeFilter() {
		this.users = new ArrayList<String>();
		users.add("user/finduser");
		users.add("book/borrowing");
		users.add("book/returnBook");
		users.add("book/list");
		users.add("book/listhome");
		users.add("borrowing/list");

		
	}
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, MyFilterChain chain) {
		String reqPath = (String) req.getAttribute(Global.REQ_PATH);
		String sid = (String) req.getAttribute("token");
		HttpSession session = SessionManager.getSession(sid);
		User user = (User) session.getAttribute("userinfo");
		String role = user.getRole();
	
		if(!Global.ROLE_User.equals(role)) {
			throw new NoPrivilegeException(E.NO_PRIVILEGE_ERROR_CODE, E.NO_PRIVILEGE_ERROR_INFO);
		}
		if(!users.contains(reqPath)) {
			throw new NoPrivilegeException(E.NO_PRIVILEGE_ERROR_CODE, E.NO_PRIVILEGE_ERROR_INFO);
		}
		log.info("通过权限验证");
		chain.doFilter(req, resp);
	}

}
