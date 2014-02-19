<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>个人资料|设置-- XX网</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css" href="<%=request.getContextPath()%>/staticFile/css/settings.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">

		<div class="grow gmt60 gpack settings-profile-page">
			<div class="side gspan-6">
				<h1>设置</h1>
				<ul>
					<li class="gactived"><span class="gicon-profile"></span><span>个人资料</span></li>
					<li><a class="gicon-avatar"
						href="<%=request.getContextPath()%>/settings/avatar/"></a><a
						href="<%=request.getContextPath()%>/settings/avatar/">设置头像</a></li>
				</ul>
			</div>
			<div class="gspan-24 gprefix-1">
				<div class="gbtitle">
					<h2>个人资料</h2>
				</div>
				<form id="profile" action="" method="POST"
					class="gprefix-1 gsuffix-2 gform">
					<label for="nickname">昵称</label>
					<div class="gform-box gclear">
						<input type="text" value="<s:property value="modifiedUser.nickName" />" name="modifiedUser.nickName"
							id="nickname" class="gstxt" />
							<s:if test="errorIndex==1">
							<span class="tip">昵称是已经有人用了哦</span>
							</s:if>
							<s:if test="errorIndex==2">
							<span class="tip">昵称不能为空</span>
							</s:if>
					</div>
					<label for="title">专业头衔</label>
					<div class="gform-box gclear">
						<input type="text" value="<s:property value="modifiedUser.title" />" name="modifiedUser.title" id="title" class="gstxt" />
					    <s:if test="errorIndex==3">
							<span class="tip">头衔不能超过一百个字哦</span>
							</s:if>
					</div>
					<label for="gender">性别</label>
					<div class="gform-box gclear">
					<s:radio name="modifiedUser.gender" list="%{#{'1':'男','2':'女','0':'保密'}}" ></s:radio>
					</div>
					<label for="blogUrl">博客地址</label>
					<div class="gform-box gclear">
						<input type="text" value="<s:property value="modifiedUser.blogUrl" />"
							name="modifiedUser.blogUrl" id="blogUrl" class="gstxt" />
					    <s:if test="errorIndex==4">
							<span class="tip">博客地址不能超过200个字哦</span>
							</s:if>
					</div>
					<label for="introduction" >个人简介</label>
					<div id="selfIntro" class="gform-box">
						<textarea data-operation="countChar" data-event="keyup" data-params="relatedCtrl=wordCount&limit=650" rows="10" name="modifiedUser.description" id="introduction"
							cols="40" class="gttxt"><s:property value="modifiedUser.description" /></textarea>
						<div class="gform-box-count-tip success">&nbsp;</div>
						<div id="wordCount" class="gform-box-count">
						</div>
						<s:if test="errorIndex==5">
							<span class="tip">个人简介不能超过650个字哦</span>
							</s:if>
					</div>
					<div class="gform-box  gclear">
						<div class="gform-box-right">
						<s:if test="modifiedUser.isWaterMark>0" >
							<input type="checkbox" checked value="1" name="modifiedUser.isWaterMark"
								id="is_watermark_enabled" />
							</s:if>	 
							<s:else>
						<input type="checkbox" value="1" name="modifiedUser.isWaterMark"
								id="is_watermark_enabled" />
							</s:else>
								 <label for="is_watermark_enabled">启用图片水印</label>
								
						</div>
					</div>
					<div class="gform-box gclear">
						<input type="submit" value="保存" class="gbtn-primary gform-submit" />
					</div>
				</form>
			</div>

		</div>
		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath()%>/staticFile/js/settings.js"></script>
	</div>
</body>
</html>