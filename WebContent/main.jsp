<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function() {
		var treeData = [ {
			text : "人力资源管理系统",
			children : [ {
				text : "部门管理",
				attributes : {
					url : "DepartmentManage.jsp"
				}
			}, {
				text : "员工管理",
				attributes : {
					url : "EmployeeManage.jsp"
				}
			}, {
				text : "培训管理",
				state : "closed",
				children : [ {
					text : "培训计划录入",
					attributes : {
						url : "addTrain.jsp"
					}
				}, {
					text : "培训计划查看",
					attributes : {
						// url : "detailTrain.jsp"
						url : "train!List"
					}
				}, {
					text : "培训总结查看",
					attributes : {
						url : "summarizeTrain.jsp"
					}
				} ],
				/* attributes : {
					url : "Train.jsp"
				} */
			},  {
				text : "招聘管理",
				state : "closed",
				children : [ {
					text : "应聘信息录入",
					attributes : {
						url : "addRecruit.jsp"
					}
				}, {
					text : "应聘信息查看",
					attributes : {
						// url : "detailTrain.jsp"
						url : "job!List"
					}
				}, {
					text : "人才浏览",
					attributes : {
						url : "job!ListTock"
					}
				} ],
				
			},{
				text : "奖惩管理",
				state : "closed",
				children : [ {
					text : "奖惩信息录入",
					attributes : {
						url : "addinstitution.jsp"
					}
				}, {
					text : "奖惩信息查看",
					attributes : {
						// url : "detailTrain.jsp"
						url : "institution!List"
					}
				} ],
				
			},{
				text : "薪酬管理",
				attributes : {
					url : "PayManage.jsp"
				}
			} ]
		} ];
		//实例化树型
		$("#tree").tree({
			data : treeData,
			lines : true,
			onClick : function(node) {
				if (node.attributes) {
					openTab(node.text, node.attributes.url);
				}
			}
		})
		//新增tab
		function openTab(text, url) {
			if ($("#tabs").tabs('exists', text)) {
				$("#tabs").tabs('select', text);
			} else {
				var content = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%;' src="
						+ url + "></iframe>";
				$("#tabs").tabs('add', {
					title : text,
					closable : true,
					content : content
				});
			}
		}
	});
</script>
<title>人力资源管理系统主界面</title>
<%
	//权限验证
	if (session.getAttribute("currentUser") == null) {
		response.sendRedirect("index.jsp");
		return;
	}
%>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'"
		style="height: 100px; background-color: #F9F7F8">
		<!-- C6C6C6 
		<B>人力资源管理系统 管理中心</B></FONT>
		-->
		<!--  
		<img alt="" src="images/main.jpg"
			style="height: 98px; width: 700px; float: left;">
		-->
		<!--
 		<br>
 		-->
		<center>
			<marquee>
				<font size=+4 color=#B8860B>人力资源管理系统 管理中心</font>
			</marquee>
		</center>

		<div style="margin: 20px 20px 0px; float: left;">
			当前登录用户：&nbsp;<a href=""><font color="red">${currentUser.userName }</font></a>
		</div>

		<div style="margin: 20px 20px 0px; float: right;">
			<a href=""><font color="red"></font></a> <a href="index.jsp"><font
				color="red">安全退出</font></a>
		</div>


	</div>
	<div data-options="region:'south'" style="height: 30px; padding: 5px;"
		align="center">
		版权所有&nbsp;&nbsp;<a href="#">米朵</a>
	</div>
	<div data-options="region:'west',split:true" title="导航菜单"
		style="width: 200px;">
		<ul id="tree" class="easyui-tree"></ul>
	</div>
	<div data-options="region:'center'">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" style="padding: 10px">
				<div align="center" style="padding-top: 100px;">
					<font color="red" size="7">欢迎使用</font>
				</div>
			</div>
		</div>
	</div>
</body>
</html>