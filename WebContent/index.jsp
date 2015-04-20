<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人力资源管理系统登录</title>

<script type="text/javascript">
	function resetValue() {
		document.getElementById("userName").value = "";
		document.getElementById("password").value = "";
	}
</script>

<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: 宋体
}

TD {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: 宋体
}

ERROR {
	padding: 10px 0;
}
</STYLE>

</HEAD>
<BODY>
	<FORM action="login" method="post">

		<DIV id=UpdatePanel1>
			<DIV id=div1
				style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
			<DIV id=div2
				style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
			<SCRIPT language=JavaScript>
				var speed = 20;
				var temp = new Array();
				var clipright = document.body.clientWidth / 2, clipleft = 0
				for (i = 1; i <= 2; i++) {
					temp[i] = eval("document.all.div" + i + ".style");
					temp[i].width = document.body.clientWidth / 2;
					temp[i].height = document.body.clientHeight;
					temp[i].left = (i - 1) * parseInt(temp[i].width);
				}
				function openit() {
					clipright -= speed;
					temp[1].clip = "rect(0 " + clipright + " auto 0)";
					clipleft += speed;
					temp[2].clip = "rect(0 auto auto " + clipleft + ")";
					if (clipright <= 0)
						clearInterval(tim);
				}
				tim = setInterval("openit()", 100);
			</SCRIPT>

			<DIV>&nbsp;&nbsp;</DIV>
			<DIV>
				<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
					<TBODY>
						<TR>
							<TD style="HEIGHT: 105px"><IMG src="images/login_1.gif"
								border=0></TD>
						</TR>
						<TR>
							<TD background=images/login_2.jpg height=300>
								<TABLE height=300 cellPadding=0 width=900 border=0>
									<TBODY>
										<TR>
											<TD colSpan=2 height=35></TD>
										</TR>
										<TR>
											<TD width=360></TD>
											<TD>
												<TABLE cellSpacing=0 cellPadding=2 border=0>
													<TBODY>
														<TR>
															<TD style="HEIGHT: 28px" width=60>账&nbsp;号</TD>
															<TD style="HEIGHT: 28px" width=150><INPUT
																name="user.userName" id="userName" type="text"
																style="WIDTH: 130px"></TD>
															<TD style="HEIGHT: 28px" width=370><SPAN
																id=RequiredFieldValidator3
																style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入登录名</SPAN></TD>
														</TR>
														<TR>
															<TD style="HEIGHT: 28px">密&nbsp;码</TD>
															<TD style="HEIGHT: 28px"><INPUT name="user.password"
																id="password" style="WIDTH: 130px" type="password"></TD>
															<TD style="HEIGHT: 28px"><SPAN
																id=RequiredFieldValidator4
																style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入密码</SPAN></TD>
														</TR>
														<TR>
															<TD style="HEIGHT: 18px"></TD>
															<TD style="HEIGHT: 18px"></TD>
															<TD style="HEIGHT: 18px"></TD>
														</TR>
														<TR>
															<TD></TD>
															<TD><INPUT id=btn
																style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
																onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("btn", "", true, "", "", false, false))'
																type=image src="images/login_button.gif" name=btn>
															</TD>
														</TR>
														<TR>
															<TD>&nbsp;</TD>
															<TD id="ERROR"><FONT color="red">${error}</FONT></TD>
														</TR>
													</TBODY>
												</TABLE>
											</TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>
						<TR>
							<TD><IMG src="images/login_3.jpg" border=0></TD>
						</TR>
					</TBODY>
				</TABLE>
			</DIV>
		</DIV>
	</FORM>
</BODY>
</HTML>
