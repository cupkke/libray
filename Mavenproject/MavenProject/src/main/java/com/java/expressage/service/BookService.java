package com.java.expressage.service;

import com.java.expressage.po.Book;
import com.java.expressage.po.Borrowing;
import com.java.expressage.po.User;
import com.java.expressage.web.Page;

public interface BookService {
	/**
	 * 添加图书
	 * @param book
	 */
	void addBook(Book book);
	/**
	 * 修改图书信息
	 * @param req
	 * @param resp
	 */
	void updateBookData(Book book);
	/**
	 * 删除图书信息
	 * @param req
	 * @param resp
	 */
	void deleteBook(Book book);
	/**
	 * 借阅图书
	 */
	void borrowingBook(Borrowing book);
	/**
	 * 归还图书
	 */
	void returnBook(Borrowing returnbook);
	/**
	 * 分页显示数据
	 */
	Page<Book> list(int pageNum, int pageSize, String keyword, String home);
	/**
	 * 根据id查找图书
	 */
	Book findBookById(String id);


}
