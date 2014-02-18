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
	href="/staticFile/css/user.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
<div class="grow gmt60 answer-page">
 <%@ include file="/WEB-INF/jsp/user/space/common/leftSize.jsp"%>
            <div class="main gprefix-1 gspan-25">
            <div class="gbtitle">
        <h2><a href="/i/<s:property value="spaceUser.id" />/" class="gbtitle-link">个人主页</a><span class="entities">&gt;</span>回答<span class="gbtitle-more">（全部<s:property value="spaceUser.answerCount" />个）</span></h2>
    </div>
    <ul class="answer_list">
    <s:iterator value="myAnswers">
    <li class="answer gclear">
            <h2><a href="/question/<s:property value="questionId" />/" target="_blank"><s:property value="question.title" /></a></h2>
            <p><s:property value="contentNoHtml100" escape="false"/><a href="/answer/<s:property value="id" />/redirect/" target="_blank">查看全文</a></p>
            <span class="answer_list-num"><s:property value="votedUpCount"/>人支持</span>
        </li>
        </s:iterator>
    </ul>
    <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${spaceUser.answerCount}"></p:pages>
            </div>
           
</div>
	    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="/staticFile/js/space.js"></script>
</div>
</body>
</html>