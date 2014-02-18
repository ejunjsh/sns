<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title> 站内信|消息中心-- XX网</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css" href="<%=request.getContextPath()%>/staticFile/css/settings.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
	
		<div class="grow gmt60 gpack">
			<div class="side gspan-6">
				<h1>消息中心</h1>
				<ul>
				<li class="gactived">
<span class="gicon-message"></span>
<span>站内信</span>
</li>
					<li>
<a class="gicon-notice" href="<%=request.getContextPath()%>/infoCenter/notice/"></a>
<a href="<%=request.getContextPath()%>/infoCenter/notice/">通知</a>
</li>

				</ul>
			</div>
			<div class="gspan-24 gprefix-1 message-index-page">
                <div class="gbtitle">
                <a href="<%=request.getContextPath()%>/infoCenter/message/send/" class="gbtn-primary gfr">写信</a>
            <h2>站内信</h2>
        </div>
<ul class="main-message" id="msgList">
      <s:iterator value="messages">  
      <s:if test="curUser.id==fromUser.id">
        <s:set name="tUser" value="toUser" />
        </s:if>
        <s:else>
        <s:set name="tUser" value="fromUser" />
        </s:else>
        <li class="">
            <div class="pt-pic">
                <a href="<%=request.getContextPath()%>/i/<s:property value="#tUser.id" />/" target="_blank" title="<s:property value="#tUser.nickName" />"><img onerror="lib.errorImg(this)"  src="<%=request.getContextPath()%><s:property value="#tUser.avatar48" />" width="48" height="48" alt="<s:property value="#tUser.nickName" />"></a>
                
            </div>
            <div class="pt-txt">
                <s:if test="curUser.id==fromUser.id">
                <p>发给<a href="<%=request.getContextPath()%>/i/<s:property value="#tUser.id" />/" target="_blank"><s:property value="#tUser.nickName" /></a>：<s:property value="content" escape="false" /></p>
                </s:if>
                <s:else>
                <s:property value="content" escape="false" />
                </s:else>
                <p class="pt-txt-d">
                    <span class="gfl"><s:property value="createdDateF" /></span>
                    <span class="gfr">
                        <a href="javascript: void 0;" data-operation="doDeleteGroupMessage" data-params="group=<s:property value="group" />&confirm=删除所有对话？" class="msg-hover">删除</a>
                        <span class="gsplit msg-hover">|</span>
                        <a href="<%=request.getContextPath()%>/infoCenter/message/send/<s:property value="#tUser.id" />/">共<s:property value="messageCount" />封站内信</a>
                        <span class="gsplit">|</span>
                        <a href="<%=request.getContextPath()%>/infoCenter/message/send/<s:property value="#tUser.id" />/">回复</a>
                    </span>
                </p>
            </div>
        </li>
        </s:iterator>
    </ul>
     <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${recordCount}"></p:pages>
              </div>
            </div>
		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/message.js"></script>
	</div>
</body>
</html>