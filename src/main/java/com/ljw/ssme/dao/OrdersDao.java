package com.ljw.ssme.dao;

import java.util.List;
import java.util.Map;

import com.ljw.ssme.beans.pojo.Orders;
import com.ljw.ssme.beans.querybean.OdersQuery;


public interface OrdersDao {
	
	/**
	 * 查询分页信息
	 * @return
	 */
	public List<Orders> getPageData(Map<String, Object> map);
	
	/**
	 * 查询总数
	 * @param query
	 * @return
	 */
	public int getTotalCount(OdersQuery query);
	
	/**
	 * 添加订单
	 * @param order
	 */
	public void addOrder(Orders order);
	
	/**
	 * 编辑订单
	 * @param order
	 */
	public void editOrder(Orders order);
	
	/**
	 * 删除订单
	 * @param ids
	 */
	public void deleteOrders(String[] ids);
}
