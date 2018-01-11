<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/script/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/script/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/script/zTree/css/zTreeStyle/zTreeStyle.css">
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/script/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/script/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/script/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/script/zTree/js/jquery.ztree.all.min.js"></script>
	
	<title>首页</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north', height:100, border:false" style="background-color:#e3e3e3;">
		<div class="top-bg"></div>
	</div>
	<jsp:include page="west.jsp"></jsp:include>
	 <%-- <jsp:include page="west2.jsp"></jsp:include> --%>
	<div data-options="region:'south', height:20" style="background-color:#f3f3f3;"></div>
	<!-- collapsed:true是否折叠 -->
	<div data-options="region:'east', width:80, collapsed:true, title:'备注', split:true"></div>
	<div data-options="region:'center', title:'详情页'">
		<div id="main_content_tabs" class="easyui-tabs" data-options="fit:true,border:false"></div>
	</div>
</body>
</html>