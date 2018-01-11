package com.ljw.ssme.service;

import java.util.List;

import com.ljw.ssme.beans.pojo.District;

public interface DistrictService {

	public List<District> getDistrict(int cityId);
}
