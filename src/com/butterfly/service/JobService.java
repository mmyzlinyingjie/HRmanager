package com.butterfly.service;

import java.sql.Connection;
import java.util.List;

import com.butterfly.po.Job;
import com.butterfly.po.Train;


public interface JobService {

	public int add(Connection con, Job job) throws Exception;
	
	public List<Job> list(Connection con) throws Exception;
	
	public List<Job> listTock(Connection con) throws Exception;
	
	public int delete(Connection con, int id) throws Exception;
	
	public int update(Connection con, int id) throws Exception;
}
