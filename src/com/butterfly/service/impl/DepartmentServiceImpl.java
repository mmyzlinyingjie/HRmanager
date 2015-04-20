package com.butterfly.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;

import com.butterfly.dao.DepartmentDao;
import com.butterfly.dao.impl.DepartmentDaoImpl;
import com.butterfly.po.Department;
import com.butterfly.po.PageBean;
import com.butterfly.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDao dao = new DepartmentDaoImpl();
	
	public ResultSet list(Connection con, PageBean pageBean, Department department) throws Exception {
		return dao.list(con, pageBean, department);
	}

	public int count(Connection con) throws Exception {
		return dao.count(con);
	}

	public int add(Connection con, Department department) throws Exception {
		return dao.add(con, department);
	}

	public int delete(Connection con, String delIds) throws Exception {
		return dao.delete(con, delIds);
	}

	public int modify(Connection con, Department department) throws Exception {
		return dao.modify(con, department);
	}
}
