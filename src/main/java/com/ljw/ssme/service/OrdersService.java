package com.ljw.ssme.service;

import java.util.List;

import com.ljw.ssme.beans.common.Page;
import com.ljw.ssme.beans.pojo.Orders;
import com.ljw.ssme.beans.querybean.OdersQuery;


public interface OrdersService {
	/**
	 * 查询分页信息
	 * @return
	 */
	public List<Orders> getPageData(OdersQuery query, Page page);
	
	/**
	 * 查询总数
	 * @param query
	 * @return
	 */
	public int getTotalCount(OdersQuery query);
	
	/**
	 * 添加
	 * @param order
	 */
	public void addOrder(Orders order);
	
	/**
	 * 编辑
	 * @param order
	 */
	public void editOrder(Orders order);
	
	/**
	 * 删除
	 * @param ids
	 */
	public void deleteOrder(String[] ids);
}
