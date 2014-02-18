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
	href="<%=request.getContextPath()%>/staticFile/css/group1.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
	<div class="grow gmt40 members-page">
	   <div class="side">
    <form method="get" class="gsearch" action="<%=request.getContextPath()%>/group/<s:property value="group.id" />/members/" id="memberSearch">
        <p>
        <input type="text" value="" placeholder="搜索此小组成员" maxlength="20" name="key" class="gsearch-txt" id="memberNick">
        <input type="submit" class="gsearch-bt gicon-search" value="搜索">
        </p>
    </form>
    <div class="side-back">
        <a href="<%=request.getContextPath()%>/group/<s:property value="group.id" />/">返回<span><s:property value="group.name" /></span>&nbsp;&gt;</a>
    </div>
</div>
<div class="main gspan-21">
   <h1 class="gbtitle"><a href="<%=request.getContextPath()%>/group/<s:property value="group.id" />/" title="<s:property value="group.name" />"><img height="24"  src="<%=request.getContextPath()%><s:property value="group.icon" />" width="24"></a><s:property value="group.name" />小组成员</h1>
   <s:if test="pageNo==1&&(key==null||key=='')">
   <h2 class="main-title">
管理员
<span>(1)</span>
</h2>
<div class="members admins gspan-20 groupMembers">
        
        

<dl class="gpack_u">
    <dt>
        <a href="<%=request.getContextPath()%>/i/<s:property value="group.createdByUser.id" />/" title="<s:property value="group.createdByUser.nickName" />" target="_blank">
            <img width="48" height="48" src="<%=request.getContextPath()%><s:property value="group.createdByUser.avatar48" />" alt="<s:property value="group.createdByUser.nickName" />">
        </a>
    </dt>
    <dd>
        <a href="<%=request.getContextPath()%>/i/<s:property value="group.createdByUser.id" />/" title="<s:property value="group.createdByUser.nickName" />" target="_blank"><s:property value="group.createdByUser.nickName" /></a>
        <span title="组长" class="gicon-leader">组长</span>
        
        </dd>
</dl> 
    </div>
    </s:if>
    <h2 class="main-title">成员<span>(<s:property value="recordCount" />)</span></h2>
    
    <div class="members gspan-19 groupMembers">
    <s:iterator value="members">
      <dl class="gpack_u">
    <dt>
        <a href="<%=request.getContextPath()%>/i/<s:property value=".id" />/" title="<s:property value="nickName" />" target="_blank">
            <img width="48" height="48" src="<%=request.getContextPath()%><s:property value="avatar48" />" alt="<s:property value="nickName" />">
        </a>
    </dt>
    <dd>
        <a href="<%=request.getContextPath()%>/i/<s:property value="id" />/" title="<s:property value="nickName" />" target="_blank"><s:property value="nickName" /></a> </dd>
</dl> 
    </s:iterator>
    <p:pages pageSize="${pageSize }" pageNo="${pageNo }" recordCount="${recordCount}"></p:pages>
    </div>
</div>
	</div>
	 <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/group.js"></script>
	</div>
</body>
</html>