package com.java.expressage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.expressage.po.Book;
import com.java.expressage.po.Borrowing;
import com.java.expressage.po.User;
import com.java.expressage.service.BookService;
import com.java.expressage.service.BookServiceImpl;
import com.java.expressage.service.BorrowingService;
import com.java.expressage.service.BorrowingServiceImpl;
import com.java.expressage.uilt.V;
import com.java.expressage.web.Page;

public class BorrowingController {
	private BorrowingService borrowingService;
	public BorrowingController() {
		this.borrowingService = new BorrowingServiceImpl();
	}
	/**
	 * 分页显示数据
	 */
	
	public Page<Borrowing> list(HttpServletRequest req, HttpServletResponse resp) {
		String pageNumStr = V.getValue(req,"page_num" , "1");
		String pageSizeStr = V.getValue(req,"page_size" , "10");
		String keyword = V.getValue(req,"keyword" , "");
		
		int pageNum = Integer.parseInt(pageNumStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		Page<Borrowing> data = borrowingService.list(pageNum, pageSize, keyword);
		return data;
	}
	/**
	 * 分页显示数据
	 */
	
	public Page<Borrowing> listadmin(HttpServletRequest req, HttpServletResponse resp) {
		String pageNumStr = V.getValue(req,"page_num" , "1");
		String pageSizeStr = V.getValue(req,"page_size" , "10");
		String keyword = V.getValue(req,"keyword" , "");
		
		int pageNum = Integer.parseInt(pageNumStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		
		Page<Borrowing> data = borrowingService.listAdmin(pageNum, pageSize, keyword);
		return data;
	}
}
