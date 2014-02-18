<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title><s:property value="spaceUser.nickName" />主页 - XX网</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/user.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
<div class="grow gmt60 me-posts-page">
 <%@ include file="/WEB-INF/jsp/user/space/common/leftSize.jsp"%>
            <div class="main gprefix-1 gspan-25">
            <div class="gbtitle">
        <h2><a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/" class="gbtitle-link">个人主页</a><span class="entities">&gt;</span>帖子<span class="gbtitle-more">（全部<s:property value="spaceUser.postCount" />个）</span></h2>
    </div>
<ul class="titles  gprefix-1 gsuffix-2">
<s:iterator value="myPosts">
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
    <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${spaceUser.postCount}"></p:pages>
            </div>
           
</div>
	    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/space.js"></script>
</div>
</body>
</html>