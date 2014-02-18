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
<div class="grow gmt60 index-page">
 <%@ include file="/WEB-INF/jsp/user/space/common/leftSize.jsp"%>
            <div class="main gprefix-1 gspan-25">
           <div class="info gpack" id="info">
        <a id="infoBtn" class="gicon-arrow gfr" href="javascript: void 0;" style="display: none; ">查看完整简介</a>
        <p class="gtop-desc">
         <s:property value="spaceUser.title" />
        </p>
        <p class="info-intro">
        
        简介：<s:property value="spaceUser.description" />
        
        </p>
        <p class="gclear">
        <s:if test="spaceUser.gender==1">
        <span class="gicon-male"></span>
        </s:if>
        <s:elseif test="spaceUser.gender==2">
        <span class="gicon-female"></span>
        </s:elseif>
        <span class="info-blog">
            博客：<a target="blank" href="<s:property value="spaceUser.blogUrl" />"><s:property value="spaceUser.blogUrl" /></a>
        </span>
        
        </p>
       
    </div>
    <div class="gmt20 gclear">
       <div class="gbtitle">
            <h2>日志<a class="gbtitle-more" href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/blog/">[全部]</a></h2>
            
          
            
        </div>
         <ul class="blog_list">
    <s:iterator value="myBlogs">
    <li class="blog">
            <h2><a href="<%=request.getContextPath()%>/blog/<s:property value="id" />/" target="_blank"><s:property value="title" /></a></h2>
            <p class="blog-meta"><s:property value="postedDateF" /></p>
            <p><s:property value="contentNoHtml100" escape="false"/><a href="/blog/<s:property value="id" />" target="_blank">查看全文</a></p>
        </li>
        </s:iterator>
    </ul>
    </div>
    <div class="gmt20 gclear">
    <div class="gbtitle">
            <h2>动态<a class="gbtitle-more" href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/activity/">[全部]</a></h2>
        </div>
        <%@ include file="/WEB-INF/jsp/user/space/common/activities.jsp"%>
    </div>
           </div>
</div>
	    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/space.js"></script>
	    
</div>
</body>
</html>