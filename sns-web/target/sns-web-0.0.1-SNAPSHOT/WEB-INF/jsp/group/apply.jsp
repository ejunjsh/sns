<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title>XX小组 - 申请创建小组</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/group1.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
		<div class="wrap grow gmt60 gpack group-apply-page">
			<div class="gspan-22 main">
				<div class="gbreadcrumb">
					<ul>
						<li><a href="/group">小组</a></li>
						<li>申请小组</li>
					</ul>
				</div>
				<form id="newGroup" class="gform" method="POST"
					action="/group/apply/">
					<div class="gprefix-1">
						<label for="name">小组名称</label>
						<div class="gform-box gclear">
							<input id="name" class="gbtxt" type="text" value="" required=""
								placeholder="给小组起个醒目的名称吧" name="group.name" maxlength="30"
								autofocus=""> <span class="tip">名称是必填的</span>
						</div>
						<label for="name">小组图标</label>
						<div class="gform-box gclear">
						<img id="groupIcon" height="48" width="48" src="/staticFile/img/defaultAvatar.gif" alt="小组图标" title="小组图标">
<br>
<input id="icon" type="hidden" value="" name="tmpUrl">
<input id="trueFile" type="file" name="file">
<input type="button" value="上传" data-operation="uploadImg" class="gbtn-primary">
<br>
<span id="uploadMsg">（支持png、jpg、gif图片格式，大小不要超过1M）</span>
<span class="tip">小组图标不能为空</span>
						</div>
						<label for="blackboard">小组介绍</label>
						<div class="gform-box gclear">
							<textarea rows="6" placeholder="介绍一下小组是干什么的，它将作为黑板报显示"
								name="group.description" id="blackboard" cols="48" class="gttxt"></textarea>
							<br> <span class="tip">小组介绍是必填的</span>
						</div>
						<label for="reason">申请理由</label>
						<div class="gform-box gclear">
							<textarea id="reason" class="gttxt" rows="6"
								placeholder="告诉我们你创建这个小组的想法吧" name="group.reason" cols="48"></textarea>
							<br> <span class="tip">理由是必填的</span>
						</div>
						<label for="contact">开发设置</label>
						<div class="gform-box gclear">
							<p>
								<input type="checkbox" value="1" name="group.isOpenContent"
									id="isOpenContent" checked=""> 内容开放给非小组成员（申请通过后，此项不可修改，需要联系网站管理员变更)



								<span class="tip"></span>

							</p>
							<p>
								<input type="checkbox" value="1" name="group.isNeedValidate"
									id="isNeedValidate">用户加入需要审核 <span class="tip"></span>

							</p>
						</div>
						<label>&nbsp;</label>
						<div class="gform-box gclear">

								<input type="checkbox" value="1" name="disclaimer" id="rule">我已阅读并愿意遵守<a target="_blank"
									href="/help/guideline/">《社区指导原则》</a><span
									class="tip">必须勾上</span>


						</div>

						<label>&nbsp;</label>
						<div class="gform-box gclear">
								<input type="submit" value="申请" class="gbtn-primary"> <a
									class="apply-canel" href="javascript: history.go(-1);">取消</a>
						</div>
					</div>
				</form>
			</div>
			<div class="gspan-10 side">
				<div class="side-tip">
					<h2>小组申请条件说明</h2>
					<p>管理员会在5个工作日内审核你的申请，审核结果将通过站内信通知你，请耐心等待。</p>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="/staticFile/js/iframeUploader.js"></script>
		<script type="text/javascript"
			src="/staticFile/js/group.js"></script>
	</div>
</body>
</html>

