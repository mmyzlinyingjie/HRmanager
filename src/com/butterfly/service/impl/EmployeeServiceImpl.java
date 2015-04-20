package com.butterfly.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.butterfly.dao.EmployeeDao;
import com.butterfly.dao.impl.EmployeeDaoImpl;
import com.butterfly.po.Employee;
import com.butterfly.po.PageBean;
import com.butterfly.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao dao = new EmployeeDaoImpl();

	@Override
	public ResultSet list(Connection con, PageBean pageBean, Employee employee, String bbirthday, String ebirthday) throws Exception {
		return dao.list(con, pageBean, employee, bbirthday, ebirthday);
	}

	@Override
	public int count(Connection con, Employee employee, String bbirthday, String ebirthday) throws Exception {
		return dao.count(con, employee, bbirthday, ebirthday);
	}

	@Override
	public int delect(Connection con, String delIds) throws Exception {
		return dao.delect(con, delIds);
	}

	@Override
	public int add(Connection con, Employee employee) throws Exception {
		return dao.add(con, employee);
	}

	@Override
	public int modify(Connection con, Employee employee) throws Exception {
		return dao.modify(con, employee);
	}

	@Override
	public boolean getEmployeeByDepartmentId(Connection con, String departmentId) throws Exception {
		return dao.getEmployeeByDepartmentId(con, departmentId);
	}

	@Override
	public List<Employee> getData(Connection con, ResultSet rs) throws Exception {
		return dao.getData(con, rs);
	}
}
