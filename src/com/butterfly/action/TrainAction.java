package com.butterfly.action;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.butterfly.po.Train;
import com.butterfly.service.TrainService;
import com.butterfly.service.impl.TrainServicempl;
import com.butterfly.util.DbUtil;
import com.butterfly.util.ResponseUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TrainAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private TrainService trainService = new TrainServicempl();
	private DbUtil dbUtil = new DbUtil();

	private Train train = new Train();
	private int id;

	public String Save() throws Exception {
		Connection con = null;
		try {
			System.out.println(train.getName());
			System.out.println(train.getEffect());
			System.out.println(train.getSummarize());
			System.out.println(train.getPurpose());
			System.out.println(train.getBeginTime());
			System.out.println(train.getEndTime());
			con = dbUtil.getCon();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			saveNums = trainService.add(con, train);
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

	
	/*public String Save() throws Exception {
		
		 HttpServletResponse response = null;
		 response = ServletActionContext.getResponse();
		 response.setCharacterEncoding("utf-8");
		 
		 HttpServletRequest request = null;
		 request = ServletActionContext.getRequest();
		 request.setCharacterEncoding("utf-8");
		 
		System.out.println("--------content:" + train.getDatum());
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			saveNums = trainService.add(con, train);
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
	*/
	public String list() throws Exception {
		Connection con = null;
		List<Train> list = null;
		con = dbUtil.getCon();
		list = trainService.list(con);
//		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("list", list);
		ActionContext.getContext().getSession().put("list",list);
		if (list != null)
			return "success";
		return "error";
	}

	public String List() throws Exception {
		Connection con = null;
		List<Train> list = null;
		try {
			con = dbUtil.getCon();
			list = trainService.list(con);
			ActionContext.getContext().getSession().put("list",list);
			if (list != null)
				return "success";
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
			int res = trainService.delete(con, id);
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
			int res = trainService.update(con, id);
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
	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
