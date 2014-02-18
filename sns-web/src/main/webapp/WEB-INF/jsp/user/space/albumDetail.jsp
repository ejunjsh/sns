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
	<link type="text/css"
	href="/<%=request.getContextPath()%>staticFile/css/multipleImageUploader.css"
	rel="stylesheet" />
				<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/photoUtils.js"></script>
</head>
<body>
	<div class="container">
<div class="grow gmt60 album-page">
 <%@ include file="/WEB-INF/jsp/user/space/common/leftSize.jsp"%>
            <div class="main gprefix-1 gspan-25">
            <div class="gbtitle">
        <h2><a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/" class="gbtitle-link">个人主页</a><span class="entities">&gt;</span><a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/album" class="gbtitle-link">相册</a><span class="entities">&gt;</span>《<s:property value="album.title" />》<span class="gbtitle-more">（图片<s:property value="recordCount" />个）</span></h2>
            <s:if test="spaceUser.id==curUser.id">
        <a style="right:180px" href="javascript:void(0)" data-operation="addPhoto" data-params="albumId=<s:property value="album.id" />" class="gbtn-primary"><b>+</b>图片</a>
         <a href="javascript:void(0)" data-operation="addPhotos" data-params="albumId=<s:property value="album.id" />" class="gbtn-primary"><b>+</b>多图片</a>
        </s:if>
    </div>
    <ul class="photo_list gclear">
    <s:iterator value="myPhotos">
<li>
<div class="c">
<a href="<%=request.getContextPath()%>/photo/<s:property value="id"/>"><img onload="bindMovePhoto(this);" onerror="lib.errorImg(this)" src="<%=request.getContextPath()%><s:property value="thumbnail"/>" alt="<s:property value="title"/>"></a></div>
<p class="ptn"><s:property value="title"/></p>
<p class="ptn"><s:property value="createdDateF"/></p>
</li>
        </s:iterator>
    </ul>
    <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${recordCount}"></p:pages>
            </div>
           
</div>
	    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="/staticFile/js/space.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/iframeUploader.js"></script>
			<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/popWinUploader.js"></script>
			<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/multipleImageUploader.js"></script>
			<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/resourceUtils.js"></script>
									<s:if test="spaceUser.id==curUser.id">
   <script type="text/javascript">
   $(function(){
	   popWin.initialCustom({id:"addPhotoWin",title:"添加图片",content:"<div style='line-height:30px;overflow-y: scroll;height:200px;'>图片：<img width='300px' src=''/></div>"
	   +"<div style='line-height:30px;'>标题：<input type='text' class='gstxt' style='width:300px' /></div>"
	   +"<div style='line-height:30px;'>描述：<textarea class='gstxt' style='width:300px;height:100px;' /></div>"
	   +"<div style='margin-top:10px;float:right;'><a href='javascript:void(0)'  class='gbtn-primary'>确定</a><div/>",width:"400"});
   });
   </script>
   </s:if>
</div>
</body>
</html>