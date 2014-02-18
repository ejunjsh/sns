<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title><s:property value="blog.title" />xx网站 - 全部主题</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/site.css"
	rel="stylesheet" />
</head>
<body>
    <div class="container">
    <div class="grow gmt60 all-sites-page">
        <div class="side gspan-6">
           <span class="all_sites">全部主题站</span>
           <ul>
           <s:iterator value="allTopics">
           <li>
			<a href="<%=request.getContextPath()%>/site/<s:property value="id"/>/">
			<img height="20" width="20" alt="<s:property value="name"/>" src="<s:property value="cover24"/>">
			<s:property value="name"/>
			</a>
			</li>
           </s:iterator>
           </ul>
        </div>
        <div class="main gspan-24 gprefix-1">
        <div class="gbtitle">
		<h1>全部主题站</h1>
		 <a class="gbtitle-rss" href="/site/rss/"></a>
		</div>
		<s:iterator value="allArticles">
		   <div class="article gspan-20">
                    <a class="article-pic" href="<%=request.getContextPath()%>/article/<s:property value="id"/>/" target="_blank">
                        <img height="129" width="166" alt="<s:property value="title"/>" src="<%=request.getContextPath()%><s:property value="cover160"/>">
                    </a>
                    <h2>
                        <a target="_blank" href="<%=request.getContextPath()%>/article/<s:property value="id"/>/"><s:property value="title"/></a>
                    </h2>
                    <p class="article-info">
                        评论&nbsp;<s:property value="commentCount"/>
                        <span class="article-info-sp">|</span>
                        推荐&nbsp;<s:property value="recommendCount"/>
                    </p>
                    <p class="article-meta">
                    <a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="postedByUser.id"/>/"><s:property value="postedByUser.nickName"/></a>&nbsp;发表于&nbsp;<s:property value="postedDateF"/>
                    </p>
                    <p><s:property value="ContentNoHtml50"/><a target="_blank" href="<%=request.getContextPath()%>/article/<s:property value="id"/>/">查看全文</a></p>
                    <p class="article-from">来自：<a href="/site/<s:property value="articleTopic.id"/>/" target="_blank"><s:property value="articleTopic.name"/></a>&nbsp;主题站</p>
                </div>
		</s:iterator>
		<p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${recordCount}"></p:pages>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
	</div>
</body>
</html>