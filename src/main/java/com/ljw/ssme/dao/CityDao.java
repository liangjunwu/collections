package com.ljw.ssme.dao;

import java.util.List;

import com.ljw.ssme.beans.pojo.City;

public interface CityDao {

	public List<City> getCityList(int provinceId);
}
