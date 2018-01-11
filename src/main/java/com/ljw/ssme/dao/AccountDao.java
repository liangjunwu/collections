package com.ljw.ssme.dao;

import java.util.List;
import java.util.Map;

import com.ljw.ssme.beans.pojo.Account;

public interface AccountDao {

	public List<Account> getAllPlatform();
	
	public int getTotalCount();
	
	public List<Account> getAccountData(Map<String,Object> map);
	
	public void addAccount(Account account);
	
	public List<Account> findAccountByName(String accountName);
}
