package com.ljw.ssme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljw.ssme.beans.common.DataGrid;
import com.ljw.ssme.beans.common.Json;
import com.ljw.ssme.beans.common.Page;
import com.ljw.ssme.beans.pojo.Goods;
import com.ljw.ssme.beans.querybean.GoodsQuery;
import com.ljw.ssme.service.GoodsService;

@Controller
@RequestMapping(value="goods")
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value="toList")
	public String toList(){
		return "/views/goods/goodsList";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public Object getGoodsList(GoodsQuery query, Page page) {
		DataGrid<Goods> dataGrid = new DataGrid<Goods>();
		List<Goods> list = goodsService.getPageData(query, page);
		int totalCount = goodsService.getTotalCount(query);
		dataGrid.setRows(list);
		dataGrid.setTotal(totalCount);
		
		return dataGrid;
	}
	
	@RequestMapping("/getGoods")
	@ResponseBody
	public Object getGoods() {
		List<Goods> goodsList = goodsService.getGoods();
		return goodsList;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object addGoods(Goods goods) {
		Json json = new Json();
		List<Goods> list = goodsService.getGoodsById(goods.getGid());
		if(list != null && list.size() > 0){
			json.setObj("商品编号已存在");
			json.setStatus(-1);
			json.setSuccess(false);
		}else{
			try{
				goodsService.addGoods(goods);
				json.setObj("添加数据成功");
				json.setStatus(1);
				json.setSuccess(true);
			}catch(Exception e){
				e.printStackTrace();
				json.setObj("添加数据失败");
				json.setStatus(-1);
				json.setSuccess(false);
			}
		}
		return json;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Object editGoods(Goods goods) {
		Json json = new Json();
		try{
			goodsService.editGoods(goods);
			json.setObj("编辑数据成功");
			json.setStatus(1);
			json.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			json.setObj("编辑数据失败");
			json.setStatus(-1);
			json.setSuccess(false);
		}
		return json;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object deleteGoods(String ids) {
		Json json = new Json();
		try{
			goodsService.deleteGoods(ids);
			json.setObj("删除数据成功");
			json.setStatus(1);
			json.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			json.setObj("删除数据失败");
			json.setStatus(-1);
			json.setSuccess(false);
		}
		return json;
	}

}
