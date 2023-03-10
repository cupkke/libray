package com.java.expressage.mapper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import com.java.expressage.po.Book;
import com.java.expressage.po.User;

public interface BookMapper {
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
	 * 分页显示
	 * @return
	 */
	int getBookByKeywordCount(@Param("keyword")String keyword);
	List<Book> getBookByKeyword(@Param("begin")int begin, @Param("pageSize")int pageSize,@Param("keyword") String keyword);
	/**
	 * 根据id查找图书
	 */
	Book findBookById(String id);
	/**
	 * 分页显示
	 * @return
	 */
	int getBookByKeywordCounttwo(@Param("keyword")String keyword);
	List<Book> getBookByKeywordtwo(@Param("begin")int begin, @Param("pageSize")int pageSize,@Param("keyword") String keyword);
	/**
	 * 根据书名和作者查找图书
	 */
	Book findBookByBnameAnd(Book book);
}
