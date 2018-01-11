package com.ljw.ssme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljw.ssme.beans.common.DataGrid;
import com.ljw.ssme.beans.common.Page;
import com.ljw.ssme.beans.pojo.Account;
import com.ljw.ssme.service.AccountService;

@Controller
@RequestMapping(value="/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="toList")
	public String toList(){
		return "/views/account/accountList";
	}
	
	@RequestMapping(value="getPlatform")
	public List<Account> getAllPlatform(){
		List<Account> list = accountService.getAllPlatform();
		return list;
	}
	
	@RequestMapping(value="getAccountData")
	@ResponseBody
	public Object getAccountData(Page page){
		DataGrid<Account> dataGrid = new DataGrid<Account>();
		int totalCount = accountService.getTotalCount();
		List<Account> accountList = accountService.getAccountData(page);
		dataGrid.setTotal(totalCount);
		dataGrid.setRows(accountList);
		return dataGrid;
	}

}
