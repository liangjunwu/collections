package com.ljw.ssme.beans.common;

/**
 * 采用easyui的自动分页，easyui会自动将这两个数据传入过来
 */
public class Page {
	private int page;  //第几页
	private int rows; //每页数据
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
}
