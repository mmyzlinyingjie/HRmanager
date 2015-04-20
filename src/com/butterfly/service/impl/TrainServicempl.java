package com.butterfly.service.impl;

import java.sql.Connection;
import java.util.List;

import com.butterfly.dao.TrainDao;
import com.butterfly.dao.impl.TrainDaoImpl;
import com.butterfly.po.Train;
import com.butterfly.service.TrainService;

public class TrainServicempl implements TrainService {
	
	private TrainDao dao = new TrainDaoImpl();

	@Override
	public int add(Connection con, Train train) throws Exception {
		return dao.add(con, train);
	}
	
	@Override
	public List<Train> list(Connection con) throws Exception {
		return dao.list(con);
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
