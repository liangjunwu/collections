package com.ljw.ssme.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljw.ssme.beans.pojo.District;
import com.ljw.ssme.dao.DistrictDao;
import com.ljw.ssme.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService{

	@Autowired
	private DistrictDao districtDao;
	
	@Override
	public List<District> getDistrict(int cityId) {
		List<District> districtList = districtDao.getDistrict(cityId);
		return districtList;
	}

}
