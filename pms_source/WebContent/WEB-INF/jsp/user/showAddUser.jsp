<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>项目管理系统——添加用户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
	
	$(function(){
	
		/** 动态显示部门和组别 */
		$("#role").change(function(){
			
			var role = $(this).val();
			if(role == "管理员" || role == "领导"){
				$("#department").css("display","none");
				$("#team").css("display","none");
			}else if(role == "科室负责人"){
				$("#department").css("display","inline");
				$("#team").css("display","none");
			}else if(role == "员工" || role=="组长"){
				$("#department").css("display","inline");
				$("#team").css("display","inline");
			}
		});
		
    	/** 员工表单提交 */
		$("#userForm").submit(function(){
			var username = $("#username");
			var role = $("#role");
			var loginname = $("#loginname");
			var password = $("#password");
			var department = $("#department1");
			var team = $("#team1");
			var msg = "";
			if ($.trim(username.val()) == ""){
				msg = "姓名不能为空！";
				username.focus();
			}else if ($.trim(role.val()) == ""){
				msg = "角色不能为空！";
				role.focus();
			}else if ($.trim(loginname.val()) == ""){
				msg = "登录名不能为空！";
				loginname.focus();
			}else if ($.trim(password.val()) == ""){
				msg = "密码不能为空！";
				password.focus();
			}else if ($.trim(department.val()) == "" && (role.val() == "科室负责人" || role.val() == "员工")){
				msg = "部门不能为空！";
				department.focus();
			}else if ($.trim(team.val()) == "" && $.trim(role.val())== "员工"){
				msg = "组别不能为空！";
				team.focus();
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#userForm").submit();
		});
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：用户管理  &gt; 添加用户</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/user/addUser" id="userForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">姓&nbsp;名：<input type="text" name="username" id="username" size="20"/></td>
		    			<td class="font3 fftd">角&nbsp;色：
		    			<select id="role" name="role">
							<option value="管理员">管理员</option>
							<option value="领导">领导</option>
							<option value="科室负责人">科室负责人</option>
							<option value="组长">组长</option>
							<option value="员工">员工</option>
						</select>
						</td>
		    		</tr>
		    			
		    		<tr>
		    			<td class="font3 fftd">登录名：<input name="loginname" id="loginname" size="20" /></td>
		    			<td class="font3 fftd">密&nbsp;码：<input name="password" id="password" size="20" /></td>
		    		</tr>
		    		
		    		<tr>
		    			<td id="department" class="font3 fftd" style="display:none">部&nbsp;门：<input type="text" name="department" id="department1" size="20"/></td>
		    			<td id="team" class="font3 fftd" style="display:none">组&nbsp;别：<input type="text" name="team" id="team1" size="20"/></td>
		    		</tr>
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="添加">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>