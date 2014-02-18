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
<div class="grow gmt60 blog-page">
 <%@ include file="/WEB-INF/jsp/user/space/common/leftSize.jsp"%>
            <div class="main gprefix-1 gspan-25">
            <div class="gbtitle">
        <h2><a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/" class="gbtitle-link">个人主页</a><span class="entities">&gt;</span>日志<span class="gbtitle-more">（全部<s:property value="recordCount" />个）</span></h2>
        <s:if test="spaceUser.id==curUser.id">
        <a href="<%=request.getContextPath()%>/blog/post/" class="gbtn-primary">写日志</a>
        </s:if>
    </div>
    <ul id="categoryCon" class="category gclear">
       <s:if test="categoryId==-1">
       <li class="t_itm current">
       <span class="t_lk">全部</span>
       </li>
       </s:if>
       <s:else>
        <li class="t_itm">
       <a class="t_lk" href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/blog/">全部</a>
       </li>
       </s:else>
       <s:if test="categoryId==0">
       <li class="t_itm current">
       <span class="t_lk">未分类</span>
       </li>
       </s:if>
       <s:else>
        <li class="t_itm">
       <a class="t_lk" href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/blog/?categoryId=0">未分类</a>
       </li>
       </s:else>
       <s:iterator value="myBlogCategories">
       <s:if test="categoryId==id">
       <li class="t_itm current">
       <span class="t_lk"><s:property value="name" />
       <s:if test="spaceUser.id==curUser.id">
       <a href="javascript:void 0" data-operation="updateCategory" data-params="id=<s:property value="id" />&txt=<s:property value="name" />">！</a>
       </s:if>
       </span>
       </li>
       </s:if>
       <s:else>
        <li class="t_itm">
       <a class="t_lk" href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/blog/?categoryId=<s:property value="id" />"><s:property value="name" /></a>
       <s:if test="spaceUser.id==curUser.id">
       <a href="javascript:void 0" data-operation="updateCategory" data-params="id=<s:property value="id" />&txt=<s:property value="name" />">！</a>
       </s:if>
       </li>
       </s:else>
       </s:iterator>
    </ul>
    <ul class="blog_list">
    <s:iterator value="myBlogs">
    <li class="blog">
            <h2><a href="<%=request.getContextPath()%>/blog/<s:property value="id" />/" target="_blank"><s:property value="title" /></a></h2>
            <p class="blog-meta"><s:property value="postedDateF" /></p>
            <p><s:property value="contentNoHtml100" escape="false"/><a href="<%=request.getContextPath()%>/blog/<s:property value="id" />" target="_blank">查看全文</a></p>
        </li>
        </s:iterator>
    </ul>
    <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${recordCount}"></p:pages>
            </div>
           
</div>
	    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/space.js"></script>
			<s:if test="spaceUser.id==curUser.id">
   <script type="text/javascript">
   $(function(){
	   popWin.initialCustom({title:"修改分类",content:"<div style='line-height:30px;'>分类：<input type='text' class='gstxt' style='width:200px' /></div><div style='margin-top:10px;float:right;'><a href='javascript:void(0)'  class='gbtn-primary'>确定</a><div/>",width:"300"});
   });
  
   </script>
   </s:if>
</div>
</body>
</html>