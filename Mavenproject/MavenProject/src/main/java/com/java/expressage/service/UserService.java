package com.java.expressage.service;

import com.java.expressage.po.User;
import com.java.expressage.web.Page;

public interface UserService {
//添加用户
	void addUser(User user);
//
	User findUserByUserNumber(String usernumber);
	//用户登录
	User login(String usernumber, String password);
	/**
	 * 修改用户数据
	 * @param user
	 */
	void updateUserData(User user);
	/**
	 * 删除用户
	 * @param user
	 */
	void deleteUser(User user);
	/**
	 * 分页显示
	 * @param pageNum
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	Page<User> list(int pageNum, int pageSize, String keyword);
}
