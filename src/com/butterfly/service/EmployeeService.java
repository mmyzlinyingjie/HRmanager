package com.butterfly.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.butterfly.po.Employee;
import com.butterfly.po.PageBean;

public interface EmployeeService {

	public ResultSet list(Connection con, PageBean pageBean, Employee employee, String bbirthday, String ebirthday) throws Exception;

	public int count(Connection con, Employee employee, String bbirthday, String ebirthday) throws Exception;

	public int delect(Connection con, String delIds) throws Exception;

	public int add(Connection con, Employee employee) throws Exception;

	public int modify(Connection con, Employee employee) throws Exception;

	public boolean getEmployeeByDepartmentId(Connection con, String departmentId) throws Exception;

	public List<Employee> getData(Connection con, ResultSet rs) throws Exception;
}
