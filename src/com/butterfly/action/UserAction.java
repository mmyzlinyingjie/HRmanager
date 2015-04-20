package com.butterfly.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.butterfly.po.User;
import com.butterfly.service.UserService;
import com.butterfly.service.impl.UserServiceImpl;
import com.butterfly.util.DbUtil;
import com.butterfly.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private User user = new User();
	private String error;
	
	DbUtil dbUtil = new DbUtil();
	UserService userService = new UserServiceImpl();

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(user.getUserName()) || StringUtil.isEmpty(user.getPassword())) {
			error = "用户名或者密码为空！";
			return ERROR;
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			User currentUser = userService.login(con, user);
			if (currentUser == null) {
				error = "用户名或者密码错误！";
				return ERROR;
			} else {
				session.setAttribute("currentUser", currentUser);
				return SUCCESS;
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
		return SUCCESS;
	}

	// set, get

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
