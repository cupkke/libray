package com.java.expressage.mapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.expressage.po.User;

public interface AdminMapper {
	User login(String usernumber);
}
