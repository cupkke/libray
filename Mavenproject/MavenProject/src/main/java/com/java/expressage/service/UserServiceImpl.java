package com.java.expressage.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.java.expressage.exception.lnvaildParamException;
import com.java.expressage.mapper.AdminMapper;
import com.java.expressage.mapper.UserMapper;
import com.java.expressage.po.User;
import com.java.expressage.uilt.D;
import com.java.expressage.uilt.E;
import com.java.expressage.web.Page;




public class UserServiceImpl implements UserService{
	private UserMapper userMapper;
	private AdminMapper adminMapper;
	public UserServiceImpl() {
		SqlSession session = D.getConn();
		userMapper = session.getMapper(UserMapper.class);
		adminMapper = session.getMapper(AdminMapper.class);
	}
	//添加用户
	public void addUser(User user) {
		User number = userMapper.login(user.getUsernumber());
		if(number != null) {
			throw new lnvaildParamException(E.SELF_DEFINE_ERROR_CODE,"该学号已存在！");
		}
		userMapper.addUser(user);
		String usernumber = user.getUsernumber();
		String username = user.getUsername();
		System.out.println(usernumber+"======================================"+username);
		User user1 = new User();
		user1.setUsername(username);
		user1.setUsernumber(usernumber);
		userMapper.addUserNumber(user1);
	}

	public User findUserByUserNumber(String usernumber) {
		User user = userMapper.login(usernumber);
		if(user == null) {
			user = adminMapper.login(usernumber);
		}
		return user;
		
	}
	//用户登录
	public User login(String usernumber, String password) {
		User user = userMapper.login(usernumber);
		if(user == null) {
			user = adminMapper.login(usernumber);
			if(user == null) {
				throw new lnvaildParamException(E.INVALID_PARAM_ERROR_CODE, E.INVALID_PARAM_ERROR_INFO);
			}
		}
		    //md5加密
				String teacherGetPassword=user.getPassword();
			//密码不相等
			if(!teacherGetPassword.equalsIgnoreCase(password)) {
				throw new lnvaildParamException(E.USER_INFO_ERROR_CODE,E.USER_INFO_ERROR_INFO);
		}
		return user;
	}
	/**
	 * 修改用户数据
	 * @param user
	 */
	public void updateUserData(User user) {
		userMapper.updateUserData(user);
		userMapper.updateUserNumber(user);
		
	}
	/**
	 * 删除用户
	 * @param user
	 */
	public void deleteUser(User user) {
		userMapper.deleteUser(user);
		userMapper.deleteUserNumber(user);
	}
	/**
	 * 分页显示
	 * @param pageNum
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	public Page<User> list(int pageNum, int pageSize, String keyword) {
		int total = userMapper.getUserByKeywordCount(keyword);
		int begin = (pageNum - 1) * pageSize;
		List<User> datas = userMapper.getUserByKeyword(begin,pageSize,keyword);
		
		Page<User> pageData = new Page<User>(datas,total,pageSize,pageNum);
		return pageData;
	}

}
