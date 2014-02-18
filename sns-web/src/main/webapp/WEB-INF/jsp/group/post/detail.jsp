<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title>xx网站 - <s:property value="groupPost.title"/></title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/group1.css"
	rel="stylesheet" />
	<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/js/sh/sh.css"
	rel="stylesheet" />
</head>
<body>
    <div class="container">
    <div class="grow gmt60 group-post-content-page">
        <div class="main gspan-21">
           <div class="gbreadcrumb">
                <ul>
                    <li>
                    <a href="<%=request.getContextPath()%>/group/all">小组</a>
                    </li>
                    <li>
                    <a href="<%=request.getContextPath()%>/group/<s:property value="groupPost.group.id"/>/"><s:property value="groupPost.group.name"/></a>
                    </li>
                    <li>
                     <s:property value="groupPost.title"/>
                    </li>
                </ul>
            </div>
            <div class="post">
      
        <h1 id="articleTitle"><s:property value="groupPost.title"/></h1>
        <div class="post-pic">
            <a target="_blank" title=" <s:property value="groupPost.postedByUser.nickName"/>" href="<%=request.getContextPath()%>/i/<s:property value="groupPost.postedByUser.id"/>/"><img width="48" height="48" src="<%=request.getContextPath()%><s:property value="groupPost.postedByUser.avatar48" />" id="articleAuthorImg"></a>
        </div>
        <div class="gpack post-txt">
            <div class="post-info">
                <p class="gfl">
                <a target="_blank" title="<s:property value="groupPost.postedByUser.nickName"/>" href="<%=request.getContextPath()%>/i/<s:property value="groupPost.postedByUser.id"/>/" id="articleAuthor" ><s:property value="groupPost.postedByUser.nickName"/></a>
                
                </p>
                <p class="gfr"><s:property value="groupPost.postedDateF"/></p>
            </div>
            <div class="post-detail" id="articleContent" >
              <s:property value="groupPost.contentWithAtLink" escape="false"/>
            </div>
        </div>
        <div id="controlPanel" class="post-do">
        <p class="gfr">
        <s:if test="groupPost.group.createdByUserId==curUser.id">
            <s:if test="groupPost.isTop==1">
            <a class="doLink" data-operation="doTop" data-params="id=<s:property value="groupPost.group.id" />&postId=<s:property value="groupPost.id" />&isCancel=1" href="javascript:void(0);">取消置顶</a> | 
            </s:if>
              <s:else>
            <a class="doLink" data-operation="doTop" data-params="id=<s:property value="groupPost.group.id" />&postId=<s:property value="groupPost.id" />&isCancel=0" href="javascript:void(0);">置顶</a> | 
            </s:else>
            <s:if test="groupPost.isBest==1">
            <a class="doLink" data-operation="doBest" data-params="id=<s:property value="groupPost.group.id" />&postId=<s:property value="groupPost.id" />&isCancel=1" href="javascript:void(0);">取消加精</a> | 
            </s:if>
              <s:else>
            <a class="doLink" data-operation="doBest" data-params="id=<s:property value="groupPost.group.id" />&postId=<s:property value="groupPost.id" />&isCancel=0" href="javascript:void(0);">加精</a> | 
            </s:else>
        </s:if>
         <s:if test="groupPost.postedByUserId==curUser.id">
						<a class="doLink" href="<%=request.getContextPath()%>/post/<s:property value="groupPost.id" />/edit">编辑</a>
					    </s:if>
        </p>
        </div>
                <p id="tags" class="post-tags tags">
        标签:
        <s:iterator value="groupPost.tags" status='st'>
            
            <span class="tag"><a href="<%=request.getContextPath()%>/tag/<s:property value="cnSpell"/>"><s:property value="name"/></a></span>
            <s:if test="#st.Last!=true">
            <span class="split">|</span>
            </s:if>
        </s:iterator>
        </p>
    </div>
     <div class="document-do">
                <div class="gfl">
						<span><s:property value="groupPost.viewCount" />人已浏览</span>
					</div>
                <div class="gfr">
                       <a href="javascript:void(0)" class="gbtn" data-operation="doRecommend" data-params="id=<s:property value="groupPost.id" />&count=<s:property 
						value="groupPost.recommendCount" />"> 推荐&nbsp;&nbsp;<s:property value="groupPost.recommendCount" /></a><a data-operation="jumpToComment" href="javascript:void(0)" class="gbtn-primary">发表评论</a>
                    </div>
                </div>
