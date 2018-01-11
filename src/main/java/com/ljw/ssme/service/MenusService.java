package com.ljw.ssme.service;

import java.util.List;

import com.ljw.ssme.beans.pojo.Menu;

public interface MenusService {
	
	public List<Menu> getMenus();
	
	public List<Menu> treeMenu();
}
