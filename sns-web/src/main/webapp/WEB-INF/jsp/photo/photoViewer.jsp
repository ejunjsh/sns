<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title><s:property value="photo.title" /> - xx日志 - xx网</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/photo.css"
	rel="stylesheet" />
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/js/sh/sh.css"
	rel="stylesheet" />
<script type="text/javascript">
							function imageRotate(imgid, direct) {
								var image = $("#"+imgid)[0];
								if(!image.getAttribute('deg')) {
									var deg = 0;
									image.setAttribute('ow', image.width);
									image.setAttribute('oh', image.height);
									if($.browser.msie) {
										image.setAttribute('om', parseInt(image.currentStyle.marginBottom));
									}
								} else {
									var deg = parseInt(image.getAttribute('deg'));
								}
								var ow = image.getAttribute('ow');
								var oh = image.getAttribute('oh');
								deg = direct == 1 ? deg - 90 : deg + 90;
								if(deg > 270) {
									deg = 0;
								} else if(deg < 0) {
									deg = 270;
								}
								image.setAttribute('deg', deg);
								if($.browser.msie) {
									if(!isNaN(image.getAttribute('om'))) {
										image.style.marginBottom = (image.getAttribute('om') + (BROWSER.ie < 8 ? 0 : (deg == 90 || deg == 270 ? Math.abs(ow - oh) : 0))) + 'px';
									}
									image.style.filter = 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (deg / 90) + ')';
								} else {
								        switch(deg) {
										case 90:var cow = oh, coh = ow, cx = 0, cy = -oh;break;
										case 180:var cow = ow, coh = oh, cx = -ow, cy = -oh;break;
										case 270:var cow = oh, coh = ow, cx = -ow, cy = 0;break;
								        }
									var canvas = document.getElementById(image.getAttribute('canvasid'));
									if(!canvas) {
										var i = document.createElement("canvas");
										i.id = 'canva_' + Math.random();
										image.setAttribute('canvasid', i.id);
										image.parentNode.insertBefore(i, image);
										canvas = i;
									}
									if(deg) {
										var canvasContext = canvas.getContext('2d');
										canvas.setAttribute('width', cow);
										canvas.setAttribute('height', coh);
										canvasContext.rotate(deg * Math.PI / 180);
										canvasContext.drawImage(image, cx, cy, ow, oh);
										image.style.display = 'none';
										canvas.style.display = '';
									} else {
										image.style.display = '';
										canvas.style.display = 'none';
									}
								}
							};
								function createElem(e) {
									var obj = document.createElement(e);
									obj.style.position = 'absolute';
									obj.style.zIndex = '20';
									obj.style.cursor = 'pointer';
									obj.onmouseout = function() {
										this.style.background = 'none';
									};
									return obj;
								};
								function viewPhoto() {
									var pager = createElem('div');
									var pre = createElem('div');
									var next = createElem('div');
									var cont = document
											.getElementById('photo_pic');
									var tar = document.getElementById('pic');
									var space = 0;
									var w = tar.width / 2;
									if (!!window.ActiveXObject
											&& !window.XMLHttpRequest) {
										space = -(cont.offsetWidth - tar.width) / 2;
									}
									pager.style.position = 'absolute';
									pager.style.top = '0';
									pager.style.left = $(tar).position().left
											+ 'px';
									pager.style.top = $(tar).position().top
											+ 'px';
									pager.style.width = tar.width + 'px';
									pager.style.height = tar.height + 'px';
									pre.style.left = 0;
									next.style.right = 0;
									pre.style.width = next.style.width = w
											+ 'px';
									pre.style.height = next.style.height = tar.height
											+ 'px';
									pre.innerHTML = next.innerHTML = '<img src="<%=request.getContextPath()%>/staticFile/img/emp.gif" width="' + w + '" height="' + tar.height+'" />';

									pre.onmouseover = function() {
										this.style.background = 'url(<%=request.getContextPath()%>/staticFile/img/pic-prev.png) no-repeat 0 100px';
									};
									pre.onclick = function() {
										
										window.location = '<%=request.getContextPath()%>/photo/<s:property value="previousPhoto.id" />';
									   
									};

									next.onmouseover = function() {
										this.style.background = 'url(<%=request.getContextPath()%>/staticFile/img/pic-next.png) no-repeat 100% 100px';
									};
									next.onclick = function() {
										
										window.location = '<%=request.getContextPath()%>/photo/<s:property value="nextPhoto.id" />';
									};

									//cont.style.position = 'relative';
									cont.appendChild(pager);
									pager.appendChild(pre);
									pager.appendChild(next);
								};

							</script>
