package com.service;

import com.dao.UserInfoDao;
import com.pojo.User;

public class UserService {

	public User login(String userName,String userPassword) throws Exception {
		return new UserInfoDao().login(userName,userPassword);
	}

}
