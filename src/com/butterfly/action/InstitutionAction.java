package com.butterfly.action;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.butterfly.po.Institution;
import com.butterfly.po.Job;
import com.butterfly.service.InstitutionService;
import com.butterfly.service.impl.InstitutionServicempl;
import com.butterfly.util.DbUtil;
import com.butterfly.util.ResponseUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InstitutionAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private InstitutionService institutionService = new InstitutionServicempl();
	private DbUtil dbUtil = new DbUtil();

	private Institution institution = new Institution();
	private int id;
	private String name;
	private String reason;
	private String explains;
	
	

	public String Save() throws Exception {
		
		 HttpServletResponse response = null;
		 response = ServletActionContext.getResponse();
		 response.setCharacterEncoding("utf-8");
		 
		 HttpServletRequest request = null;
		 request = ServletActionContext.getRequest();
		 request.setCharacterEncoding("utf-8");
	
		 System.out.println("name:" + institution.getName());
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			saveNums = institutionService.add(con, institution);
			if (saveNums > 0) {
				result.put("success", "true");
				ResponseUtil.write(ServletActionContext.getResponse(), "保存成功");
			} else {
				result.put("success", "false");
				result.put("errorMeg", "删除失败");
				ResponseUtil.write(ServletActionContext.getResponse(), "保存失败");
			}
			// ResponseUtil.write(ServletActionContext.getResponse(), result);
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



	public String List() throws Exception {
		Connection con = null;
		List<Institution> list = null;
		try {
			con = dbUtil.getCon();
			list = institutionService.list(con);
			ActionContext.getContext().getSession().put("institutionListTock",list);
			if (list != null)
				return "success";
			return "success";
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
	

	public void Delete() throws Exception {
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int res = institutionService.delete(con, id);
			if(res <=0) {
				ResponseUtil.write(ServletActionContext.getResponse(), "删除失败");				
				return;
			}
			List();
			ResponseUtil.write(ServletActionContext.getResponse(), "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void Finish() throws Exception {
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int res = institutionService.update(con, id, name, reason, explains);
			if(res <=0) {
				ResponseUtil.write(ServletActionContext.getResponse(), "操作失败");				
				return;
			}
			List();
			ResponseUtil.write(ServletActionContext.getResponse(), "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//set ,get 


	public int getId() {
		return id;
	}



	public Institution getInstitution() {
		return institution;
	}



	public void setInstitution(Institution institution) {
		this.institution = institution;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getReason() {
		return reason;
	}



	public void setReason(String reason) {
		this.reason = reason;
	}



	public String getExplains() {
		return explains;
	}



	public void setExplains(String explains) {
		this.explains = explains;
	}



	public void setId(int id) {
		this.id = id;
	}
}
