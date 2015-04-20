package com.butterfly.action;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.butterfly.po.Job;
import com.butterfly.service.JobService;
import com.butterfly.service.impl.JobServicempl;
import com.butterfly.util.DbUtil;
import com.butterfly.util.ResponseUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class JobAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private JobService jobService = new JobServicempl();
	private DbUtil dbUtil = new DbUtil();

	private Job job = new Job();
	private int id;

	public String Save() throws Exception {
		
		 HttpServletResponse response = null;
		 response = ServletActionContext.getResponse();
		 response.setCharacterEncoding("utf-8");
		 
		 HttpServletRequest request = null;
		 request = ServletActionContext.getRequest();
		 request.setCharacterEncoding("utf-8");
		 
		System.out.println("--------content:" + job.getContent());
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			saveNums = jobService.add(con, job);
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

	public String list() throws Exception {
		Connection con = null;
		List<Job> list = null;
		con = dbUtil.getCon();
		list = jobService.list(con);
//		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("list", list);
		ActionContext.getContext().getSession().put("list",list);
		if (list != null)
			return "success";
		return "error";
	}

	public String List() throws Exception {
		Connection con = null;
		List<Job> list = null;
		try {
			con = dbUtil.getCon();
			list = jobService.list(con);
			ActionContext.getContext().getSession().put("jobList",list);
			if (list != null)
				return "detail";
			return "error";
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
	
	public String ListTock() throws Exception {
		Connection con = null;
		List<Job> list = null;
		try {
			con = dbUtil.getCon();
			list = jobService.listTock(con);
			ActionContext.getContext().getSession().put("jobListTock",list);
			if (list != null)
				return "list";
			return "error";
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
			int res = jobService.delete(con, id);
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
			int res = jobService.update(con, id);
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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void setId(int id) {
		this.id = id;
	}
}