</head>
<body>
	<div class="container">
		<div class="grow gmt60  photo-content-page">
<div class="main gspan-21 gsuffix-1">
<div class="gbreadcrumb">
<ul>
					<li><a href="<%=request.getContextPath()%>/photo/">图片</a></li>
					<li><a
						href="<%=request.getContextPath()%>/i/<s:property value="photo.userId"/>/album/"><s:property
								value="photo.user.nickName" />的相册</a></li>
					<li><s:property value="photo.title" /></li>
</ul>
</div>
<div class="bm_c">

					<div class="tbmu" id="pic_block">
						<div class="y">
							<a href="javascript:;" onclick="imageRotate('pic', 1)"><img
								class="vm" src="<%=request.getContextPath()%>/staticFile/img/rleft.gif" /></a> <a
								href="javascript:;" onclick="imageRotate('pic', 2)"><img
								class="vm" src="<%=request.getContextPath()%>/staticFile/img/rright.gif" /></a><span
								class="pipe">|</span> 
								
								<a href="<%=request.getContextPath()%>/photo/<s:property value="previousPhoto.id" />">上一张</a>
								
								
								<span class="pipe">|</span> 
								
								<a href="<%=request.getContextPath()%>/photo/<s:property value="nextPhoto.id" />" id="nextlink">下一张</a>
								
								
								<span class="pipe">|</span> <a
								href="<%=request.getContextPath()%><s:property value="photo.original" />"
								target="_blank">查看原图</a> <span id="displayNum"></span>
						</div>
						<a
							href="<%=request.getContextPath()%>/i/<s:property value="photo.userId" />/album/<s:property value="photo.albumId" />">&laquo;
							返回图片列表</a> <span class="pipe">|</span>当前第 <s:property value="curPhotoIndex" /> 张<span class="pipe">|</span>共
						<s:property value="albumSize" /> 张图片&nbsp;
					</div>

					<div class="vw pic">

						<div id="photo_pic" class="c">
							
							<a href=""><img onload="viewPhoto();" src="<%=request.getContextPath()%><s:property value="photo.normal"/>"
								id="pic" alt="" /></a>
						
						</div>

						<div class="pns mlnv vm mtm mbm cl">
							<a
								href="<%=request.getContextPath()%>/photo/<s:property value="previousPhoto.id" />"
								class="btn" title="上一张"><img
								src="<%=request.getContextPath()%>/staticFile/img/pic_nv_prev.gif" alt="上一张" /></a>
						    <s:iterator value="albumPhotos">
						    <s:if test="id==photo.id">
						    <a
								href="<%=request.getContextPath()%>/photo/<s:property value="id" />"
								 title="<s:property value="title" />"><img class="a"
								src="<%=request.getContextPath()%><s:property value="thumbnail" />" alt="<s:property value="title" />" /></a>
						    </s:if>
						    <s:else>
						     <a
								href="<%=request.getContextPath()%>/photo/<s:property value="id" />"
								 title="<s:property value="title" />"><img
								src="<%=request.getContextPath()%><s:property value="thumbnail" />" alt="<s:property value="title" />" /></a>
						    </s:else>
						    </s:iterator>
							<a	href="<%=request.getContextPath()%>/photo/<s:property value="nextPhoto.id" />"
								class="btn" title="下一张"><img
								src="<%=request.getContextPath()%>/staticFile/img/pic_nv_next.gif" alt="下一张" /></a>
						</div>

					</div>
				</div>
<div class="document-do">
					<div class="gfl">
						<span><s:property value="photo.viewCount" />人已浏览</span>
					</div>
					<div class="gfr">
						<a href="javascript:void(0)" class="gbtn" data-operation="doRecommend" data-params="id=<s:property value="photo.id" />&count=<s:property 
						value="photo.recommendCount" />"> 推荐&nbsp;&nbsp;<s:property value="photo.recommendCount" /></a><a data-operation="jumpToComment" href="javascript:void(0)" class="gbtn-primary">发表评论</a>
						</div>
