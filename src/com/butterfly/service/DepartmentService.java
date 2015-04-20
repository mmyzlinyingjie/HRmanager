package com.butterfly.service;

import java.sql.Connection;
import java.sql.ResultSet;

import com.butterfly.po.Department;
import com.butterfly.po.PageBean;

public interface DepartmentService {

	public ResultSet list(Connection con, PageBean pageBean, Department department) throws Exception;

	public int count(Connection con) throws Exception;

	public int add(Connection con, Department department) throws Exception;

	public int delete(Connection con, String delIds) throws Exception;

	public int modify(Connection con, Department department) throws Exception;
}
