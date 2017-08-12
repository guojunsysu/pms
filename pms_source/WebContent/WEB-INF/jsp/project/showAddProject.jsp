<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>项目管理系统——添加项目</title>
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
    	/** 项目表单提交 */
		$("#projectForm").submit(function(){
			var important = $("#important");
			var source = $("#source");
			var manager = $("#manager");
			var status = $("#status");
			var product = $("#product");
			var projectname = $("#projectname");
			var overview = $("#overview");
			var income_year = $("#income_year");
			var time = $("#time");
			var income = $("#income");
			var income_gz = $("#income_gz");
			var increase_stock = $("#increase_stock");
			var cost = $("#cost");
			var time_success = $("#time_success");
			var time_tagged = $("#time_tagged");
			var msg = "";
			if ($.trim(important.val()) == ""){
				msg = "重要性不能为空！";
				important.focus();
			}else if ($.trim(source.val()) == ""){
				msg = "项目来源不能为空！";
				source.focus();
			}else if ($.trim(manager.val()) == ""){
				msg = "项目经理不能为空！";
				manager.focus();
			}else if ($.trim(status.val()) == ""){
				msg = "项目阶段/状态不能为空！";
				status.focus();
			}else if ($.trim(product.val()) == ""){
				msg = "项目业务/产品不能为空！";
				product.focus();
			}else if ($.trim(projectname.val()) == ""){
				msg = "项目名称不能为空！";
				projectname.focus();
			}else if ($.trim(overview.val()) == ""){
				msg = "项目概况不能为空！";
				overview.focus();
			}else if ($.trim(income_year.val()) == ""){
				msg = "年收入预测不能为空！";
				income_year.focus();
			}else if (isNaN(income_year.val())){
				msg = "年收入必须是数字！";
				income_year.focus();
			}else if ($.trim(time.val()) == ""){
				msg = "签约年限不能为空！";
				time.focus();
			}else if (/^[0-9]*[1-9][0-9]*$/.test(time.val()) == ""){
				msg = "签约年限必须是整数！";
				time.focus();
			}else if ($.trim(income.val()) == ""){
				msg = "合同收入预测不能为空！";
				income.focus();
			}else if (isNaN(income.val())){
				msg = "合同收入必须是数字！";
				income.focus();
			}else if ($.trim(income_gz.val()) == ""){
				msg = "广州年收入预测不能为空！";
				income_gz.focus();
			}else if (isNaN(income_gz.val())){
				msg = "广州年收入必须是数字！";
				income_gz.focus();
			}else if ($.trim(increase_stock.val()) == ""){
				msg = "增/存不能为空！";
				increase_stock.focus();
			}else if ($.trim(cost.val()) == ""){
				msg = "成本测算不能为空！";
				cost.focus();
			}else if (isNaN(cost.val())){
				msg = "成本测算必须是数字！";
				cost.focus();
			}else if ($.trim(time_success.val()) == ""){
				msg = "预计成功时间不能为空！";
				time_success.focus();
			}else if ($.trim(time_tagged.val()) == ""){
				msg = "预计成功时间不能为空！";
				time_tagged.focus();
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
		});
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：项目管理&gt; 添加项目</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/project/addProject" id="projectForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
	  
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
			
			<tr align="center">
			  <td class="main_trbg_tit">是否重点项目</td>
			  <td class="main_trbg">
			  <select id='important' name="important">
					<option value="是">是</option>
					<option value="否">否</option>
				</select>
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">项目来源</td>
			  <td class="main_trbg">
			  <input type="text" name="source" id="source" size="20">
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">客户经理</td>
			  <td class="main_trbg">
			  <input type="text" name="manager" id="manager" size="20">
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">项目状态/所处阶段</td>
			  <td class="main_trbg">
			  <input type="text" name="status" id="status" size="20">
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">业务/产品</td>
			  <td class="main_trbg">
			  <input type="text" name="product" id="product" size="20">
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">项目名称</td>
			  <td class="main_trbg">
			  <input type="text" name="projectname" id="projectname" size="20">
			  </td>
			</tr>
			
			<tr align="center" height="5" style="height: 100px; ">
			  <td class="main_trbg_tit">项目概况</td>
			  <td class="main_trbg">
			  <textarea name="overview" id="overview" rows="5" cols="20" "></textarea>
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">年收入预测（万/年）</td>
			  <td class="main_trbg">
			  <input type="text" name="income_year" id="income_year" size="20">
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">签约年限（年）</td>
			  <td class="main_trbg">
			  <input type="text" name="time" id="time" size="20">
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">合同收入预测（万/年）</td>
			  <td class="main_trbg">
			  <input type="text" name="income" id="income" size="20">
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">广州年收入预测（万/年）</td>
			  <td class="main_trbg">
			  <input type="text" name="income_gz" id="income_gz" size="20">
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">增/存量</td>
			  <td class="main_trbg">
			  <input type="text" name="increase_stock" id="increase_stock" size="20">
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">成本测算（万/年）</td>
			  <td class="main_trbg">
			  <input type="text" name="cost" id="cost" size="20">
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">预计成功时间</td>
			  <td class="main_trbg">
			  <input type="text" name="time_success" id="time_success" size="20">
			  </td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">预计挂标时间</td>
			  <td class="main_trbg">
			  <input type="text" name="time_tagged" id="time_tagged" size="20">
			  </td>
			</tr>
			
		  </table>
		</td>
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