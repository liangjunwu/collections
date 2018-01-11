package com.ljw.ssme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljw.ssme.beans.pojo.Province;
import com.ljw.ssme.dao.ProvinceDao;
import com.ljw.ssme.service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService{
	
	@Autowired
	private ProvinceDao provinceDao;
	
	@Override
	public List<Province> getProvinceList(){
		List<Province> provinceList = provinceDao.getProvinceList();
		return provinceList;
	}
}
