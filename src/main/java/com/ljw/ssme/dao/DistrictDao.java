package com.ljw.ssme.dao;

import java.util.List;

import com.ljw.ssme.beans.pojo.District;

public interface DistrictDao {
	
	public List<District> getDistrict(int cityId);

}
