<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>XX - 全部日志</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/blog1.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
	<div class="grow gmt60 all-blogs-page">
	<div class="main gspan-21 gsuffix-1">
	    <h1 class="gbtitle">全部日志<span class="more">(全部<s:property value="recordCount"/>篇)</span></h1>
 <ul class="blog_list">
    <s:iterator value="blogs">
    <li class="blog">
    <h2><a target="_blank" href="<%=request.getContextPath()%>/blog/<s:property value="id" />/"><s:property value="title"/></a></h2>
    <p class="blog-meta"><a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="postedByUserId" />/"><s:property value="postedByUser.nickName" /></a>发表于<span><s:property value="postedDateF" /></span></p>
    <p class="blog-num">评论&nbsp;<s:property value="commentCount" /><span class="blog-num-sp">|</span>推荐&nbsp;<s:property value="recommendCount" /></p>
    <p><s:property value="contentNoHtml100" /><a target="_blank" href="<%=request.getContextPath()%>/blog/<s:property value="id" />/">查看全文</a></p>
    </li>
    </s:iterator>
</ul>
<p:pages pageSize="${pageSize }" pageNo="${ pageNo}" recordCount="${recordCount }"></p:pages>
	    </div>
	  </div>
	  			<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
	  </div>
</body>
</html>