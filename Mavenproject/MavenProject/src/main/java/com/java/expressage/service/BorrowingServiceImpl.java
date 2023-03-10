package com.java.expressage.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.java.expressage.exception.lnvaildParamException;
import com.java.expressage.mapper.AdminMapper;
import com.java.expressage.mapper.BorrowingMapper;
import com.java.expressage.mapper.UserMapper;
import com.java.expressage.po.Borrowing;
import com.java.expressage.po.User;
import com.java.expressage.uilt.D;
import com.java.expressage.uilt.E;
import com.java.expressage.web.Page;




public class BorrowingServiceImpl implements BorrowingService{
	private BorrowingMapper borrowingMapper;
	public BorrowingServiceImpl() {
		SqlSession session = D.getConn();
		borrowingMapper = session.getMapper(BorrowingMapper.class);
	}
	
	/**
	 * 分页显示
	 * @param pageNum
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	public Page<Borrowing> list(int pageNum, int pageSize, String keyword) {
		int total = borrowingMapper.getBorrowingByKeywordCount(keyword);
		int begin = (pageNum - 1) * pageSize;
		List<Borrowing> datas = borrowingMapper.getBorrowingByKeyword(begin,pageSize,keyword);
		Page<Borrowing> pageData = new Page<Borrowing>(datas,total,pageSize,pageNum);
		return pageData;
	}

	@Override
	public Page<Borrowing> listAdmin(int pageNum, int pageSize, String keyword) {
		int total = borrowingMapper.getBorrowingByKeywordCountadmin(keyword);
		int begin = (pageNum - 1) * pageSize;
		List<Borrowing> datas = borrowingMapper.getBorrowingByKeywordadmin(begin,pageSize,keyword);
		Page<Borrowing> pageData = new Page<Borrowing>(datas,total,pageSize,pageNum);
		return pageData;
	}

}
