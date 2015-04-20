package com.butterfly.service.impl;

import java.sql.Connection;
import java.util.List;

import com.butterfly.dao.JobDao;
import com.butterfly.dao.impl.JobDaoImpl;
import com.butterfly.po.Job;
import com.butterfly.po.Train;
import com.butterfly.service.JobService;

public class JobServicempl implements JobService {
	
	private JobDao dao = new JobDaoImpl();

	@Override
	public int add(Connection con, Job train) throws Exception {
		return dao.add(con, train);
	}
	
	@Override
	public List<Job> list(Connection con) throws Exception {
		return dao.list(con);
	}
	
	@Override
	public List<Job> listTock(Connection con) throws Exception {
		return dao.listTock(con);
	}
	
	@Override
	public int delete(Connection con, int id) throws Exception {
		return dao.delete(con, id);
	}

	@Override
	public int update(Connection con, int id) throws Exception {
		return dao.update(con, id);
	}
}
