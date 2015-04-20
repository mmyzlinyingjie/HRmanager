package com.butterfly.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.butterfly.dao.EmployeeDao;
import com.butterfly.po.Employee;
import com.butterfly.po.PageBean;
import com.butterfly.util.DateUtil;
import com.butterfly.util.StringUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	public ResultSet list(Connection con, PageBean pageBean, Employee employee, String bbirthday, String ebirthday) throws Exception {
		StringBuffer sb = new StringBuffer("select * from employee e, department d where e.departmentId=d.departmentId");
		if (employee != null && !StringUtil.isEmpty(employee.getEmployeeNo())) {
			sb.append(" and e.employeeNo like '%" + employee.getEmployeeNo() + "%'");
		}
		if (employee != null && !StringUtil.isEmpty(employee.getName())) {
			sb.append(" and e.name like '%" + employee.getName() + "%'");
		}
		if (employee != null && !StringUtil.isEmpty(employee.getSex())) {
			sb.append(" and e.sex = '" + employee.getSex() + "'");
		}
		if (employee != null && !StringUtil.isEmpty(employee.getNationality())) {
			sb.append(" and e.nationality like '%" + employee.getNationality() + "%'");
		}
		if (employee != null && !StringUtil.isEmpty(employee.getEducation())) {
			sb.append(" and e.education like '%" + employee.getEducation() + "%'");
		}
		if (employee != null && !StringUtil.isEmpty(employee.getPosition())) {
			sb.append(" and e.position like '%" + employee.getPosition() + "%'");
		}
		if (employee != null && !StringUtil.isEmpty(employee.getEducation())) {
			sb.append(" and e.education like '%" + employee.getEducation() + "%'");
		}
		if (employee.getDepartmentId() != -1) {
			sb.append(" and e.departmentId ='" + employee.getDepartmentId() + "'");
		}
		if (StringUtil.isNotEmpty(bbirthday)) {
			sb.append(" and TO_DAYS(e.birthday)>=TO_DAYS('" + bbirthday + "')");
		}
		if (StringUtil.isNotEmpty(ebirthday)) {
			sb.append(" and TO_DAYS(e.birthday)<=TO_DAYS('" + ebirthday + "')");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	public int count(Connection con, Employee employee, String bbirthday, String ebirthday) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from employee e,department d where e.departmentId=d.departmentId");
		if (employee != null && !StringUtil.isEmpty(employee.getEmployeeNo())) {
			sb.append(" and e.employeeNo like '%" + employee.getEmployeeNo() + "%'");
		}
		if (employee != null && !StringUtil.isEmpty(employee.getName())) {
			sb.append(" and e.name like '%" + employee.getName() + "%'");
		}
		if (employee != null && !StringUtil.isEmpty(employee.getSex())) {
			sb.append(" and e.sex = '" + employee.getSex() + "'");
		}
		if (employee != null && !StringUtil.isEmpty(employee.getNationality())) {
			sb.append(" and e.nationality like '%" + employee.getNationality() + "%'");
		}
		if (employee != null && !StringUtil.isEmpty(employee.getEducation())) {
			sb.append(" and e.education like '%" + employee.getEducation() + "%'");
		}
		if (employee != null && !StringUtil.isEmpty(employee.getPosition())) {
			sb.append(" and e.position like '%" + employee.getPosition() + "%'");
		}
		if (employee != null && !StringUtil.isEmpty(employee.getEducation())) {
			sb.append(" and e.education like '%" + employee.getEducation() + "%'");
		}
		if (employee.getDepartmentId() != -1) {
			sb.append(" and e.departmentId ='" + employee.getDepartmentId() + "'");
		}
		if (StringUtil.isNotEmpty(bbirthday)) {
			sb.append(" and TO_DAYS(e.birthday)>=TO_DAYS('" + bbirthday + "')");
		}
		if (StringUtil.isNotEmpty(ebirthday)) {
			sb.append(" and TO_DAYS(e.birthday)<=TO_DAYS('" + ebirthday + "')");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public int delect(Connection con, String delIds) throws Exception {
		String sql = "delete from employee where employeeId in(" + delIds + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int add(Connection con, Employee employee) throws Exception {
		String sql = "insert into employee values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, employee.getEmployeeNo());
		pstmt.setString(2, employee.getName());
		pstmt.setString(3, employee.getSex());
		pstmt.setString(4, DateUtil.formatDate(employee.getBirthday(), "yyyy-MM-dd"));
		pstmt.setString(5, employee.getNationality());
		pstmt.setString(6, employee.getEducation());
		pstmt.setString(7, employee.getProfession());
		pstmt.setInt(8, employee.getDepartmentId());
		pstmt.setString(9, employee.getPosition());
		pstmt.setFloat(10, employee.getBaseMoney());
		pstmt.setFloat(11, employee.getOvertime());
		pstmt.setFloat(12, employee.getAge());
		pstmt.setFloat(13, employee.getCheck1());
		pstmt.setFloat(14, employee.getAbsent());
		pstmt.setFloat(15, employee.getSafety());
		return pstmt.executeUpdate();
	}

	public int modify(Connection con, Employee employee) throws Exception {
		String sql = "update employee set employeeNo=?,name=?,sex=?,birthday=?,nationality=?,education=?,profession=?,departmentId=?,position=?,baseMoney=?,overtime=?,age=?,check1=?,absent=?,safety=? where employeeId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, employee.getEmployeeNo());
		pstmt.setString(2, employee.getName());
		pstmt.setString(3, employee.getSex());
		pstmt.setString(4, DateUtil.formatDate(employee.getBirthday(), "yyyy-MM-dd"));
		pstmt.setString(5, employee.getNationality());
		pstmt.setString(6, employee.getEducation());
		pstmt.setString(7, employee.getProfession());
		pstmt.setInt(8, employee.getDepartmentId());
		pstmt.setString(9, employee.getPosition());
		pstmt.setFloat(10, employee.getBaseMoney());
		pstmt.setFloat(11, employee.getOvertime());
		pstmt.setFloat(12, employee.getAge());
		pstmt.setFloat(13, employee.getCheck1());
		pstmt.setFloat(14, employee.getAbsent());
		pstmt.setFloat(15, employee.getSafety());
		pstmt.setInt(16, employee.getEmployeeId());
		return pstmt.executeUpdate();
	}

	public boolean getEmployeeByDepartmentId(Connection con, String departmentId) throws Exception {
		String sql = "select * from employee where departmentId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, departmentId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public List<Employee> getData(Connection con, ResultSet rs) throws Exception {
		List<Employee> list = new ArrayList<Employee>();
		try {
			con.setAutoCommit(false);
			while (rs.next()) {
				int employeeId = rs.getInt("employeeId");
				String employeeNo = rs.getString("employeeNo");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				Date birthday = rs.getDate("birthday");
				String nationality = rs.getString("nationality");
				String education = rs.getString("education");
				String profession = rs.getString("profession");
				String departmentNameSrc = rs.getString("departmentName");
				String position = rs.getString("position");
				float baseMoney = rs.getFloat("baseMoney");
				float overtime = rs.getFloat("overtime");
				float age = rs.getFloat("age");
				float check1 = rs.getFloat("check1");
				float absent = rs.getFloat("absent");
				float safety = rs.getFloat("safety");

				Employee employee = new Employee();
				employee.setEmployeeId(employeeId);
				employee.setEmployeeNo(employeeNo);
				employee.setName(name);
				employee.setSex(sex);
				employee.setBirthday(birthday);
				employee.setNationality(nationality);
				employee.setEducation(education);
				employee.setProfession(profession);
				employee.setDepartmentNameSrc(departmentNameSrc);
				employee.setPosition(position);
				employee.setBaseMoney(baseMoney);
				employee.setOvertime(overtime);
				employee.setAge(age);
				employee.setCheck1(check1);
				employee.setAbsent(absent);
				employee.setSafety(safety);

				list.add(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
