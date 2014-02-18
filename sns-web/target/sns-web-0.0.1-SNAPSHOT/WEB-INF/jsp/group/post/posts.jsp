<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>XX小组 - <s:property value="group.name" /></title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="/staticFile/css/group1.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
	<div class="grow gmt60 group-postlist-page">
	<div class="main gspan-21 gsuffix-1">
	    <div class="main-title">
		<img width="24" height="24" src="<s:property value="group.cover24"/>">
		<h2><s:property value="group.name"/>小组帖子列表</h2>
		<a id="newPost" class="gbtn" href="/group/<s:property value="group.id"/>/post/">发新帖</a>
		</div>
		<ul class="gtabs">
		<s:if test="isBest==1">
		   <li ><a href="/group/<s:property value="group.id"/>/posts/">全部帖子</a></li>
<li class="gtabs-curr">
<a href="/group/<s:property value="group.id" />/posts/digest/">精华区</a>
</li>
		</s:if>
		<s:else>
		<li class="gtabs-curr">全部帖子</li>
<li>
<a href="/group/<s:property value="group.id" />/posts/digest/">精华区</a>
</li>
		</s:else>
</ul>
<ul class="titles">
<s:iterator value="postInGroup">
<li>
<h3 class="titles-txt"><a target="_blank" href="/post/<s:property value="id" />/"><s:property value="title" />
    <s:if test="isTop==1">
                                <span class="gicon-top"></span>
                                </s:if>
                                <s:if test="isBest==1">
                                <span class="gicon-best"></span>
                                </s:if>
                        </a></h3>
                        <div class="titles-r-grey"><s:property value="commentCount" /><span class="titles-comment-icon">&nbsp;</span></div>
<p class="titles-b">
<span class="titles-b-l">
发表：
<a target="_blank" href="/i/<s:property value="postedByUserId" />/"><s:property value="postedByUser.nickName" /></a>
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
	<div class="side gspan-9 gprefix-1">
        <div>
            <a href="/group/<s:property value="group.id"/>/">返回<s:property value="group.name"/>小组主页 &gt;</a> </div>
    </div>

	</div>
			<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="/staticFile/js/group.js"></script>
</div>
</body>
</html>