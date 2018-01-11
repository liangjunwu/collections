package com.ljw.ssme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljw.ssme.beans.pojo.City;
import com.ljw.ssme.dao.CityDao;
import com.ljw.ssme.service.CiytService;

@Service
public class CiytServiceImpl implements CiytService{
	
	@Autowired
	private CityDao cityDao;

	@Override
	public List<City> getCityList(int provinceId) {
		List<City> cityList = cityDao.getCityList(provinceId);
		return cityList;
	}

}
