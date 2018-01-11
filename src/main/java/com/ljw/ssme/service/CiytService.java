package com.ljw.ssme.service;

import java.util.List;

import com.ljw.ssme.beans.pojo.City;

public interface CiytService {

	public List<City> getCityList(int provinceId);
}
