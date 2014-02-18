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
<div class="grow gmt60 group-page">
 <%@ include file="/WEB-INF/jsp/user/space/common/leftSize.jsp"%>
            <div class="main gprefix-1 gspan-25">
            <div class="gbtitle">
        <h2><a href="/i/<s:property value="spaceUser.id" />/" class="gbtitle-link">个人主页</a><span class="entities">&gt;</span>小组<span class="gbtitle-more">（全部<s:property value="spaceUser.groupCount" />个）</span></h2>
    </div>
<div class="group-list gprefix-1 gsuffix-2 gclear">
    <s:iterator value="myGroups">
   <dl class="gpack_u">
            <dt><a href="/group/<s:property value="id" />/" title="<s:property value="name" />" target="_blank"><img onerror="lib.errorImg(this)" width="48" height="48" src="<s:property value="cover48" />" alt="<s:property value="name" />"></a></dt>
            <dd><a href="/group/<s:property value="id" />/" title="<s:property value="name" />" target="_blank"><s:property value="name" /></a></dd>
        </dl>
	 </s:iterator> 
 </div>
    <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${spaceUser.groupCount}"></p:pages>
            </div>
           
</div>
	    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="/staticFile/js/space.js"></script>
</div>
</body>
</html>