<div id="comments" class="cmts">
   <div class="cmts-title">
   <s:if test="groupPost.commentCount>0">
                <div class="gfl"><s:property value="groupPost.commentCount"/>条评论</div>
				</s:if>
				<s:else>
				<div class="gfl">目前还没有人评论</div>
				</s:else>
                <p class="gfr">
<p:littlePages anchor="comments" pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${recordCount}"></p:littlePages>
                </p>
   </div>
   <ul class="cmts-list">
                
                
              <s:iterator value="comments" var="comment" status="st">  
                <li id="comment<s:property value="#comment.id"/>">
                    <div class="cmt-img cmtImg pt-pic">
                        <a target="_blank" title="<s:property value="#comment.postedByUser.nickName"/>" href="<%=request.getContextPath()%>/i/<s:property value="#comment.postedByUserId"/>/">
                            <img height="48" width="48" src="<%=request.getContextPath()%><s:property value="#comment.postedByUser.avatar48"/>">
                        </a>
                        <span class="cmt-floor"><s:property value="pageSize*(pageNo-1)+#st.count"/>楼</span>
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
            <p:pages anchor="comments" pageSize="${pageSize }" pageNo="${pageNo }" recordCount="${recordCount }"></p:pages>
			<div id="commentsReplyer" class="cmts-do">
			<h3 id="replyer">你的评论</h3>
			 <s:if test="curUser==null">
						<p>
							请 <a href="javascript:popWin.loginShow();">登录</a> 后发表评论
						</p>
						</s:if>
			<s:else>
			 <form action="<%=request.getContextPath()%>/group/post/<s:property value="groupPost.id"/>/newComment" method="POST" id="replyForm">
                         <textarea  name="groupPostComment.content" id="editor"></textarea>
                         <input type="submit" data-operation="addGroupPostComment" value="发布" class="gbtn-primary">
                        </form>
			</s:else>
			</div>
</div>
        </div>
        <div class="side gspan-10 gprefix-1">
        
            <div class="side-title">
              <h2>本帖来自</h2>
            </div>
            <div class="gpack side-source">
            <a target="_blank" title="<s:property value="groupPost.group.name"/>" href="<%=request.getContextPath()%>/group/<s:property value="groupPost.group.id"/>/" class="pt-pic"><img width="48" height="48" hoverboxadded="true" alt="<s:property value="groupPost.group.name"/>" src="<%=request.getContextPath()%><s:property value="groupPost.group.icon"/>"></a>
            <div class="pt-txt">
                <h3><a target="_blank" href="<%=request.getContextPath()%>/group/<s:property value="groupPost.group.id"/>/"><s:property value="groupPost.group.name"/></a></h3>
                <p class="pt-txt-d"><s:property value="groupPost.group.joinedUserCount"/>人加入</p>
            </div>
        </div>
        <div class="side-title">
                <h2>小组热帖推荐</h2>
        </div>
        <ul id="recommendHotPosts" class="side-hotest">
        <s:iterator value="recommendPosts"> 
             <li><a  class="post-title"  target="_blank" href="<%=request.getContextPath()%>/post/<s:property value="id"/>/"><s:property value="title"/></a>
             <p class="side-hotest-l">
作者：
<a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="postedByUser.id"/>/"><s:property value="postedByUser.nickName"/></a>
</p>
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
			src="<%=request.getContextPath()%>/staticFile/js/group.js"></script>
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
                 							'insertunorderedlist', '|', 'emoticons', 'image', 'link','code']
											});

						});
			</script>
		</s:if>
</body>
</html>