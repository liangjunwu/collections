package com.ljw.ssme.dao;

import java.util.List;

import com.ljw.ssme.beans.pojo.Menu;

public interface MenusDao {
	
	public List<Menu> getMenus();
	
	public List<Menu> treeMenus();
}
