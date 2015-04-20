package com.butterfly.dao;

import java.sql.Connection;
import java.util.List;

import com.butterfly.po.Institution;
import com.butterfly.po.Train;

public interface InstitutionDao {

	public int add(Connection con, Institution inStitution) throws Exception;
	
	public List<Institution> list(Connection con) throws Exception;
	
	public int delete(Connection con, int id) throws Exception;
	
	public int update(Connection con, int id, String name, String reason, 
			String explains) throws Exception;
}
