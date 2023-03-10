package com.java.expressage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.expressage.po.User;
import com.java.expressage.service.UserService;
import com.java.expressage.service.UserServiceImpl;
import com.java.expressage.uilt.SessionManager;
import com.java.expressage.uilt.V;
import com.java.expressage.web.Page;


public class UserController {
	private UserService userService;
	public UserController() {
		this.userService = new UserServiceImpl();
	}
	/**
	 * 登陆
	 * @param req
	 * @param resp
	 * @return
	 */
	public Map<String,String> login(HttpServletRequest req, HttpServletResponse resp) {
		//验证用户名和密码是否为空
				String[] params= {"login_name","password"};
				V.valid(req, params);
				//获取用户名和密码
				String usernumber=req.getParameter("login_name");
				String password=req.getParameter("password");
				
				
				//登陆操作
				User user=userService.login(usernumber, password);
				//存入session
				HttpSession session=req.getSession();
				session.setAttribute("userinfo", user);
				//将session保存起来
				SessionManager.saveSession(session);
				//向用户告知登陆成功，并返回一个令牌
				Map<String,String>map=new HashMap<String, String>();
				map.put("token", session.getId());
				map.put("username", user.getUsername());
				return map;
	}
	/**
	 * 添加用户
	 * @param req
	 * @param resp
	 */
	public void add(HttpServletRequest req, HttpServletResponse resp) {
		String[] params = {"username","password","gender","usernumber","age","classinfo"};
		V.valid(req, params);
		User user = V.entity(req, User.class, params);
		userService.addUser(user);
	}
	/**
	 * 修改用户数据
	 * @param req
	 * @param resp
	 */
	public void updateUserData(HttpServletRequest req, HttpServletResponse resp) {
		String[] params = {"username","password","gender","usernumber","age"};
		System.out.println("修改用户数据");
		V.valid(req, params);
		User user = V.entity(req, User.class, params);
		userService.updateUserData(user);
	}
	/**
	 * 删除用户数据
	 * @param req
	 * @param resp
	 */
	public void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
		String[] params = {"usernumber"};
		V.valid(req, params);
		User user = V.entity(req, User.class, params);
		userService.deleteUser(user);
	}
	/**
	 * 查询个人信息
	 * @param req
	 * @param resp
	 */
	public User finduser(HttpServletRequest req, HttpServletResponse resp) {
		String[] usernumberinfo = {"usernumber"};
		V.valid(req, usernumberinfo);
		String usernumber = req.getParameter("usernumber");
		return userService.findUserByUserNumber(usernumber);
	}
	/**
	 * 分页显示数据
	 */
	
	public Page<User> list(HttpServletRequest req, HttpServletResponse resp) {
		String pageNumStr = V.getValue(req,"page_num" , "1");
		String pageSizeStr = V.getValue(req,"page_size" , "10");
		String keyword = V.getValue(req,"keyword" , "");
		
		int pageNum = Integer.parseInt(pageNumStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		
		Page<User> data = userService.list(pageNum, pageSize, keyword);
		return data;
	}
	
}