</div>
<div id="comments" class="cmts">
   <div class="cmts-title">
   <s:if test="photo.commentCount>0">
                <div class="gfl"><s:property value="photo.commentCount"/>条评论</div>
				</s:if>
				<s:else>
				<div class="gfl">目前还没有人评论</div>
				</s:else>
                <p class="gfr">
                    
                    <span class="cmts-title-page">
                        
                        
                    </span>
                </p>
   </div>
   <ul class="cmts-list">
                
                
              <s:iterator value="comments" var="comment" status="st">  
                <li id="comment<s:property value="#comment.id"/>">
                    <div class="cmt-img cmtImg pt-pic">
                        <a target="_blank" title="<s:property value="#comment.postedByUser.nickName"/>" href="<%=request.getContextPath()%>/i/<s:property value="#comment.postedByUserId"/>/">
                            <img height="48" width="48" src="<%=request.getContextPath()%><s:property value="#comment.postedByUser.avatar48"/>">
                        </a>
                        <span class="cmt-floor"><s:property value="#st.count"/>楼</span>
                    </div>
                    <div class="pt-txt">
                        <span class="cmt-info"><s:property value="#comment.postedDateF" /></span>
                        <a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="#comment.postedByUserId"/>/" class="cmt-author cmtAuthor"><s:property value="#comment.postedByUser.nickName"/></a>
                        <div class="cmt-content cmtContent"><s:property value="#comment.contentWithAtLink" escape="false" /></div>
                        <span class="cmt-do">
                            
                            <a  href="javascript:void 0;" class="cmt-do-quote" data-operation="quoteComment" data-params="id=<s:property value="#comment.id"/>">引用</a>
                            
                            <s:if test="curUser.id==#comment.postedByUserId">
                            &nbsp;&nbsp;|&nbsp;&nbsp;<a  href="javascript:void 0;" class="cmt-do-delete" data-operation="deleteComment" data-params="id=<s:property value="#comment.id"/>&confirm=是否真的删除这条回复？">删除</a>
                            </s:if>
                        </span>
                    </div>
                    </li>
                
                </s:iterator>
            </ul>
			<div id="commentsReplyer" class="cmts-do">
			<h3 id="replyer">你的评论</h3>
			 <s:if test="curUser==null">
						<p>
							请 <a href="javascript:popWin.loginShow();">登录</a> 后发表评论
						</p>
						</s:if>
			<s:else>
			 <form action="<%=request.getContextPath()%>/photo/<s:property value="photo.id"/>/newComment" method="POST" id="replyForm">
                         <textarea  name="photoComment.content" id="editor"></textarea>
                         <input type="submit" data-operation="addPhotoComment" value="发布" class="gbtn-primary">
                        </form>
			</s:else>
			</div>
</div>
</div>
<div class="side gspan-10">
        <div class="side-links">
            <p><a href="<%=request.getContextPath()%>/i/<s:property value="photo.userId"/>">返回<s:property value="photo.user.nickName"/>的主页 &gt;</a></p>
        </div>
        <div class="side-title">
            <h2>图片信息</h2>
            <div class="info"><s:property value="photo.description" /></div>
        </div>
        <ul class="side-hotest">
        <s:iterator value="photos">
        <li>
        <a href="<%=request.getContextPath()%>/photo/<s:property value="id" />/"><s:property value="title" /></a>
        </li>
        </s:iterator>    
        </ul>
</div>
</div>
		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		</div>

		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/kindeditor-min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/zh_CN.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/sh/sh.js"></script>
        		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/photo.js"></script>
		<s:if test="curUser!=null">
			<script type="text/javascript">
		var kEditor;
                        	KindEditor.ready(function(K) {
                        		kEditor=K.create('#editor', {
                        			uploadJson:'<%=request.getContextPath()%>/ajax/keImageUpload/upload',
                        			cssPath : ['<%=request.getContextPath()%>/staticFile/css/kind-code.css' ],
                        			items : [
                 							'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                 							'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                 							'insertunorderedlist', '|', 'emoticons', 'image', 'link','code']		});

						});
			</script>
		</s:if>
</body>
</html>