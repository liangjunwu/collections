package com.ljw.ssme.service;

import java.util.List;

import com.ljw.ssme.beans.common.Page;
import com.ljw.ssme.beans.pojo.Account;

public interface AccountService {

	public List<Account> getAllPlatform();
	
	public int getTotalCount();
	
	public List<Account> getAccountData(Page page);
}
