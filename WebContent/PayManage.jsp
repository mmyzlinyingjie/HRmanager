<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>薪酬管理</title>
 		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
		<style type="text/css">
			input{
				width:80px;
			}
		</style>
		<script type="text/javascript">
			var url;
			function searchEmployee(){
				$('#dg').datagrid('load',{
					s_employeeNo:$('#s_employeeNo').val(),
					s_name:$('#s_name').val(),									
				});				
			}
			
			function openEmployeeAddDialog(){
				$('#dlg').dialog('open').dialog("setTitle","新增薪酬信息");
				
				url=("stipend!save");
			}
			
			function openEmployeeModifyDialog(){
				var selectedRows=$("#dg").datagrid('getSelections');
				if(selectedRows.length!=1){
					$.messager.alert("系统提示","请选择一条要编辑的数据！");
					return;
				}
				var row=selectedRows[0];
				$("#dlg").dialog("open").dialog("setTitle","编辑薪酬信息");
				$("#stipendNoId").val(row.id);
				$("#name").val(row.name);
				$("#departmentNameSrc").val(row.departmentNameSrc);
				$('#basic').val(row.basic);
				$("#eat").val(row.eat);
				$("#house").val(row.house);
				$("#granttime").datebox("setValue", row.granttime);
				$("#duty").val(row.duty);
				$("#scot").val(row.scot);
				$("#punishment").val(row.punishment);
				$("#other").val(row.other);
				$("#effect").val(row.effect);
				url="stipend!update?stipendId="+row.id;
			}
			
	
			
			function closeEmployeeDialog(){
				$('#dlg').dialog("close");
				resetValue();
			}
			
			function resetValue(){
				$('#stipendNo').val("");
				$('#name').val("");
				$('#departmentNameSrc').combobox("setValue","");
				$('#basic').datebox("setValue","");
				$('#eat').combobox("setValue","");
				$('#house').val("");
				$('#granttime').val("");
				$('#duty').val("");
				$('#scot').val("");
				$("#punishment").val("");
				$("#other").val("");
				$("#effect").val("");
			}
			
			function saveEmployee(){
				$('#fm').form("submit",{
					url:url,
					onSubmit:function(){
						return $(this).form("validate");
					},
					success:function(result){
						if(result.errorMsg){
							$.messager.alert("系统提示",result.errorMsg);
							return;
						}else{
							$.messager.alert("系统提示","保存成功");
							resetValue();
							$("#dlg").dialog("close");
							$("#dg").datagrid("reload");
						}
					}
				});
			}
			function deleteDepartment(){
				var selectedRows=$("#dg").datagrid('getSelections');
				if(selectedRows.length==0){
					$.messager.alert("系统提示","请选择要删除的数据！");
					return;
				}
				var strIds=[];
				for(var i=0;i<selectedRows.length;i++){
					strIds.push(selectedRows[i].id);
				}
				var ids=strIds.join(",");
				$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
					if(r){
						$.post("stipend!delete",{delIds:ids},function(result){
							if(result.success){
								$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNum+"</font>条数据!");
								$("#dg").datagrid("reload");
							}else{
								$.messager.alert("系统提示",'<font color=red>'+selectedRows[result.errorIndex].departmentName+'</font>'+result.errorMsg);
							}
						},"json");
					}
				});
			}
		</script>
</head>
<body>
	<table id="dg" title="" class="easyui-datagrid" style="width:1000px;height:550px"
				fitColumns="true" rownumbers="true" fit="true" pagination="true" url="stipend" toolbar="#tb" >
			<thead>
				<tr>				
					<th field="cb" checkbox="true" ></th>
					<th data-options="field:'id'" width="5">编号</th>
					<th data-options="field:'name'" width="10">姓名</th>
					<th data-options="field:'departmentNameSrc'" width="10">部门</th>
					<th data-options="field:'basic'" width="10">基本薪金</th>
					<th data-options="field:'eat'" width="10">饭补</th>
					<th data-options="field:'house'" width="10">房补</th>
					<th data-options="field:'granttime'" width="10">工资发放时间</th>
					<th data-options="field:'duty'" width="10" >全勤奖</th>
					<th data-options="field:'scot'" width="10" >赋税</th>
					<th data-options="field:'punishment'" width="10" >罚款</th>
					<th data-options="field:'other'" width="10" >额外补助</th>
					<th data-options="field:'绩效'" width="10">绩效</th>
					<th data-options="field:'totalize'" width="10">总计</th>
	
				</tr>
			</thead>
	</table>
	<div id="tb" >
		<div>
			<a href="javascript:openEmployeeAddDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true">添加</a>
			<a href="javascript:openEmployeeModifyDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" plain="true">修改</a>
			<a href="javascript:deleteDepartment()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" plain="true">删除</a>
			&nbsp;编号:&nbsp;<input type="text" name="s_employeeNo" id="s_employeeNo" size="10" />
		    &nbsp;姓名:&nbsp;<input type="text" name="s_name" id="s_name" size="10" />
			<a href="javascript:searchEmployee()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" plain="true">搜索</a>
		</div>
		<br />
	</div>
	<div id="dlg" class="easyui-dialog" style="width:420px;height:260px;padding:20px 10px 0;" closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post" >
			<table>
				<tr>
					<td>&nbsp;编号:&nbsp;</td>
					<td><input type="text" name="stipend.id" id="stipendNoId" class="easyui-validatebox" ></td>
					<td>&nbsp;姓名:&nbsp;</td>
					<td><input type="text" name="stipend.name" id="name" class="easyui-validatebox" ></td>
				</tr>	
			<tr>
					<td>&nbsp;部门:&nbsp;</td>
					<td><input type="text" name="stipend.departmentNameSrc" id="departmentNameSrc" class="easyui-validatebox" ></td>
					<td>&nbsp;基本薪金:&nbsp;</td>
					<td><input type="text" name="stipend.basic" id="basic" class="easyui-validatebox" ></td>
				</tr>
				<tr>
					<td>&nbsp;饭补:&nbsp;</td>
					<td><input type="text" name="stipend.eat" id="eat" class="easyui-validatebox" ></td>
					<td>&nbsp;房补:&nbsp;</td>
					<td><input type="text" name="stipend.house" id="house" class="easyui-validatebox" ></td>
				</tr>
				<tr>
					<td>&nbsp;工资发放时间:&nbsp;</td>
					<td><input type="text" name="stipend.granttime" id="granttime" class="easyui-datebox"></td>
					<td>&nbsp;全勤奖:&nbsp;</td>
					<td><input type="text" name="stipend.duty" id="duty" class="easyui-validatebox" ></td>
				</tr>
				<tr>
					<td>&nbsp;赋税:&nbsp;</td>
					<td><input type="text" name="stipend.scot" id="scot" class="easyui-validatebox" ></td>
					<td>&nbsp;罚款:&nbsp;</td>
					<td><input type="text" name="stipend.punishment" id="punishment" class="easyui-validatebox" ></td>
				</tr>
				<tr>
					<td>&nbsp;额外补助:&nbsp;</td>
					<td><input type="text" name="stipend.other" id="other" class="easyui-validatebox" ></td>
					<td>&nbsp;绩效:&nbsp;</td>
					<td><input type="text" name="stipend.effect" id="effect" class="easyui-validatebox" ></td>
					
				</tr>
						
			</table>			
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:saveEmployee()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
		<a href="javascript:closeEmployeeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
	</div>
</body>
</html>