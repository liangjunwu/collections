package com.ljw.ssme.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljw.ssme.beans.pojo.Menu;
import com.ljw.ssme.service.MenusService;

@Controller
@RequestMapping("menus/")
public class MenuController {
	
	@Resource
	private MenusService menusServiceImpl; 
	
	/**
	 * 手风琴菜单的 请求
	 * @return
	 */
	@RequestMapping("menu")
	@ResponseBody
	public Object getMenus(){
		List<Menu> list = menusServiceImpl.getMenus();
		return list;
	}
	
	/**
	 * tree的请求
	 * @return
	 */
	@RequestMapping("treeMenu")
	@ResponseBody
	public Object treeMenu(){
		List<Menu> list = menusServiceImpl.treeMenu();
		return list;
	}
}
