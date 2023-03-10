package com.java.expressage.web;

import java.util.List;

public class Page<T> {
	//当前页码
	private int curPage;
	//每页都多少条数据
	private int pageSize;
	//一共有多少条数据
	private int totalPage;
	//一共多少页
	private int totalSize;
	//当前数据
	private List<T> data;
	
	public Page(List<T> data, int totalSize, int pageSize, int curPage) {
		this.data = data;
		this.totalPage = totalSize;
		this.pageSize = pageSize;
		this.curPage = curPage;
		this.totalPage = (totalSize % pageSize == 0) ? (totalSize / pageSize) :(totalSize / pageSize) + 1;
	}
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
