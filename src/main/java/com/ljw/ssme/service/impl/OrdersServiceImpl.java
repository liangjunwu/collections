package com.ljw.ssme.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ljw.ssme.beans.common.Page;
import com.ljw.ssme.beans.pojo.Account;
import com.ljw.ssme.beans.pojo.Orders;
import com.ljw.ssme.beans.querybean.OdersQuery;
import com.ljw.ssme.dao.AccountDao;
import com.ljw.ssme.dao.OrdersDao;
import com.ljw.ssme.service.OrdersService;


@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

	@Resource
	private OrdersDao ordersDao;
	@Resource
	private AccountDao accountDao;
	
	@Override
	public List<Orders> getPageData(OdersQuery query, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pay_type", query.getPay_type());
		map.put("platform", query.getPlatform());
		map.put("account_name", query.getAccount_name());
		map.put("beginTime", query.getBeginTime());
		map.put("endTime", query.getEndTime());
		
		int pageSize = page.getRows(); 
		
		map.put("index", (page.getPage() - 1) * pageSize);
		map.put("pageSize", pageSize);
		
		return ordersDao.getPageData(map);
	}

	public int getTotalCount(OdersQuery query) {
		return ordersDao.getTotalCount(query);
	}


	@Override
	public void deleteOrder(String[] ids) {
		ordersDao.deleteOrders(ids);
	}

	@Override
	public void addOrder(Orders order) {
		List<Account> accountList = accountDao.findAccountByName(order.getAccount_name());
		Integer accountId = null;
		if(accountList.size() <= 0){
			Account account = new Account();
			account.setPlatform(order.getPlatform());
			account.setAccount_name(order.getAccount_name());
			accountDao.addAccount(account);
			
			accountId = account.getId(); //得到Account的Id
		}else{
			accountId = accountList.get(0).getId();
		}
		
		order.setAccount_id(accountId);
		order.setCreate_time(new Date());
		
		ordersDao.addOrder(order);
	}

	@Override
	public void editOrder(Orders order) {
		ordersDao.editOrder(order);
	}
}
