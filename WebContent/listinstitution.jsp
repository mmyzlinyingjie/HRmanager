<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.butterfly.po.Institution"%>
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
	List<Institution> institutionListTock = (List<Institution>) ActionContext.getContext().getSession().get("institutionListTock");
	// List<Train> trainList = (List<Train>) request.getAttribute("list");
%>

<body class="ContentBody">
	<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" align="center">奖惩信息列表</th>
  </tr>
	<%
		//显示当前页数据
		out.print("<center>");
		out.print("<table border=0 cellpadding=8 cellspacing=1 style=width:'180%'>");
		out.print("<tr>");
		out.print("<td width=180 >" + "ID" + "</td>");
		out.print("<td width=180 >" + "奖惩名称" + "</td>");
		out.print("<td width=180 >" + "奖惩原因" + "</td>");
		out.print("<td width=180 >" + "奖惩说明" + "</td>");
		out.print("<td width=180 >" + "執行操作" + "</td>");
		out.print("</tr>");
		for (Institution institution : institutionListTock) {
			out.print("<TR align='left'>");
			out.print("<td >" + institution.getId() + "</td>");
 			out.print("<td >" + institution.getName() + "</td>");
 			out.print("<td >" + institution.getReason() + "</td>");
 			out.print("<td >" + institution.getExplain() + "</td>");
			out.print("<td >" + "<a href=\"institution!Delete.action?id=" + institution.getId() + "\"" + ">刪除</a>  " + "</td>");
			out.print("</tr>"); 
		}
		out.print("</table>");
		out.print("</center>");
	%>
	</div>
	</table>
</body>
</html>

