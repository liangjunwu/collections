package com.ljw.ssme.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ljw.ssme.beans.pojo.Menu;
import com.ljw.ssme.dao.MenusDao;
import com.ljw.ssme.service.MenusService;

@Service
@Transactional
public class MenusServiceImpl implements MenusService {

	@Resource
	private MenusDao menusDao;
	
	public List<Menu> getMenus() {
		return menusDao.getMenus();
	}

	public List<Menu> treeMenu() {
		return menusDao.treeMenus();
	}
}
