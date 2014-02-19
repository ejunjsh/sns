<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>出现错误了哦</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link  type="text/css" href="<%=request.getContextPath()%>/staticFile/css/error.css" rel="stylesheet"/>
</head>
<body>
	<div class="container">
		<div class="wrap grow gmt60 error-page">
    <div class="gspan-32">
	   <div class="gbreadcrumb">
				<ul class="breadcrumb">
					<li><a href="<%=request.getContextPath()%>">XX首页</a></li>
					<li>错误页面</li>
				</ul>
			</div>
		</div>
		<div>
		<s:property value="exception.message"/>
		</div>
		<div>
		<s:property value="exceptionStack"/>
		</div>
		</div>
	</div>
</body>
</html>