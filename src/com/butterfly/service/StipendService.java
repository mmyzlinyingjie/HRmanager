package com.butterfly.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.butterfly.po.Employee;
import com.butterfly.po.PageBean;
import com.butterfly.po.Stipend;

public interface StipendService {

	public ResultSet list(Connection con, PageBean pageBean, Stipend stipend) throws Exception;

	public int count(Connection con) throws Exception;

	public int delete(Connection con, String delIds) throws Exception;

	public int add(Connection con, Stipend stipend) throws Exception;

	public int modify(Connection con, Stipend stipend) throws Exception;

	//计算总薪资
	public float countTotals(Stipend stipend);
	//public List<Employee> getData(Connection con, ResultSet rs) throws Exception;
}
