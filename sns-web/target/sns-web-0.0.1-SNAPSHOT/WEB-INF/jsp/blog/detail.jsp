<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title><s:property value="blog.title" /> - xx日志 - xx网</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/blog1.css"
	rel="stylesheet" />
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/js/sh/sh.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
		<div class="grow gmt60  blog-content-page">
<div class="main gspan-21 gsuffix-1">
<div class="gbreadcrumb">
<ul>
					<li><a href="/blog/">日志</a></li>
					<li><a
						href="/i/<s:property value="blog.postedByUserId"/>/blog/"><s:property
								value="blog.postedByUser.nickName" />的日志</a></li>
					<li><s:property value="blog.title" /></li>
</ul>
</div>
<div class="post">
<h1 id="articleTitle"><s:property value="blog.title" /></h1>
<div class="gpack post-txt">
                <div class="post-info">
                    <p class="gfl">
                    <a target="_blank" title="<s:property value="blog.postedByUser.nickName" />" href="/i/<s:property value="blog.postedByUser.id" />/" id="articleAuthor"><s:property
								value="blog.postedByUser.nickName" /></a>
                    </p>
                    <p class="gfl"><s:property
								value="blog.postedDateF"  /></p>
								<s:if test="blog.postedByUserId==curUser.id">
						<p class="gfr"><a class="doLink" href="/blog/<s:property value="blog.id" />/edit">编辑</a></p>
					    </s:if>
                </div>
                <div class="post-detail" id="articleContent">
                     <s:property value="blog.contentWithAtLink" escape="false" />
                </div>
</div>
        <p id="tags" class="post-tags tags">
        标签:
        <s:iterator value="blog.tags" status='st'>
            
            <span class="tag"><a href="/tag/<s:property value="cnSpell"/>"><s:property value="name"/></a></span>
            <s:if test="#st.Last!=true">
            <span class="split">|</span>
            </s:if>
        </s:iterator>
        </p>
</div>
<div class="document-do">
					<div class="gfl">
						<span><s:property value="blog.viewCount" />人已浏览</span>
					</div>
					<div class="gfr">
						<a href="javascript:void(0)" class="gbtn" data-operation="doRecommend" data-params="id=<s:property value="blog.id" />&count=<s:property 
						value="blog.recommendCount" />"> 推荐&nbsp;&nbsp;<s:property value="blog.recommendCount" /></a><a data-operation="jumpToComment" href="javascript:void(0)" class="gbtn-primary">发表评论</a>
						</div>
</div>
<div id="comments" class="cmts">
   <div class="cmts-title">
   <s:if test="blog.commentCount>0">
                <div class="gfl"><s:property value="blog.commentCount"/>条评论</div>
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
                        <a target="_blank" title="<s:property value="#comment.postedByUser.nickName"/>" href="/i/<s:property value="#comment.postedByUserId"/>/">
                            <img height="48" width="48" src="<s:property value="#comment.postedByUser.avatar48"/>">
                        </a>
                        <span class="cmt-floor"><s:property value="#st.count"/>楼</span>
                    </div>
                    <div class="pt-txt">
                        <span class="cmt-info"><s:property value="#comment.postedDateF" /></span>
                        <a target="_blank" href="/i/<s:property value="#comment.postedByUserId"/>/" class="cmt-author cmtAuthor"><s:property value="#comment.postedByUser.nickName"/></a>
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
			 <form action="/blog/<s:property value="blog.id"/>/newComment" method="POST" id="replyForm">
                         <textarea  name="blogComment.content" id="editor"></textarea>
                         <input type="submit" data-operation="addBlogComment" value="发布" class="gbtn-primary">
                        </form>
			</s:else>
			</div>
</div>
</div>
<div class="side gspan-10">
        <div class="side-links">
            <p><a href="/i/<s:property value="blog.postedByUserId"/>">返回<s:property value="blog.postedByUser.nickName"/>的主页 &gt;</a></p>
        </div>
        <div class="side-title">
            <h2>作者其他日志</h2>
            <a href="/i/<s:property value="blog.postedByUserId"/>/blog/">[全部]</a>
        </div>
        <ul class="side-hotest">
        <s:iterator value="blogs">
        <li>
        <a href="/blog/<s:property value="id" />/"><s:property value="title" /></a>
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
			src="<%=request.getContextPath()%>/staticFile/js/blog.js"></script>
		<s:if test="curUser!=null">
			<script type="text/javascript">
		var kEditor;
                        	KindEditor.ready(function(K) {
                        		kEditor=K.create('#editor', {
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