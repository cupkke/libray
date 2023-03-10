package com.java.expressage.service;

import java.util.List;

import com.java.expressage.po.Borrowing;
import com.java.expressage.po.User;
import com.java.expressage.web.Page;

public interface BorrowingService {

	/**
	 * 分页显示
	 * @param pageNum
	 * @param pageSize
	 * @param keyword
	 * @return
	 */
	Page<Borrowing> list(int pageNum, int pageSize, String keyword);

	Page<Borrowing> listAdmin(int pageNum, int pageSize, String keyword);

}
