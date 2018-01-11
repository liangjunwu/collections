package com.ljw.ssme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ljw.ssme.beans.pojo.User;
import com.ljw.ssme.dao.UserDao;
import com.ljw.ssme.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User checkIn(String username, String password) {
		User user = userDao.checkIn(username, password);
		return user;
	}

}
