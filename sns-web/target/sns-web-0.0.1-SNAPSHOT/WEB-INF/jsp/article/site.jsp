<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title><s:property value="blog.title" />xx主题站-<s:property value="topic.name"/></title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="/staticFile/css/site.css"
	rel="stylesheet" />
</head>
<body>
    <div class="container">
    <div class="grow gmt60 site-page">
        <div class="side gspan-6">
           <span class="all_sites"><a href="/site/all">全部主题站</a></span>
           <ul>
           <s:iterator value="allTopics">
           <li>
			<a href="/site/<s:property value="id"/>/">
			<img height="20" width="20" alt="<s:property value="name"/>" src="<s:property value="cover24"/>">
			<s:property value="name"/>
			</a>
			</li>
           </s:iterator>
           </ul>
        </div>
        <div class="main gspan-24 gprefix-1">
        <div class="gbtitle">
		<h1><s:property value="topic.name"/></h1>
		<p class="gfr"><a class="gbtn-primary" href="/site/<s:property value="topic.id"/>/post">发布文章</a>
		</p>
		</div>
		<s:iterator value="allArticles">
		   <div class="article gspan-20">
                    <a class="article-pic" href="/article/<s:property value="id"/>/" target="_blank">
                        <img height="129" width="166" alt="<s:property value="title"/>" src="<s:property value="cover160"/>">
                    </a>
                    <h2>
                        <a target="_blank" href="/article/<s:property value="id"/>/"><s:property value="title"/></a>
                    </h2>
                    <p class="article-info">
                        评论&nbsp;<s:property value="commentCount"/>
                        <span class="article-info-sp">|</span>
                        推荐&nbsp;<s:property value="recommendCount"/>
                    </p>
                    <p class="article-meta">
                    <a target="_blank" href="/i/<s:property value="postedByUser.id"/>/"><s:property value="postedByUser.nickName"/></a>&nbsp;发表于&nbsp;<s:property value="postedDateF"/>
                    </p>
                    <p><s:property value="ContentNoHtml100"/><a target="_blank" href="/article/<s:property value="id"/>/">查看全文</a></p>
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