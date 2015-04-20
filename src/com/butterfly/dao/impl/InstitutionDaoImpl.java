package com.butterfly.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.butterfly.dao.InstitutionDao;
import com.butterfly.po.Institution;
import com.butterfly.po.Job;
import com.butterfly.util.DateUtil;

public class InstitutionDaoImpl implements InstitutionDao {

	@Override
	public int add(Connection con, Institution inst) throws Exception {
		String sql = "insert into Institution values(null,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, inst.getName());
		pstmt.setString(2, inst.getReason());
		pstmt.setString(3, inst.getExplain());
		pstmt.setString(4, DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
		return pstmt.executeUpdate();
	}
	
	@Override
	public List<Institution> list(Connection con) throws Exception {
		String sql = "select * from Institution";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<Institution> list = new ArrayList<Institution>();
		while(rs.next()) {
			Institution t = new Institution();
			
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setExplain(rs.getString("explains"));
			t.setReason(rs.getString("reason"));
			list.add(t);
		}
		return list;
	}
	
	
	
	@Override
	public int delete(Connection con, int id) throws Exception {
		String sql = "delete from Institution where id = " + id;
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	@Override
	public int update(Connection con, int id, String name, String reason, 
			String explains) throws Exception {
		String sql = "update Institution set name = '" + name +
					"', reason = '"+reason +
				"',explains = '" + explains
				+ "' where id = " + id;
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
}
