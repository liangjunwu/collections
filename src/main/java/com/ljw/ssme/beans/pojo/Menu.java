package com.ljw.ssme.beans.pojo;

import java.util.List;

/**
 * [{"id"1, "url":"", "name":"", "pid":"", "children":[]}, {}, {}]
 * @author Administrator
 *
 */
public class Menu {
	private int id;
	private String name;
	private String url;
	private List<Menu> children;
	private int pid;
	
	private String page;  //
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
}
