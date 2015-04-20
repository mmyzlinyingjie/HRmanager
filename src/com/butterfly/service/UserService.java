package com.butterfly.service;

import java.sql.Connection;

import com.butterfly.po.User;

public interface UserService {

	public User login(Connection con, User user) throws Exception;
}
