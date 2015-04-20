package com.butterfly.dao;

import java.sql.Connection;
import java.util.List;

import com.butterfly.po.Train;

public interface TrainDao {

	public int add(Connection con, Train train) throws Exception;
	
	public List<Train> list(Connection con) throws Exception;
	
	public int delete(Connection con, int id) throws Exception;
	
	public int update(Connection con, int id) throws Exception;
}
