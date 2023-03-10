package com.java.expressage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.expressage.po.Book;
import com.java.expressage.po.Borrowing;
import com.java.expressage.po.User;
import com.java.expressage.service.BookService;
import com.java.expressage.service.BookServiceImpl;
import com.java.expressage.uilt.V;
import com.java.expressage.web.Page;

public class BookController {
	private BookService bookService;
	public BookController() {
		this.bookService = new BookServiceImpl();
	}
	/**
	 * 添加图书
	 * @param req
	 * @param resp
	 */
	public void addBook(HttpServletRequest req, HttpServletResponse resp) {
		String[] params = {"bookname","author","publisher","price","category","bookdesc","amount"};
		V.valid(req, params);
		Book book = V.entity(req, Book.class, params);
		bookService.addBook(book);
	}
	/**
	 * 修改图书信息
	 * @param req
	 * @param resp
	 */
	public void updateBookData(HttpServletRequest req, HttpServletResponse resp) {
		String[] params = {"id","bookname","author","publisher","price","category","bookdesc","amount"};
		V.valid(req, params);
		Book book = V.entity(req, Book.class, params);
		bookService.updateBookData(book);
	}
	/**
	 * 删除图书信息
	 * @param req
	 * @param resp
	 */
	public void deleteBook(HttpServletRequest req, HttpServletResponse resp) {
		String[] params = {"id"};
		V.valid(req, params);
		Book book = V.entity(req, Book.class, params);
		bookService.deleteBook(book);
	}
	/**
	 * 根据id查找图书
	 */
	public Book findBook(HttpServletRequest req, HttpServletResponse resp) {
		String[] idinfo = {"id"};
		V.valid(req, idinfo);
		String id = req.getParameter("id");
		return bookService.findBookById(id);
	}
	/**
	 * 借阅图书
	 */
	public void borrowing(HttpServletRequest req, HttpServletResponse resp) {
		String[] params = {"bookid","bookname","usernumber","username"};
		V.valid(req, params);
		Borrowing book = V.entity(req, Borrowing.class, params);
		bookService.borrowingBook(book);
	}
	/**
	 * 归还图书
	 */
	public void returnBook(HttpServletRequest req, HttpServletResponse resp) {
		String[] params = {"id","bookid"};
		V.valid(req, params);
		Borrowing returnbook = V.entity(req, Borrowing.class, params);
		bookService.returnBook(returnbook);
	}
	/**
	 * 分页显示数据home
	 */
	
	public Page<Book> listhome(HttpServletRequest req, HttpServletResponse resp) {
		String pageNumStr = V.getValue(req,"page_num" , "1");
		String pageSizeStr = V.getValue(req,"page_size" , "10");
		String keyword = V.getValue(req,"keyword" , "");
		
		int pageNum = Integer.parseInt(pageNumStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		String home = "home";
		Page<Book> data = bookService.list(pageNum, pageSize, keyword ,home);
		return data;
	}
	/**
	 * 分页显示数据user
	 */
	
	public Page<Book> list(HttpServletRequest req, HttpServletResponse resp) {
		String pageNumStr = V.getValue(req,"page_num" , "1");
		String pageSizeStr = V.getValue(req,"page_size" , "10");
		String keyword = V.getValue(req,"keyword" , "");
		
		int pageNum = Integer.parseInt(pageNumStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		
		Page<Book> data = bookService.list(pageNum, pageSize, keyword,null);
		return data;
	}
	/**
	 * 分页显示数据admin
	 */
	
	public Page<Book> listadmin(HttpServletRequest req, HttpServletResponse resp) {
		String pageNumStr = V.getValue(req,"page_num" , "1");
		String pageSizeStr = V.getValue(req,"page_size" , "10");
		String keyword = V.getValue(req,"keyword" , "");
		
		int pageNum = Integer.parseInt(pageNumStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		
		Page<Book> data = bookService.list(pageNum, pageSize, keyword,null);
		return data;
	}
}
