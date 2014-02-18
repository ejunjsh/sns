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
<div class="grow gmt60 activity-page">
 <%@ include file="/WEB-INF/jsp/user/space/common/leftSize.jsp"%>
            <div class="main gprefix-1 gspan-25">
            <div class="gbtitle">
        <h2><a href="/i/<s:property value="spaceUser.id" />/" class="gbtitle-link">个人主页</a><span class="entities">&gt;</span>动态<span class="gbtitle-more">（全部<s:property value="spaceUser.activityCount" />个）</span></h2>
    </div>
 <div class="gprefix-1 gsuffix-2">
    <%@ include file="/WEB-INF/jsp/user/space/common/activities.jsp"%>
     <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${spaceUser.activityCount}"></p:pages>
 </div>
   
  </div>
           
</div>
	    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="/staticFile/js/space.js"></script>
</div>
</body>
</html>