package com.java.expressage.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.java.expressage.exception.lnvaildParamException;
import com.java.expressage.mapper.AdminMapper;
import com.java.expressage.mapper.BookMapper;
import com.java.expressage.mapper.BorrowingMapper;
import com.java.expressage.mapper.UserMapper;
import com.java.expressage.po.Book;
import com.java.expressage.po.Borrowing;
import com.java.expressage.po.User;
import com.java.expressage.uilt.D;
import com.java.expressage.uilt.E;
import com.java.expressage.web.Page;




public class BookServiceImpl implements BookService{
	private BookMapper bookMapper;
	private BorrowingMapper borrowingMapper;
	public BookServiceImpl() {
		SqlSession session = D.getConn();
		bookMapper = session.getMapper(BookMapper.class);
		borrowingMapper = session.getMapper(BorrowingMapper.class);
	}
	/**
	 * 添加图书
	 * @param book
	 */
	public void addBook(Book book) {
		Book bookAnd = bookMapper.findBookByBnameAnd(book);
		if(bookAnd != null) {
			throw new lnvaildParamException(E.SELF_DEFINE_ERROR_CODE,"该书已存在！");
		}
		bookMapper.addBook(book);
	}
	/**
	 * 修改图书信息
	 * @param req
	 * @param resp
	 */
	public void updateBookData(Book book) {
		bookMapper.updateBookData(book);

	}
	/**
	 * 删除图书信息
	 * @param req
	 * @param resp
	 */
	public void deleteBook(Book book) {
		bookMapper.deleteBook(book);
		List<Borrowing> borrowing =  borrowingMapper.findBorrowingBook(book.getId());
		if(borrowing != null)
		borrowingMapper.deleteBorrowing(book.getId());
	}
	/**
	 * 根据id查找图书
	 */
	public Book findBookById(String id) {
		Book book = bookMapper.findBookById(id);
		return book;
	}

	/**
	 * 借阅图书
	 */
	public void borrowingBook(Borrowing borrowing) {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());
        borrowing.setDate(date);
		Book book = new Book();
		book.setId(borrowing.getBookid());
		Book bookSelect = bookMapper.findBookById(borrowing.getBookid().toString());
		int amount = bookSelect.getAmount()-1;
		if(amount < 0) {
			throw new lnvaildParamException(E.SELF_DEFINE_ERROR_CODE,"本书已全部借出！");
		}
		List<Borrowing> bor = borrowingMapper.findBorrowingNumber(borrowing.getUsernumber());
		if(bor.size()>=10) {
			throw new lnvaildParamException(E.SELF_DEFINE_ERROR_CODE,"已借图书达到十本！");
		}
		    book.setAmount(amount);
		    bookMapper.updateBookData(book);
			borrowingMapper.borrowingBook(borrowing);
	}
	/**
	 * 归还图书
	 */
	@Override
	public void returnBook(Borrowing returnbook) {
		Book book = new Book();
		book.setId(returnbook.getBookid());
		Book bookSelect = bookMapper.findBookById(returnbook.getBookid().toString());
		int amount = bookSelect.getAmount()+1;
		book.setAmount(amount);
		bookMapper.updateBookData(book);
		borrowingMapper.deleteBorrowingById(returnbook.getId());
		
	}
	/**
	 * 分页显示数据
	 */
	public Page<Book> list(int pageNum, int pageSize, String keyword,String home) {
		String homeinfo = "home";
		int total,begin;
		List<Book> datas;
		if(homeinfo.equals(home)) {
			 total = bookMapper.getBookByKeywordCounttwo(keyword);
			 begin = (pageNum - 1) * pageSize;
			 datas = bookMapper.getBookByKeywordtwo(begin,pageSize,keyword);
		}else {
			 total = bookMapper.getBookByKeywordCount(keyword);
		     begin = (pageNum - 1) * pageSize;
		     datas = bookMapper.getBookByKeyword(begin,pageSize,keyword);
		}
		
		
		Page<Book> pageData = new Page<Book>(datas,total,pageSize,pageNum);
		return pageData;
	}

	}


