<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.butterfly.po.Job"%>
<%@ page import="com.butterfly.util.*"%>
<%@ page language="java" import="com.opensymphony.xwork2.ActionContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人力资源管理系统</title>
<link rel="stylesheet" rev="stylesheet" href="css/style.css "
	type="text/css" media="all" />
<script type="text/javascript" src="Js/typem.js"></script>
<script type="text/javascript" src="Js/js.js"></script>
<style type="text/css">
<!--
.atten {
	font-size: 12px;
	font-weight: normal;
	color: #F00;
}
-->
</style>
</head>

<%
	List<Job> jobListTock = (List<Job>) ActionContext.getContext().getSession().get("jobListTock");
	// List<Train> trainList = (List<Train>) request.getAttribute("list");
%>

<body class="ContentBody">
	<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" align="center">人才库</th>
  </tr>
	<%
		//显示当前页数据
		out.print("<center>");
		out.print("<table border=0 cellpadding=8 cellspacing=1 style=width:'80%'>");
		out.print("<tr>");
		out.print("<td width=80 >" + "ID" + "</td>");
		out.print("<td width=80 >" + "姓名" + "</td>");
		out.print("<td width=80 >" + "性别" + "</td>");
		out.print("<td width=80 >" + "年龄" + "</td>");
		out.print("<td width=80>" + "职位" + "</td>");
		out.print("<td width=80>" + "所学专业" + "</td>");
		out.print("<td width=80>" + "工作经验" + "</td>");
		out.print("<td width=80>" + "学历" + "</td>");
		out.print("<td width=80>" + "毕业院校" + "</td>");
		out.print("<td width=80>" + "电话" + "</td>");
		out.print("<td width=80>" + "email" + "</td>");
		out.print("<td width=80>" + "详细经历" + "</td>");
		out.print("<td width=100>" + "执行操作" + "</td>");
		out.print("</tr>");
		for (Job job : jobListTock) {
			out.print("<TR align='left'>");
			out.print("<td >" + job.getId() + "</td>");
 			out.print("<td >" + job.getName() + "</td>");
 			out.print("<td >" + StringUtil.convertSex(job.getSex()) + "</td>");
			out.print("<td >" + job.getAge() + "</td>");
			out.print("<td >" + job.getJob() + "</td>");
			out.print("<td >" + job.getSpecialty() + "</td>");
			out.print("<td >" + job.getExperience() + "</td>");
			out.print("<td >" + StringUtil.notNull(job.getStudyeffort()) + "</td>");
			out.print("<td >" + job.getSchool() + "</td>");
			out.print("<td >" + job.getTel() + "</td>");
			out.print("<td >" + job.getEmail() + "</td>");
			out.print("<td >" + job.getContent() + "</td>");
			out.print("<td >" + "<a href=\"job!Finish.action?id=" + job.getId() + "\"" + ">入库</a>  " + "</td>");
			out.print("</tr>"); 
		}
		out.print("</table>");
		out.print("</center>");
	%>
	</div>
	</table>
</body>
</html>

