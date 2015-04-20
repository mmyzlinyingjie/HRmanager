package com.butterfly.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.butterfly.dao.JobDao;
import com.butterfly.po.Job;
import com.butterfly.util.DateUtil;

public class JobDaoImpl implements JobDao {

	@Override
	public int add(Connection con, Job job) throws Exception {
		String sql = "insert into job values(null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, job.getName());
		pstmt.setInt(2, job.getSex());
		pstmt.setInt(3, job.getAge());
		pstmt.setString(4, job.getJob());
		pstmt.setString(5, job.getSpecialty());
		pstmt.setString(6, job.getExperience());
		pstmt.setString(7, job.getStudyeffort());
		pstmt.setString(8, job.getSchool());
		pstmt.setString(9, job.getTel());
		pstmt.setString(10, job.getEmail());
		pstmt.setString(11, DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
		pstmt.setString(12, job.getContent());
		pstmt.setInt(13, 0);
		System.out.println();
		System.out.println("iiiiiiiiiiiiiiii");
		return pstmt.executeUpdate();
	}
	
	@Override
	public List<Job> list(Connection con) throws Exception {
		String sql = "select * from Job where isstock = 1";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<Job> list = new ArrayList<Job>();
		while(rs.next()) {
			Job t = new Job();
			
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setSex(rs.getByte("sex"));
			t.setAge(rs.getInt("age"));
			t.setJob(rs.getString("job"));
			t.setSpecialty(rs.getString("specialty"));
			t.setExperience(rs.getString("experience"));
			t.setStudyeffort(rs.getString("studyeffort"));
			t.setSchool(rs.getString("school"));
			t.setTel(rs.getString("tel"));
			t.setEmail(rs.getString("email"));
			t.setContent(rs.getString("content"));
			
			list.add(t);
		}
		return list;
	}
	
	@Override
	public List<Job> listTock(Connection con) throws Exception {
		String sql = "select * from Job where isstock = 0";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<Job> list = new ArrayList<Job>();
		while(rs.next()) {
			Job t = new Job();
			
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setSex(rs.getByte("sex"));
			t.setAge(rs.getInt("age"));
			t.setJob(rs.getString("job"));
			t.setSpecialty(rs.getString("specialty"));
			t.setExperience(rs.getString("experience"));
			t.setStudyeffort(rs.getString("studyeffort"));
			t.setSchool(rs.getString("school"));
			t.setTel(rs.getString("tel"));
			t.setEmail(rs.getString("email"));
			t.setContent(rs.getString("content"));
			
			list.add(t);
		}
		return list;
	}
	
	
	@Override
	public int delete(Connection con, int id) throws Exception {
		String sql = "delete from Job where id = " + id;
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	@Override
	public int update(Connection con, int id) throws Exception {
		String sql = "update Job set isstock = 1 where id = " + id;
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
