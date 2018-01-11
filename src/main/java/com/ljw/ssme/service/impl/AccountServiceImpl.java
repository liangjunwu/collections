package com.ljw.ssme.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljw.ssme.beans.common.Page;
import com.ljw.ssme.beans.pojo.Account;
import com.ljw.ssme.dao.AccountDao;
import com.ljw.ssme.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountDao accountDao;

	@Override
	public List<Account> getAllPlatform() {
		
		return accountDao.getAllPlatform();
	}

	@Override
	public int getTotalCount() {

		return accountDao.getTotalCount();
	}

	@Override
	public List<Account> getAccountData(Page page) {
		Map<String,Object> map = new HashMap<String,Object>();
		int index = (page.getPage() - 1) * page.getRows();
		map.put("index", index);
		map.put("pageSize", page.getRows());

		return accountDao.getAccountData(map);
	}

}
