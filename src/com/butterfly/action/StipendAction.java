package com.butterfly.action;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.butterfly.po.Stipend;
import com.butterfly.po.Employee;
import com.butterfly.po.PageBean;
import com.butterfly.po.Stipend;
import com.butterfly.service.StipendService;
import com.butterfly.service.impl.StipendServiceImpl;
import com.butterfly.util.DbUtil;
import com.butterfly.util.JsonUtil;
import com.butterfly.util.ResponseUtil;
import com.butterfly.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class StipendAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Stipend stipend = new Stipend();
	private String stipendId;
	private String s_name = "";
	private String page;
	private String rows;
	private String delIds;
	

	DbUtil dbUtil = new DbUtil();
	StipendService stipendService = new StipendServiceImpl();



	@Override
	public String execute() throws Exception {
		Connection con = null;
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		try {
			if (stipend == null) {
				stipend = new Stipend();
			}
			stipend.setName(s_name);

			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			ResultSet rs = stipendService.list(con, pageBean, stipend);
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(rs);
			int total = stipendService.count(con);
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
			int delNums = stipendService.delete(con, delIds);
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
		System.out.println("save---------------");
		if (StringUtil.isNotEmpty(stipendId)) {
			stipend.setId(Integer.parseInt(stipendId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			stipend.setTotalize(stipendService.countTotals(stipend));
			
				saveNums = stipendService.add(con, stipend);
	
			if (saveNums > 0) {
				result.put("success", "true");
			} else {
				result.put("success", "true");
				result.put("errorMeg", "删除失败");
			}
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
	
	public String update() throws Exception {
		if (StringUtil.isNotEmpty(stipendId)) {
			stipend.setId(Integer.parseInt(stipendId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			stipend.setTotalize(stipendService.countTotals(stipend));
			
			if (StringUtil.isNotEmpty(stipendId)) {
				saveNums = stipendService.modify(con, stipend);
			} 
			if (saveNums > 0) {
				result.put("success", "true");
			} else {
				result.put("success", "true");
				result.put("errorMeg", "删除失败");
			}
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

/*	public void ExportEmployee() throws Exception {
		Connection con = null;
		con = dbUtil.getCon();
		List<Employee> list = employeeService.getData(con, search());
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			HSSFSheet sheet = workbook.createSheet("employee");
			HSSFRow row = sheet.createRow(0);
			String[] cellTitle = { "ID", "编号", "姓名", "性别", "出生日期", "民族", "学历", "专业", "部门", "职务", "基本工资", "加班费", "工龄工资", "考勤费", "旷工费", "保险费" };
			for (int i = 0; i < cellTitle.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(cellTitle[i]);
			}

			for (int rowIndex = 0; rowIndex < list.size(); rowIndex++) {
				row = sheet.createRow(rowIndex + 1);
				HSSFCell cell = row.createCell(rowIndex);
				cell.setCellValue(cellTitle[rowIndex]);
				Employee employee = list.get(rowIndex);
				for (int cellnum = 0; cellnum < 17; cellnum++) {
					cell = row.createCell(cellnum);
					switch (cellnum) {
					case 0:
						cell.setCellValue(employee.getEmployeeId());
						break;
					case 1:
						cell.setCellValue(employee.getEmployeeNo());
						break;
					case 2:
						cell.setCellValue(employee.getName());
						break;
					case 3:
						cell.setCellValue(employee.getSex());
						break;
					case 4:
						cell.setCellValue(DateUtil.formatDate(employee.getBirthday(), "yyyy-MM-dd"));
						break;
					case 5:
						cell.setCellValue(employee.getNationality());
						break;
					case 6:
						cell.setCellValue(employee.getEducation());
						break;
					case 7:
						cell.setCellValue(employee.getProfession());
						break;
					case 8:
						cell.setCellValue(employee.getDepartmentNameSrc());
						break;
					case 9:
						cell.setCellValue(employee.getPosition());
						break;
					case 10:
						cell.setCellValue(employee.getBaseMoney());
						break;
					case 11:
						cell.setCellValue(employee.getOvertime());
						break;
					case 12:
						cell.setCellValue(employee.getAge());
						break;
					case 13:
						cell.setCellValue(employee.getCheck1());
						break;
					case 14:
						cell.setCellValue(employee.getAbsent());
						break;
					case 15:
						cell.setCellValue(employee.getSafety());
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String exportFileName = "employee.xls";

		ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=" + new String((exportFileName).getBytes(), "ISO8859-1"));// 设定输出文件头
		ServletActionContext.getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型

		OutputStream out = ServletActionContext.getResponse().getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
	}*/

	public String getStipendId() {
		return stipendId;
	}

	public void setStipendId(String stipendId) {
		this.stipendId = stipendId;
	}

	public Stipend getStipend() {
		return stipend;
	}

	public void setStipend(Stipend stipend) {
		this.stipend = stipend;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
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
	
	
}