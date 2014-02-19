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
<div class="grow gmt60 follower-page">
 <%@ include file="/WEB-INF/jsp/user/space/common/leftSize.jsp"%>
            <div class="main gprefix-1 gspan-25">
            <div class="gbtitle">
        <h2><a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/" class="gbtitle-link">个人主页</a><span class="entities">&gt;</span>被关注<span class="gbtitle-more">（全部<s:property value="spaceUser.followedCount" />人）</span></h2>
    </div>
    <ul class="gfollow-list">
    <s:iterator value="followedUsers">
    <li>
       <div class="gfollow-head">
                    <a href="<%=request.getContextPath()%>/i/<s:property value="id" />/" target="_blank">
                        <img width="48" height="48" src="<%=request.getContextPath()%><s:property value="avatar48" />">
                    </a>
       </div>
       <div class="gfollow-details gpack">
       <div class="gfollow-btns">
        <s:if test="curUser.id==id">
         </s:if>
         <s:else>
           <s:if test="isFollow==0">
               <a  data-operation="doFollow" data-params="id=<s:property value="id"/>" class="gbtn-focus" href="javascript: void 0;">加关注</a>
               </s:if>
               <s:else>
               <a class="gbtn-complete" data-event="click,mouseenter,mouseleave" data-operation="doUnfollow,followEnter,followLeave" data-params="id=<s:property value="id"/>"  href="javascript: void 0;">已关注</a>
               </s:else>
         </s:else>
       </div>
       <div class="gfollow-info">
       <p>
           <span><a href="<%=request.getContextPath()%>/i/<s:property value="id" />/" target="_blank"><s:property value="nickName" /></a></span><span><s:property value="followedCount" />人关注</span>
       </p>
       <s:if test="title!=null&&title!=''">
       <p><s:property value="title" /></p>
       </s:if>
       <p><s:property value="description" /></p>
       <p>
            <s:if test="gender==1">
            <span class="gicon-male"></span>
            </s:if>
             <s:if test="gender==2">
            <span class="gicon-female"></span>
            </s:if>
            <s:if test="blogUrl!=null&&blogUrl!=''">
            <span>
博客：
<a href="<s:property value="blogUrl" />"><s:property value="blogUrl" /></a>
</span>
            </s:if>
       </p>
       </div>
       </div>       
    </li>
        </s:iterator>
    </ul>
    <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${spaceUser.followedCount}"></p:pages>
            </div>
           
</div>
	    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/space.js"></script>
</div>
</body>
</html>