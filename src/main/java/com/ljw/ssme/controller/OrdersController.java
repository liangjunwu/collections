package com.ljw.ssme.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljw.ssme.beans.common.DataGrid;
import com.ljw.ssme.beans.common.Json;
import com.ljw.ssme.beans.common.Page;
import com.ljw.ssme.beans.pojo.Orders;
import com.ljw.ssme.beans.pojo.Platform;
import com.ljw.ssme.beans.querybean.OdersQuery;
import com.ljw.ssme.service.OrdersService;

@Controller
@RequestMapping("/orders/")
public class OrdersController {
	
	@Resource
	private OrdersService ordersService;
	
	/**
	 * 到orders信息的主页面
	 * @return
	 */
	@RequestMapping("/toList") 
	public String toList(){
		return "/views/orders/ordersList";
	}
	
	/**
	 * easyui-datagrid 接受的数据： {"total":234, "rows":[{"order_id":1, "create_time":"", "pay_type":"wx", ... }, {}, {}]}
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object getOrders(OdersQuery query, Page page) {
		DataGrid<Orders> dataGrid = new DataGrid<Orders>();
		List<Orders> list = ordersService.getPageData(query, page);
		int totalCount = ordersService.getTotalCount(query);
		dataGrid.setRows(list);
		dataGrid.setTotal(totalCount);
		
		return dataGrid;
	}
	
	@RequestMapping(value="getPlatform")
	@ResponseBody
	public List<Platform> getPlatform(){
		List<Platform> list = new ArrayList<Platform>();
		Platform p = new Platform();
		p.setValue("uc");
		p.setName("UC");
		Platform p1 = new Platform();
		p1.setValue("Saturn");
		p1.setName("土星");
		Platform p2 = new Platform();
		p2.setValue("vivo");
		p2.setName("Vivo");
		Platform p3 = new Platform();
		p3.setValue("xiaomi");
		p3.setName("小米");
		Platform p4 = new Platform();
		p4.setValue("oppo");
		p4.setName("Oppo");
		Platform p5 = new Platform();
		p5.setValue("huawei");
		p5.setName("华为");
		Platform p6 = new Platform();
		p6.setValue("wandoujia");
		p6.setName("豌豆荚");
		Platform p7 = new Platform();
		p7.setValue("Saturn_rapid");
		p7.setName("官方土星");
		Platform p8 = new Platform();
		p8.setValue("lianxiang");
		p8.setName("联想");
		Platform p9 = new Platform();
		p9.setValue("jinli");
		p9.setName("金立");
		Platform p10 = new Platform();
		p10.setValue("sougou");
		p10.setName("搜狗");
		list.add(p);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		list.add(p8);
		list.add(p9);
		list.add(p10);
		return list;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object addOrders(Orders order){
		Json json = new Json();
		try{
			ordersService.addOrder(order);
			json.setObj("数据添加成功");
			json.setStatus(1); //1表示添加成功状态
			json.setSuccess(true);
		}catch(Exception ex){
			ex.printStackTrace();
			json.setObj("数据添加失败，请检查数据是否填写正确");
			json.setStatus(-1); //1表示添加成功状态
			json.setSuccess(false);
		}
		return json;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Object editOrders(Orders order){
		Json json = new Json();
		try{
			ordersService.editOrder(order);
			json.setObj("数据编辑成功");
			json.setStatus(1); //1表示添加成功状态
			json.setSuccess(true);
		}catch(Exception ex){
			ex.printStackTrace();
			json.setObj("数据编辑失败");
			json.setStatus(-1); //1表示添加成功状态
			json.setSuccess(false);
		}
		return json;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public Object deleteOrders(String ids){
		String[] idsArray = ids.split(",");  //将前端的字符串拆分为字符串数组
		Json json = new Json();
		try{
			ordersService.deleteOrder(idsArray);
			json.setObj("删除成功");
			json.setStatus(1); //1表示添加成功状态
			json.setSuccess(true);
		}catch(Exception ex){
			ex.printStackTrace();
			json.setObj("数据删除失败，请联系管理员");
			json.setStatus(-1); //1表示添加成功状态
			json.setSuccess(false);
		}
		return json;
	}
}
