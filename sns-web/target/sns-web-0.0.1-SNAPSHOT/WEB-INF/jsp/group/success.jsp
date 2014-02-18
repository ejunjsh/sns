<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title>XX小组 - 申请创建小组</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="/staticFile/css/group1.css"
	rel="stylesheet" />
</head>
<body>
   <div class="container">
		<div class="wrap grow gmt60 gpack create-group-success">
			<div class="gspan-22 main">
				<div class="gbreadcrumb">
					<ul>
						<li><a href="/group">小组</a></li>
						<li>申请小组</li>
					</ul>
				</div>
				<div class="main-panel">
                <h3>您的小组申请已成功提交！</h3>
                <p>审核结果我们会通过邮件和站内信及时通知您。</p>
                </div>
				</div>
			<div class="gspan-10 side">
				<a href="/group/all/">去全部小组页面 </a>
			</div>
				</div>

		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
				</div>
				
</body>
</html>