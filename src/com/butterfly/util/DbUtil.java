package com.butterfly.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

	private String URL = "jdbc:mysql://localhost:3306/humanresourcemanage";
	private String USER = "root";
	private String PASSWORD = "mynona";
	private String DRIVER = "com.mysql.jdbc.Driver";

	public Connection getCon() throws Exception {
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
		return con;
	}

	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}

	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		Connection con = null;
		try {
			con = dbUtil.getCon();
			System.out.println("连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
				System.out.println("关闭连接");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
