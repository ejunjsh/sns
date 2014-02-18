<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>XX主题站 - 发布文章</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/site.css"
	rel="stylesheet" />
</head>
<body>
   <div class="container">
		<div class="wrap grow gmt60 gpack create-article-success">
			<div class="gspan-22 main">
				<div class="gbreadcrumb">
					<ul>
						<li>
                        <a href="<%=request.getContextPath()%>/site/all">XX主题站</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/site/<s:property value="topic.id" />"><s:property value="topic.name" /></a>
                    </li>
					<li>发布文章</li>
					</ul>
				</div>
				<div class="main-panel">
                <h3>你的文章已成功提交！</h3>
                <p>审核结果我们会通过邮件和站内信及时通知您。</p>
                </div>
				</div>
			<div class="gspan-10 side">
				<a href="<%=request.getContextPath()%>/site/<s:property value="topic.id" />">去<s:property value="topic.name" />主题站</a>
			</div>
				</div>

		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
				</div>
				
</body>
</html>