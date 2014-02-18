<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>XX小组 - 全部小组热帖</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/group1.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
	<div class="grow gmt60 group-hot-page">
	<div class="main gspan-21 gsuffix-1">
	    <div class="gtitle">
<h2>全部小组热帖</h2>
</div>
<ul class="titles">
<s:iterator value="hotPosts">
<li>
<h3 class="titles-txt"><a target="_blank" href="<%=request.getContextPath()%>/post/<s:property value="id" />/"><s:property value="title" />
                        </a></h3>
                        <div class="titles-r-grey"><s:property value="commentCount" /><span class="titles-comment-icon">&nbsp;</span></div>
<p class="titles-b">
<span class="titles-b-l">
来自：
<a target="_blank" href="<%=request.getContextPath()%>/group/<s:property value="group.id" />/"><s:property value="group.name" /></a>
小组
</span>
<span class="titles-b-c">|</span>
<span class="titles-b-l">
发表：
<a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="postedByUserId" />/"><s:property value="postedByUser.nickName" /></a>
</span>
<s:if test="lastCommentDateF==null">
<span class="titles-b-r"> 发表时间：<s:property value="postedDateF" /> </span>
</s:if>
<s:else>
<span class="titles-b-r"> 最后回应：<s:property value="lastCommentDateF" /> </span>
</s:else>
</p>
</li>
</s:iterator>
</ul>
<p:pages pageSize="${pageSize }" pageNo="${ pageNo}" recordCount="${recordCount }"></p:pages>
	    </div>
	    <div class="side gspan-10">
	    <div class="side-links">
                <p><a href="<%=request.getContextPath()%>/group/all/">查看全部小组 &gt;</a></p>
            </div>
            <form method="get" class="gsearch" action="<%=request.getContextPath()%>/group/search/" id="groupSearch">
                <p>
                <input type="text" value="" placeholder="搜索小组" maxlength="10" name="key" class="gsearch-txt" id="word">
                <input type="submit" class="gsearch-bt gicon-search" value="搜索">
                </p>
            </form>
	    </div>
	  </div>
	  			<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/group.js"></script>
	  </div>
</body>
</html>