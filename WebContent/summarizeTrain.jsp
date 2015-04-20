<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.butterfly.po.Train"%>
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
	List<Train> trainList = (List<Train>) ActionContext.getContext().getSession().get("list");
	// List<Train> trainList = (List<Train>) request.getAttribute("list");
%>

<body class="ContentBody">
	<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" align="center">培训总结</th>
  </tr>
	<%
		//显示当前页数据
		out.print("<center>");
		out.print("<table border=0 cellpadding=8 cellspacing=1 style=width:'100%'>");
		out.print("<tr>");
		out.print("<td width=100 >" + "ID" + "</td>");
		out.print("<td width=100 >" + "培训名称" + "</td>");
		out.print("<td width=100 >" + "培训目的" + "</td>");
		out.print("<td width=100 >" + "培训开始时间" + "</td>");
		out.print("<td width=100>" + "培训结束时间" + "</td>");
		out.print("<td width=100>" + "讲师" + "</td>");
		out.print("<td width=100>" + "培训材料" + "</td>");
		out.print("<td width=100>" + "培训效果" + "</td>");
		out.print("<td width=100>" + "培训是否完成" + "</td>");
		out.print("<td width=100>" + "培训总结" + "</td>");
		out.print("</tr>");
		for (Train train : trainList) {
			out.print("<TR align='left'>");
			out.print("<td >" + train.getId() + "</td>");
			out.print("<td >" + train.getName() + "</td>");
			out.print("<td >" + train.getPurpose() + "</td>");
			out.print("<td >" + train.getBeginTime() + "</td>");
			out.print("<td >" + train.getEndTime() + "</td>");
			out.print("<td >" + train.getTeacher() + "</td>");
			out.print("<td >" + train.getDatum() + "</td>");
			out.print("<td >" + StringUtil.notNull(train.getEffect()) + "</td>");
			if(train.getIsFinish() == 0)
				out.print("<td >" + "否" + "</td>");
			else
				out.print("<td >" + "是" + "</td>");
			out.print("<td >" + StringUtil.notNull(train.getSummarize()) + "</td>");
			out.print("</tr>");
		}
		out.print("</table>");
		out.print("</center>");
	%>
	</div>
	</table>
</body>
</html>

