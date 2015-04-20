package com.butterfly.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.butterfly.dao.DepartmentDao;
import com.butterfly.po.Department;
import com.butterfly.po.PageBean;
import com.butterfly.util.StringUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	public ResultSet list(Connection con, PageBean pageBean, Department department) throws Exception {
		StringBuffer sb = new StringBuffer("select * from department");
		if (department != null && !StringUtil.isEmpty(department.getDepartmentName())) {
			sb.append(" and departmentName like '%" + department.getDepartmentName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	public int count(Connection con) throws Exception {
		String sql = "select count(*) as total from department";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public int add(Connection con, Department department) throws Exception {
		String sql = "insert into department values(null,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, department.getDepartmentName());
		pstmt.setString(2, department.getDepartmentDesc());
		return pstmt.executeUpdate();
	}

	public int delete(Connection con, String delIds) throws Exception {
		String sql = "delete from department where departmentId in(" + delIds + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int modify(Connection con, Department department) throws Exception {
		String sql = "update department set departmentName=?,departmentDesc=? where departmentId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, department.getDepartmentName());
		pstmt.setString(2, department.getDepartmentDesc());
		pstmt.setInt(3, department.getDepartmentId());
		return pstmt.executeUpdate();
	}
}
