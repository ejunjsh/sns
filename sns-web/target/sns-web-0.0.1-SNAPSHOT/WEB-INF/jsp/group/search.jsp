<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title>XX小组 - 搜索小组</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/group1.css"
	rel="stylesheet" />
</head>
<body>
<div class="container">
<div class="grow search-group-page">
<div class="side gspan-10 gmt40">
        <form method="get" class="gsearch" action="" id="groupSearch">
            <p>
            <input type="text"  placeholder="搜索小组" maxlength="10" name="key" class="gsearch-txt" id="word">
            <input type="submit" class="gsearch-bt gicon-search" value="搜索">
            </p>
        </form>
        <p><a href="<%=request.getContextPath()%>/group/all/">返回全部小组&nbsp;&gt;</a></p>
    </div>
    <div class="main gspan-21 gmt40">
    <h1 class="gbtitle">搜索小组</h1>
    <p class="search_result">“<s:property value="key" /> ”相关的小组</p>
    <ul class="group_list gpack">
      <s:iterator value="searchGroups">
      <li class="group">
         <div class="group-options">
                    <a href="<%=request.getContextPath()%>/group/<s:property value="id" />/"><img width="48" height="48" class="group-icon" src="<%=request.getContextPath()%><s:property value="cover48" />"></a>
                   <s:if test="isJoined==1">
                     <a class="quit-group joinBt" data-operation="doQuit" data-params="id=<s:property value="id" />&className=gbtn-join-gray joinBt&txt=加入"  href="javascript:void 0;" >退出</a>
                       </s:if>
                       <s:else>  
                        <a class="gbtn-join-gray joinBt" data-operation="doJoin" data-params="id=<s:property value="id" />&className=quit-group joinBt&txt=退出" href="javascript: void 0;">加入</a>
                       </s:else>
                </div>
                <div class="group-desc">
<p>
<a href="<%=request.getContextPath()%>/group/<s:property value="id" />/"><s:property value="name" /></a>
<span><s:property value="joinedUserCount" />人加入</span>
</p>
<p><s:property value="description30" /></p>
</div>
      </li>
      </s:iterator>
    </ul>
    </div>
    		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/iframeUploader.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/group.js"></script>
</div>
</div>
</body>
</html>