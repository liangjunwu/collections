package com.ljw.ssme.dao;

import java.util.List;
import java.util.Map;

import com.ljw.ssme.beans.pojo.Goods;
import com.ljw.ssme.beans.querybean.GoodsQuery;

public interface GoodsDao {
	
	public int getTotalCount(GoodsQuery query);
	
	public List<Goods> getPageData(Map<String,Object> map);
	
	public List<Goods> getGoods();
	
	public void addGoods(Goods goods);
	
	public List<Goods> getGoodsById(String gid);
	
	public void editGoods(Goods goods);
	
	public void deleteGoods(String[] id);

}
