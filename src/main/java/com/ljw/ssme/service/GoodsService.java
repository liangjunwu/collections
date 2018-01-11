package com.ljw.ssme.service;

import java.util.List;

import com.ljw.ssme.beans.common.Page;
import com.ljw.ssme.beans.pojo.Goods;
import com.ljw.ssme.beans.querybean.GoodsQuery;

public interface GoodsService {
	
	public int getTotalCount(GoodsQuery query);
	
	public List<Goods> getPageData(GoodsQuery query, Page page);
	
	/**
	 * 	获取全部商品
	 * @return
	 */
	public List<Goods> getGoods();
	
	public void addGoods(Goods goods);
	
	public List<Goods> getGoodsById(String gid);
	
	public void editGoods(Goods goods);
	
	public void deleteGoods(String ids);

}
