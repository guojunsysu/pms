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
	 	   /** 导出excel功能 */
	    	$("#down").click(function(){
	    		window.location = "${ctx }/statis/downloadDepartment";
	    	})
	    });
	    	
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：统计报表 &gt; 科室汇总</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <tr><td>
	  <a href="#" id="down">
		<img width="20" height="20" title="导出excel" src="${ctx }/images/downLoad.png"/>
		</a>
		</td>
	  </tr>
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit" align="center">
			  <td rowspan="2" colspan="2">项目渠道<br>(具体到科室)</td>
			  <td colspan="2">重点关注</td>
			  <td colspan="2">正常推进</td>
			  <td colspan="2">停滞</td>
			  <td colspan="2">合计</td>
			</tr>
						
			<tr class="main_trbg_tit" align="center">
				<td>项目量</td>
			    <td>年收入规模（万）</td>
			    <td>项目量</td>
			    <td>年收入规模（万）</td>
			    <td>项目量</td>
			    <td>年收入规模（万）</td>
			    <td>项目量</td>
			    <td>年收入规模（万）</td>
			</tr>
			
			<c:forEach items="${requestScope.statiss}" begin="0" end="11" var="statis" varStatus="stat">
				<c:if test="${stat.index %4 == 0 }">
					<tr align="center" class="main_trbg">
				</c:if>
				<c:if test="${stat.index %12 == 0 }">
					<td rowspan="3">${statis.department}</td>
				</c:if>
				<c:if test="${stat.index %4 == 0 }">
					<td>${statis.team}</td>
				</c:if>
					<td id="count_1_${stat.index }">${statis.count }</td>
					<td id="sum_1_${stat.index }">${statis.sum }</td>
				<c:if test="${stat.index %4 == 3 }">
					</tr>
				</c:if>
			</c:forEach>
			
			<c:forEach items="${requestScope.statiss}" begin="12" end="15" var="statis" varStatus="stat">
				<c:if test="${stat.index %4 == 0 }">
					<tr align="center" class="main_trbg">
						<td colspan = "2">小结</td>
				</c:if>
					<td>${statis.count }</td>
					<td>${statis.sum }</td>
				<c:if test="${stat.index %4 == 3 }">
					</tr>
				</c:if>
			</c:forEach>
			
			<c:forEach items="${requestScope.statiss}" begin="16" end="27" var="statis" varStatus="stat">
				<c:if test="${stat.index %4 == 0 }">
					<tr align="center" class="main_trbg">
				</c:if>
				<c:if test="${(stat.index-4) %12 == 0 }">
					<td rowspan="3">${statis.department}</td>
				</c:if>
				<c:if test="${stat.index %4 == 0 }">
					<td>${statis.team}</td>
				</c:if>
					<td id="count_1_${stat.index }">${statis.count }</td>
					<td id="sum_1_${stat.index }">${statis.sum }</td>
				<c:if test="${stat.index %4 == 3 }">
					</tr>
				</c:if>
			</c:forEach>
			
			<c:forEach items="${requestScope.statiss}" begin="28" end="31" var="statis" varStatus="stat">
				<c:if test="${stat.index %4 == 0 }">
					<tr align="center" class="main_trbg">
						<td colspan = "2">小结</td>
				</c:if>
					<td>${statis.count }</td>
					<td>${statis.sum }</td>
				<c:if test="${stat.index %4 == 3 }">
					</tr>
				</c:if>
			</c:forEach>
			
			<c:forEach items="${requestScope.statiss}" begin="32" end="35" var="statis" varStatus="stat">
				<c:if test="${stat.index %4 == 0 }">
					<tr align="center" class="main_trbg">
						<td colspan = "2">合计</td>
				</c:if>
					<td>${statis.count }</td>
					<td>${statis.sum }</td>
				<c:if test="${stat.index %4 == 3 }">
					</tr>
				</c:if>
			</c:forEach>
			
		  </table>
		</td>
	  </tr>
	  
	</table>
</body>
</html>