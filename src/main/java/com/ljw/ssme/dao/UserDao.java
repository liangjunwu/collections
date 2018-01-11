package com.ljw.ssme.dao;

import org.apache.ibatis.annotations.Param;

import com.ljw.ssme.beans.pojo.User;

public interface UserDao {
	
	public User checkIn(@Param("username")String username, @Param("password")String password);

}
