package com.butterfly.action;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.butterfly.po.Department;
import com.butterfly.po.PageBean;
import com.butterfly.service.DepartmentService;
import com.butterfly.service.EmployeeService;
import com.butterfly.service.impl.DepartmentServiceImpl;
import com.butterfly.service.impl.EmployeeServiceImpl;
import com.butterfly.util.DbUtil;
import com.butterfly.util.JsonUtil;
import com.butterfly.util.ResponseUtil;
import com.butterfly.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class DepartmentAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Department department = new Department();
	private String s_departmentName = "";
	private String page;
	private String rows;
	private String id;
	private String delIds;

	DbUtil dbUtil = new DbUtil();
	DepartmentService departmentService = new DepartmentServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();

	@Override
	public String execute() throws Exception {
		Connection con = null;
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		try {
			if (department == null) {
				department = new Department();
			}
			department.setDepartmentName(s_departmentName);

			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			ResultSet rs = departmentService.list(con, pageBean, department);
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(rs);
			int total = departmentService.count(con);
			result.put("rows", jsonArray);
			result.put("total", total);
			HttpServletResponse response = ServletActionContext.getResponse();
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String delete() throws Exception {
		Connection con = null;
		try {
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				boolean f = employeeService.getEmployeeByDepartmentId(con, str[i]);
				if (f) {
					result.put("errorIndex", i);
					result.put("errorMsg", "部门下面有员工,不能删除!");
					ResponseUtil.write(ServletActionContext.getResponse(), result);
					return null;
				}
			}
			int delNums = departmentService.delete(con, delIds);
			if (delNums > 0) {
				result.put("success", "true");
				result.put("delNums", delNums);
			} else {
				result.put("success", "true");
				result.put("errorMsg", "删除失败");
			}
			result.put("delNum", delNums);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String save() throws Exception {
		if (StringUtil.isNotEmpty(id)) {
			department.setDepartmentId(Integer.parseInt(id));
		}

		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			if (StringUtil.isNotEmpty(id)) {
				saveNums = departmentService.modify(con, department);
			} else {
				saveNums = departmentService.add(con, department);
			}
			if (saveNums > 0) {
				result.put("success", "true");
			} else {
				result.put("success", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String departmentComboList() throws Exception {
		Connection con = null;
		try {
			con = dbUtil.getCon();
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("departmentId", "");
			jsonObject.put("departmentName", "请选择");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JsonUtil.formatRsToJsonArray(departmentService.list(con, null, null)));
			ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// set, get
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getS_departmentName() {
		return s_departmentName;
	}

	public void setS_departmentName(String s_departmentName) {
		this.s_departmentName = s_departmentName;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getDelIds() {
		return delIds;
	}

	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}