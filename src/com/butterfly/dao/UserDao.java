package com.butterfly.dao;

import java.sql.Connection;

import com.butterfly.po.User;

public interface UserDao {

	public User login(Connection con, User user) throws Exception;
}
