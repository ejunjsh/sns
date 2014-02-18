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
<div class="grow gmt60 tag-page">
 <%@ include file="/WEB-INF/jsp/user/space/common/leftSize.jsp"%>
            <div class="main gprefix-1 gspan-25">
            <div class="gbtitle">
        <h2><a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/" class="gbtitle-link">个人主页</a><span class="entities">&gt;</span>标签<span class="gbtitle-more">（全部<s:property value="spaceUser.tagCount" />个）</span></h2>
    </div>
 <ul class="tag-list gpack gclear">
    <s:iterator value="myTags">
<li>
            <div class="tag-list-options">
                <a target="_blank" href="<%=request.getContextPath()%>/tag/<s:property value="cnSpell" />/"><img src="<%=request.getContextPath()%><s:property value="cover48" />" onerror="lib.errorImg(this)" width="48" height="48"></a>
                <s:if test="isFollowed==0">
                 <a class="gbtn-join-gray" data-params="id=<s:property value="id" />&className=quit-tag&txt=取消关注"  data-operation="doFollowTag"  href="javascript: void 0;">关注</a>
         </s:if>
         <s:else>
         <a class="quit-tag" data-params="id=<s:property value="id" />&className=gbtn-join-gray&txt=关注"  data-operation="doUnfollowTag"  href="javascript: void 0;">取消关注</a>
         </s:else>
            </div>
            <div class="tag-list-desc">
                <p>
                    <a target="_blank" href="<%=request.getContextPath()%>/tag/<s:property value="cnSpell" />/"><s:property value="name"/></a>
                </p>
                <p>
                 <s:property value="followCount"/>关注
                </p>
                <p><s:property value="description50"/></p>
            </div>
        </li>
	 </s:iterator> 
 </ul>
    <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${spaceUser.tagCount}"></p:pages>
            </div>
           
</div>
	    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/space.js"></script>
	    
</div>
</body>
</html>