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
	    	
	    	/** 修改项目绑定点击事件 */
	 	   $("#setproject").click(function(){
	 	   	window.location = "${ctx }/project/updateProject?flag=1&id=" + ${project.id};
	 	   })
	    	
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
	    	
	    	/** 新建日志绑定点击事件 */
	 	   $("#new").click(function(){
	 	   	window.location = "${ctx }/log/addLog?flag=1&project_id=" + ${project.id};
	 	   })
	    	
	    	/** 删除日志绑定点击事件 */
	 	   $("#delete").click(function(){
	 		   /** 获取到用户选中的复选框  */
	 		   var checkedBoxs = boxs.filter(":checked");
	 		   if(checkedBoxs.length < 1){
	 			   $.ligerDialog.error("请选择一个需要删除的日志！");
	 		   }else{
	 			   /** 得到用户选中的所有的需要删除的ids */
	 			   var ids = checkedBoxs.map(function(){
	 				   return this.value;
	 			   })
	 			   
	 			   $.ligerDialog.confirm("确认要删除吗?","删除日志",function(r){
	 				   if(r){
	 					   // alert("删除："+ids.get());
	 					   // 发送请求
	 					   window.location = "${ctx }/log/removeLog?ids=" + ids.get() + "&project_id=" + ${project.id};
	 				   }
	 			   });
	 		   }
	 	   })
	    	
	    	/** 下载日志功能 */
	    	$("a[id^='down_']").click(function(){
	    		/** 得到需要下载的文档的id */
	    		var id = this.id.replace("down_","");
	    		/** 下载该文档 */
	    		window.location = "${ctx}/log/downLoad?id="+id;
	    	})
	    	
	    	
	    	
	    })
	    
	    function down(id){
	    	$("a[id='down_"+id+"']").trigger("click");
	    }
	    
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：项目管理 &gt; 项目详情</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">	  
	  
	  <!-- 项目详情 -->
	 <tr valign="top">
	  <tr><td>项目详情
	  <input id="setproject" type="button" value="修改项目"/>
	  </td></tr>
	</tr>
	  
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    
		    <tr align="center">
			  <td class="main_trbg_tit">项目时间</td>
			  <td class="main_trbg">
			  	<f:formatDate value="${project.createtime}" 
								type="date" dateStyle="long"/></td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">主办科室</td>
			  <td class="main_trbg">${project.user.department}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">组别</td>
			  <td class="main_trbg">${project.user.team}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">政支客工</td>
			  <td class="main_trbg">${project.user.username}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">是否重点项目</td>
			  <td class="main_trbg">${project.important}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">项目来源</td>
			  <td class="main_trbg">${project.source}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">客户经理</td>
			  <td class="main_trbg">${project.manager}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">项目状态/所处阶段</td>
			  <td class="main_trbg">${project.status}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">业务/产品</td>
			  <td class="main_trbg">${project.product}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">项目名称</td>
			  <td class="main_trbg">${project.projectname}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">项目概况</td>
			  <td class="main_trbg">${project.overview}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">年收入预测（万/年）</td>
			  <td class="main_trbg">${project.income_year}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">签约年限</td>
			  <td class="main_trbg">${project.time}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">合同收入预测（万元）</td>
			  <td class="main_trbg">${project.income}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">预计广州年收入预测</td>
			  <td class="main_trbg">${project.income_gz}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">增/存量</td>
			  <td class="main_trbg">${project.increase_stock}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">成本测算</td>
			  <td class="main_trbg">${project.cost}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">预计成功时间</td>
			  <td class="main_trbg">${project.time_success}</td>
			</tr>
			
			<tr align="center">
			  <td class="main_trbg_tit">预计挂标时间</td>
			  <td class="main_trbg">${project.time_tagged}</td>
			</tr>
			
		  </table>
		</td>
	  </tr>
	  
	  <!-- 项目日志 -->
	 <tr valign="top">
	  <tr><td><br><br>项目日志
	  <input id="new" type="button" value="新建日志"/>&nbsp;&nbsp;
	  <input id="delete" type="button" value="删除日志"/>
	  </td></tr>
	</tr>
	
	<!-- 数据展示 -->
	<tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit" align="center">
			  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
			  <td>日志时间</td>
			  <td>本周进展</td>
			  <td>下周计划</td>
			  <td>问题及风险点</td>
			  <td>文件名</td>			  
			  <td align="center">修改</td>
			  <td align="center">下载</td>
			</tr>
			<c:forEach items="${requestScope.logs}" var="log" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">
					<td><input type="checkbox" id="box_${stat.index}" value="${log.id}"></td>
					  <td><f:formatDate value="${log.createtime}" 
								type="date" dateStyle="long"/>
								</td>
					  <td>${log.thisweek }</td>
					  <td>${log.nextweek }</td>
					  <td>${log.problem }</td>
					  <td>${log.filename }</td>
					 
					<td align="center" width="40px;"><a href="${ctx }/log/updateLog?flag=1&project_id=${project.id }&id=${log.id }" >
							<img title="修改" src="${ctx }/images/update.gif"/></a>
					  </td>
					  <td align="center"  width="40px;">
					  <c:if test="${not empty log.filename }">
					  		<a href="#" id="down_${log.id }">
							<img width="20" height="20" title="下载" src="${ctx }/images/downLoad.png"/></a>
					  </c:if>
					  </td>
				</tr>
			</c:forEach>
		  </table>
		</td>
	  </tr>
	  
	</table>
	<div style="height:10px;"></div>
</body>
</html>