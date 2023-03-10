package com.java.expressage.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java.expressage.po.Borrowing;
import com.java.expressage.po.User;


public interface BorrowingMapper {
	/**
	 * 查询借阅记录
	 * @param book
	 * @return
	 */
	List<Borrowing> findBorrowingBook(Integer bookid);
	/**
	 * 添加借阅记录
	 * @param book
	 */
	void borrowingBook(Borrowing book);
	/**
	 * 更新借阅记录
	 * @param book
	 * @return
	 */
    void updateBorrowingBook(Borrowing book);
	/**
	 * 归还图书
	 */
	void returnBook(Borrowing returnbook);
	/**
	 * 删除记录根据bookid
	 * @param id
	 */
	void deleteBorrowing(Integer bookid);
	/**
	 * 分页显示
	 * @param pageNum
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	int getBorrowingByKeywordCount(@Param("keyword")String keyword);

	List<Borrowing> getBorrowingByKeyword(@Param("begin")int begin, @Param("pageSize")int pageSize,@Param("keyword") String keyword);
	/**
	 * 通过bookid修改借阅记录
	 * @param borrowing
	 */
//	void updateBorrowingByBookId(Borrowing borrowing);
	/**
	 * 删除记录根据id
	 * @param id
	 */
	void deleteBorrowingById(Integer id);
	/**
	 * 分页显示admin
	 * @param pageNum
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	int getBorrowingByKeywordCountadmin(@Param("keyword")String keyword);
	List<Borrowing> getBorrowingByKeywordadmin(@Param("begin")int begin, @Param("pageSize")int pageSize,@Param("keyword") String keyword);
	/**
	 * 通过usernumber查询借阅记录
	 * @param borrowing
	 */
	List<Borrowing> findBorrowingNumber(String usernumber);
	
	
}
