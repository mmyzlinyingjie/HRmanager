package com.butterfly.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.butterfly.dao.TrainDao;
import com.butterfly.po.Train;
import com.butterfly.util.DateUtil;

public class TrainDaoImpl implements TrainDao {

	@Override
	public int add(Connection con, Train train) throws Exception {
		String sql = "insert into train values(null,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, train.getName());
		pstmt.setString(2, train.getPurpose());
		pstmt.setString(3, DateUtil.formatDate(train.getBeginTime(), "yyyy-MM-dd"));
		pstmt.setString(4, DateUtil.formatDate(train.getEndTime(), "yyyy-MM-dd"));
		pstmt.setString(5, train.getDatum());
		pstmt.setString(6, train.getTeacher());
		pstmt.setString(7, train.getEmployee());
		pstmt.setString(8, DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
		pstmt.setInt(9, train.getIsFinish());
		pstmt.setString(10, train.getEffect());
		pstmt.setString(11, train.getSummarize());
		System.out.println();
		System.out.println("iiiiiiiiiiiiiiii");
		return pstmt.executeUpdate();
	}
	
	@Override
	public List<Train> list(Connection con) throws Exception {
		String sql = "select * from train";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<Train> list = new ArrayList<Train>();
		while(rs.next()) {
			Train t = new Train();
			
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setPurpose(rs.getString("purpose"));
			t.setBeginTime(rs.getDate("beginTime"));
			t.setEndTime(rs.getDate("endTime"));
			t.setTeacher(rs.getString("teacher"));
			t.setDatum(rs.getString("datum"));
			t.setEffect(rs.getString("effect"));
			t.setSummarize(rs.getString("summarize"));
			
			list.add(t);
		}
		return list;
	}
	
	@Override
	public int delete(Connection con, int id) throws Exception {
		String sql = "delete from train where id = " + id;
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	@Override
	public int update(Connection con, int id) throws Exception {
		String sql = "update train set isFinish = 1 where id = " + id;
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
