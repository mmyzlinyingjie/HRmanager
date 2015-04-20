package com.butterfly.service.impl;

import java.sql.Connection;
import java.util.List;

import com.butterfly.dao.InstitutionDao;
import com.butterfly.dao.impl.InstitutionDaoImpl;
import com.butterfly.po.Institution;
import com.butterfly.po.Job;
import com.butterfly.service.InstitutionService;

public class InstitutionServicempl implements InstitutionService {
	
	private InstitutionDao dao = new InstitutionDaoImpl();

	@Override
	public int add(Connection con, Institution train) throws Exception {
		return dao.add(con, train);
	}
	
	@Override
	public List<Institution> list(Connection con) throws Exception {
		return dao.list(con);
	}
	
	
	@Override
	public int delete(Connection con, int id) throws Exception {
		return dao.delete(con, id);
	}

	@Override
	public int update(Connection con, int id, String name, String reason, 
			String explains) throws Exception {
		return dao.update(con, id, name, reason, explains);
	}
}
