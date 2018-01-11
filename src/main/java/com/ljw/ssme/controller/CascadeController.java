package com.ljw.ssme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljw.ssme.beans.pojo.City;
import com.ljw.ssme.beans.pojo.District;
import com.ljw.ssme.beans.pojo.Province;
import com.ljw.ssme.service.CiytService;
import com.ljw.ssme.service.DistrictService;
import com.ljw.ssme.service.ProvinceService;

@Controller
@RequestMapping(value="/cascade")
public class CascadeController {
	
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CiytService cityService;
	@Autowired
	private DistrictService districtService;
	
	@RequestMapping(value="index")
	public String toIndex(){
		return "/views/cascade/cascade";
	}
	
	@RequestMapping(value="/getProvince")
	@ResponseBody
	public List<Province> getProvinceList(){
		List<Province> provinceList = provinceService.getProvinceList();
		return provinceList;
	}
	
	@RequestMapping(value="/getCity")
	@ResponseBody
	public List<City> getCityList(String provId){
		List<City> cityList = null;
		if(!"".equals(provId)){
			int provinceId = Integer.parseInt(provId);
			cityList = cityService.getCityList(provinceId);
		}
		return cityList;
	}
	
	@RequestMapping(value="/getDistrict")
	@ResponseBody
	public List<District> getDistrict(String cityId){
		List<District> districtList = null;
		if(!"".equals(cityId)){
			int cId = Integer.parseInt(cityId);
			districtList = districtService.getDistrict(cId);
		}
		return districtList;
	}
}
