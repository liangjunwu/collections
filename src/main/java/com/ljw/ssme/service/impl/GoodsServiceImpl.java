package com.ljw.ssme.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljw.ssme.beans.common.Page;
import com.ljw.ssme.beans.pojo.Goods;
import com.ljw.ssme.beans.querybean.GoodsQuery;
import com.ljw.ssme.dao.GoodsDao;
import com.ljw.ssme.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private GoodsDao goodsDao;

	@Override
	public int getTotalCount(GoodsQuery query) {
		int totalCount = goodsDao.getTotalCount(query);
		return totalCount;
	}

	@Override
	public List<Goods> getPageData(GoodsQuery query, Page page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goods_name", query.getGoods_name());
		map.put("min_price", query.getMin_price());
		map.put("max_price", query.getMax_price());
		int pageSize = page.getRows();
		map.put("index", (page.getPage() - 1)*pageSize);
		map.put("pageSize", pageSize);
		List<Goods> list = goodsDao.getPageData(map);
		return list;
	}

	@Override
	public List<Goods> getGoods() {
		List<Goods> goodsList = goodsDao.getGoods();
		return goodsList;
	}

	@Override
	public void addGoods(Goods goods) {
		goodsDao.addGoods(goods);
	}

	@Override
	public List<Goods> getGoodsById(String gid) {
		return goodsDao.getGoodsById(gid);
	}

	@Override
	public void editGoods(Goods goods) {
		goodsDao.editGoods(goods);
	}

	@Override
	public void deleteGoods(String ids) {
		String[] id = ids.split(",");
		goodsDao.deleteGoods(id);
	}

}
