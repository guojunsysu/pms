<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>项目管理系统 ——项目管理</title>
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
	 	   var boxs = $("input[type='checkbox'][id^='box_']");
	    	/** 给全选按钮绑定点击事件  */
	    	$("#checkAll").click(function(){
	    		// this是checkAll  this.checked是true
	    		// 所有数据行的选中状态与全选的状态一致
	    		boxs.attr("checked",this.checked);
	    	})
	    	
	    	/** 给每个数据行绑定点击事件：判断如果数据行都选中全选也应该选中，反之  */
	    	boxs.click(function(event){
	    		/** 去掉复选按钮的事件传播：点击复选会触发行点击：因为复选在行中 */
	    		event.stopPropagation();
	    		
	    		/** 判断当前选中的数据行有多少个  */
	    		var checkedBoxs = boxs.filter(":checked");
	    		/** 判断选中的总行数是否等于总行数：以便控制全选按钮的状态   */
	    		$("#checkAll").attr("checked",checkedBoxs.length == boxs.length);
	    	})
	    	
	    	/** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
	    	$("tr[id^='data_']").hover(function(){
	    		$(this).css("backgroundColor","#eeccff");
	    	},function(){
	    		$(this).css("backgroundColor","#ffffff");
	    	}).click(function(){
	    		/** 控制该行是否需要被选中 */
	    		/** 获取此行的复选框id */
	    		var checkboxId = this.id.replace("data_","box_");
	    		
	    		/** 触发本行的复选点击 */
	    		$("#"+checkboxId).trigger("click");
	    	})
	    	
	    	
	 	   /** 删除项目绑定点击事件 */
	 	   $("#delete").click(function(){
	 		   /** 获取到用户选中的复选框  */
	 		   var checkedBoxs = boxs.filter(":checked");
	 		   if(checkedBoxs.length < 1){
	 			   $.ligerDialog.error("请选择一个需要删除的用户！");
	 		   }else{
	 			   /** 得到用户选中的所有的需要删除的ids */
	 			   var ids = checkedBoxs.map(function(){
	 				   return this.value;
	 			   })
	 			   
	 			   $.ligerDialog.confirm("确认要删除吗?","删除用户",function(r){
	 				   if(r){
	 					   // alert("删除："+ids.get());
	 					   // 发送请求
	 					   window.location = "${ctx }/project/removeProject?ids=" + ids.get();
	 				   }
	 			   });
	 		   }
	 	   })
	    })
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：项目管理 &gt; 项目查询</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <!-- 查询区  -->
	  <tr valign="top">
	    <td height="30">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr>
			  <td class="fftd">
			  	<form name="empform" method="post" id="projectform" action="${ctx}/project/selectProject">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	<table>
		    					<tr>
		    						<td class="font3 fftd">主办科室：<input type="text" name="department" value="${department}">
		    						<td class="font3 fftd">分组：<input type="text" name="team" value="${team}">
					    			<td class="font3 fftd">政支客工：<input type="text" name="username" value="${username}">
					    		</tr>
					    		<tr>
					    			<td class="font3 fftd">是否重点：<input type="text" name="important" value="${project.important}"></td>
					    			<td class="font3 fftd">项目来源：<input type="text" name="source" value="${project.source}"></td>
					    		</tr>
					    		<tr>
					    			<td class="font3 fftd">项目状态：<input type="text" name="status" value="${project.status}"></td>
					    			<td class="font3 fftd">项目名称：<input type="text" name="projectname" value="${project.projectname}"></td>
					    		</tr>
					    	</table>
					    </td>
					    
					    <tr><td class="main_tdbor"></td></tr>
					    
					    <tr><td align="left" class="fftd">
					    	<input type="submit" value="搜索"/>&nbsp;&nbsp;
					    	<input id="delete" type="button" value="删除"/>
					    </td></tr>
					    	
					    </td>
					  </tr>
					</table>
				</form>
			  </td>
			</tr>
		  </table>
		</td>
	  </tr>
	  
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit" align="center">
			  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
			  <td>项目时间</td>
			  <td>主办科室</td>
			  <td>分组</td>
			  <td>政支客工</td>
			  <td>是否重点项目</td>
			  <td>项目来源</td>
			  <td>项目状态/阶段</td>
			  <td>项目名称</td>
			  
			  <td align="center">操作</td>
			</tr>
			<c:forEach items="${requestScope.projects}" var="project" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">
					<td><input type="checkbox" id="box_${stat.index}" value="${project.id}"></td>
					  <td><f:formatDate value="${project.createtime}" 
								type="date" dateStyle="long"/>
								</td>
					  <td>${project.user.department }</td>
					  <td>${project.user.team }</td>
					  <td>${project.user.username }</td>
					  <td>${project.important }</td>
					  <td>${project.source }</td>
					  <td>${project.status }</td>		  
					  <td>${project.projectname }</td>	
					 
					<td align="center" width="40px;">
					    <a href="${ctx}/project/selectProjectDetail?id=${project.id}">
					    <img title="详情" src="${ctx}/images/prev.gif"/></a>
					    
					 	<a href="${ctx}/project/updateProject?flag=1&id=${project.id}">
						<img title="修改" src="${ctx}/images/update.gif"/></a>
					</td>
				</tr>
			</c:forEach>
		  </table>
		</td>
	  </tr>
	  
	  <!-- 分页标签 -->
	  <tr valign="top"><td align="center" class="font3">
	  	 <fkjava:pager
	  	        pageIndex="${requestScope.pageModel.pageIndex}" 
	  	        pageSize="${requestScope.pageModel.pageSize}" 
	  	        recordCount="${requestScope.pageModel.recordCount}" 
	  	        style="digg"
	  	        submitUrl="${ctx}/project/selectProject?pageIndex={0}&department=${project.user.department}&team=${project.user.department}&username=${project.user.team }&important=${project.important }&source=${project.source }&status=${project.status }&projectname=${project.projectname }"/>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>