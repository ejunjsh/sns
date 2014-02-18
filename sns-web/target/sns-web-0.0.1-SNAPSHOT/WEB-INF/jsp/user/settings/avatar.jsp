<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title> 设置头像|设置-- XX网</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css" href="/staticFile/css/settings.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
	
		<div class="grow gmt60 gpack settings-profile-page">
			<div class="side gspan-6">
				<h1>设置</h1>
				<ul>
					<li>
<a class="gicon-profile" href="/settings/profile/"></a>
<a href="/settings/profile/">个人资料</a>
</li>
<li class="gactived">
<span class="gicon-avatar"></span>
<span>设置头像</span>
</li>
				</ul>
			</div>
			<div class="main gspan-24 gprefix-1">
                <div class="gbtitle">
                    <h2>设置头像</h2>
                    </div>
                <div class="gprefix-2 gmt20">
                        <div class="form-div">
                            <p>
                                
                                <input type="file" id="trueFile" name="file">
                                <input type="submit" data-operation="uploadImg" value="上传" id="imgSubmit">
                                </p><p class="head-summary" id="uploadMsg">
                                    （支持gif、jpg、png图片格式，大小不要超过1M）
                                </p>
                            <p></p>
                        </div>

                    <div class="gmt30 gpack">
                        <div class="main-head">
                            <div class="main-head-holder">
                                <div style="height:160px;width:160px;" id="preview160">
                                    <img width="160px" height="160px" id="previewBig" src="<s:property value="modifiedUser.avatar160" />">
                                </div>
                            </div>
                            <p class="head-summary center">
                                大尺寸头像，160×160像素
                            </p>
                        </div>
                        <div class="other-head">
                            <div>
                                <div id="preview48">
                                    <img width="48" height="48" id="previewMiddle" src="<s:property value="modifiedUser.avatar48" />">
                                </div>
                                <p class="head-summary">
                                    中尺寸头像，48x48像素
                                </p>
                            </div>
                            <div class="gmt20">
                                <div id="preview24">
                                    <img width="24" height="24" id="previewTiny" src="<s:property value="modifiedUser.avatar24" />">
                                </div>
                                <p class="head-summary">
                                    小尺寸头像，24x24像素
                                </p>
                            </div>
                        </div>
                    </div>
                     <form  action="/settings/avatar" style="display:none" id="cutPanel" class="head-cut gmt30" action="" method="POST">
                        <div class="cut-btns gmt20">
                        <input id="hideField" type="hidden" value="<s:property value="tmpUrl" />" name="tmpUrl" >
                            <input type="submit" id="corpImg" value="保存" class="gbtn-primary">
                            <input type="button" onclick="location.href='/i/<s:property value="curUser.id" />'" id="canelCorp" value="取消" class="gbtn">
                        </div>
                    </form>
                </div>
            </div>
            </div>
		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript" src="/staticFile/js/settings.js"></script>
		<script type="text/javascript" src="/staticFile/js/iframeUploader.js"></script>
	</div>
</body>
</html>