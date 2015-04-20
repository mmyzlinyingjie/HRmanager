package com.butterfly.service.impl;

import java.sql.Connection;

import com.butterfly.dao.UserDao;
import com.butterfly.dao.impl.UserDaoImpl;
import com.butterfly.po.User;
import com.butterfly.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao dao = new UserDaoImpl();

	public User login(Connection con, User user) throws Exception {
		return dao.login(con, user);
	}
}
