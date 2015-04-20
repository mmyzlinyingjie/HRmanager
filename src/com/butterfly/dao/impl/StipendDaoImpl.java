package com.butterfly.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.butterfly.dao.StipendDao;
import com.butterfly.po.Department;
import com.butterfly.po.PageBean;
import com.butterfly.po.Stipend;
import com.butterfly.util.DateUtil;
import com.butterfly.util.StringUtil;

public class StipendDaoImpl implements StipendDao {

	public ResultSet list(Connection con, PageBean pageBean, Stipend stipend) throws Exception {
		StringBuffer sb = new StringBuffer("select * from stipend");
		if (stipend != null && !StringUtil.isEmpty(stipend.getName())) {
			sb.append(" and name like '%" + stipend.getName()+ "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	public int count(Connection con) throws Exception {
		String sql = "select count(*) as total from stipend";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public int delete(Connection con, String delIds) throws Exception {
		String sql = "delete from stipend where id in(" + delIds + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int add(Connection con, Stipend stipend) throws Exception {
		String sql = "insert into stipend( name, basic, eat, house, granttime, duty, scot, punishment, "
				+ "other, departmentNameSrc, effect,totalize,id) value(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, stipend.getName());
		pstmt.setFloat(2, stipend.getBasic());
		pstmt.setFloat(3, stipend.getEat());
		pstmt.setFloat(4, stipend.getHouse());
		pstmt.setString(5, DateUtil.formatDate(stipend.getGranttime(), "yyyy-MM-dd"));
		pstmt.setFloat(6, stipend.getDuty());
		pstmt.setFloat(7, stipend.getScot());
		pstmt.setFloat(8, stipend.getPunishment());
		pstmt.setFloat(9, stipend.getOther());
	//	pstmt.setFloat(10, stipend.getTotalize());
	//	pstmt.setInt(10, stipend.getDepartmentId());
		pstmt.setString(10, stipend.getDepartmentNameSrc());
		pstmt.setFloat(11, stipend.getEffect());
		pstmt.setFloat(12, stipend.getTotalize());
		pstmt.setInt(13, stipend.getId());
		
		return pstmt.executeUpdate();
	}

	public int modify(Connection con, Stipend stipend) throws Exception {
		String sql = "update stipend set name=?,basic=?,eat=?,house=?,granttime=?,duty=?,"
				+ "scot=?,punishment=?,other=?,departmentNameSrc=?,effect=?,totalize = ? where id = ?;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, stipend.getName());
		pstmt.setFloat(2, stipend.getBasic());
		pstmt.setFloat(3, stipend.getEat());
		pstmt.setFloat(4, stipend.getHouse());
		pstmt.setString(5, DateUtil.formatDate(stipend.getGranttime(), "yyyy-MM-dd"));
		pstmt.setFloat(6, stipend.getDuty());
		pstmt.setFloat(7, stipend.getScot());
		pstmt.setFloat(8, stipend.getPunishment());
		pstmt.setFloat(9, stipend.getOther());
	//	pstmt.setFloat(10, stipend.getTotalize());
	//	pstmt.setInt(10, stipend.getDepartmentId());
		pstmt.setString(10, stipend.getDepartmentNameSrc());
		pstmt.setFloat(11, stipend.getEffect());
		pstmt.setFloat(12, stipend.getTotalize());
		pstmt.setFloat(13, stipend.getId());
		
		
		return pstmt.executeUpdate();
	}

/*	public boolean getstipendByDepartmentId(Connection con, String departmentId) throws Exception {
		String sql = "select * from stipend where departmentId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, departmentId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}*/

	public List<Stipend> getData(Connection con, ResultSet rs) throws Exception {
		List<Stipend> list = new ArrayList<Stipend>();
		try {
			con.setAutoCommit(false);
			while (rs.next()) {
/*				int stipendId = rs.getInt("stipendId");
				String stipendNo = rs.getString("stipendNo");
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

				stipend stipend = new stipend();
				stipend.setstipendId(stipendId);
				stipend.setstipendNo(stipendNo);
				stipend.setName(name);
				stipend.setSex(sex);
				stipend.setBirthday(birthday);
				stipend.setNationality(nationality);
				stipend.setEducation(education);
				stipend.setProfession(profession);
				stipend.setDepartmentNameSrc(departmentNameSrc);
				stipend.setPosition(position);
				stipend.setBaseMoney(baseMoney);
				stipend.setOvertime(overtime);
				stipend.setAge(age);
				stipend.setCheck1(check1);
				stipend.setAbsent(absent);
				stipend.setSafety(safety);

				list.add(stipend);*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
