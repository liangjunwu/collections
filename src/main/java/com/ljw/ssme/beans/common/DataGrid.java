package com.ljw.ssme.beans.common;

import java.util.List;

/**
 *  {"total":234, "rows":[{"order_id":1, "create_time":"", "pay_type":"wx", ... }, {}, {}]}
 * @param <T>
 */
public class DataGrid<T> {
	private int total;  //总数据量
	private List<T> rows; //每页的数据
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
