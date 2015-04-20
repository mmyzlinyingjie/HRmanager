package com.butterfly.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;

import com.butterfly.dao.StipendDao;
import com.butterfly.dao.impl.StipendDaoImpl;
import com.butterfly.po.Department;
import com.butterfly.po.PageBean;
import com.butterfly.po.Stipend;
import com.butterfly.service.StipendService;

public class StipendServiceImpl implements StipendService {
	
	private StipendDao dao = new StipendDaoImpl();

	public ResultSet list(Connection con, PageBean pageBean, Stipend stipend) throws Exception {
		return dao.list(con, pageBean, stipend);
	}

	public int count(Connection con) throws Exception {
		return dao.count(con);
	}
	
	public int delete(Connection con, String delIds) throws Exception {
		return dao.delete(con, delIds);
	}
	
	@Override
	public int add(Connection con, Stipend stipend) throws Exception {
		return dao.add(con, stipend);
	}

	@Override
	public int modify(Connection con, Stipend stipend) throws Exception {
		return dao.modify(con, stipend);
	}

	@Override
	public float countTotals(Stipend stipend) {
		
		float total = 0;
		total = stipend.getBasic() + stipend.getEat()+stipend.getHouse()
				+stipend.getDuty()+stipend.getOther() - stipend.getPunishment()
				-stipend.getScot();
		return total;
	}

/*	@Override
	public boolean getEmployeeByDepartmentId(Connection con, String departmentId) throws Exception {
		return dao.getEmployeeByDepartmentId(con, departmentId);
	}

	@Override
	public List<Employee> getData(Connection con, ResultSet rs) throws Exception {
		return dao.getData(con, rs);
	}*/
}
