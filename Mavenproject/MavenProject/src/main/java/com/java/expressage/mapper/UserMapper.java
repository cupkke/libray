package com.java.expressage.mapper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import com.java.expressage.po.User;

public interface UserMapper {
	//添加用户
	void addUser(User user);

	void findUserByUserNumber(String[] usernumber);
	//用户登录
	User login(String usernumber);
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
	int getUserByKeywordCount(@Param("keyword")String keyword);

	List<User> getUserByKeyword(@Param("begin")int begin, @Param("pageSize")int pageSize,@Param("keyword") String keyword);
	/**
	 * 添加用户学号表
	 * @param usernumber
	 * @param username
	 */
	void addUserNumber(User user1);
	/**
	 * 删除用户账号姓名
	 * @param user
	 */
	void deleteUserNumber(User user);
	/**
	 * 修改用户账号姓名
	 * @param user
	 */
	void updateUserNumber(User user);
	}
