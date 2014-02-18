<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title>xx网站 - <s:property value="article.title"/></title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/site.css"
	rel="stylesheet" />
	<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/js/sh/sh.css"
	rel="stylesheet" />
</head>
<body>
    <div class="container">
    <div class="grow gmt60 article-page">
        <div class="main gspan-21">
           <div class="gbreadcrumb">
                <ul>
                    <li>
                    <a href="<%=request.getContextPath()%>/site/all">主题站</a>
                    </li>
                    <li>
                    <a href="<%=request.getContextPath()%>/site/<s:property value="article.articleTopic.id"/>/"><s:property value="article.articleTopic.name"/></a>
                    </li>
                    <li>
                     <s:property value="article.title"/>
                    </li>
                </ul>
            </div>
           <div class="content">
           <div class="content-th">
              <h1 id="articleTitle"><s:property value="article.title"/></h1>
				<div class="content-th-info">
				<a href="<%=request.getContextPath()%>/i/<s:property value="article.postedByUserId"/>/" title="<s:property value="article.postedByUser.nickName"/>"><s:property value="article.postedByUser.nickName"/></a>
                 <span><s:property value="article.postedDateF"/></span>
				</div>
           </div>
           <div id="articleContent" class="content-txt">
			<div class="document">
			<div>
			   <s:property value="article.contentWithAtLink" escape="false"/>
			</div>
			</div>
           </div>
                   <p id="tags" class="post-tags tags">
        标签:
        <s:iterator value="article.tags" status='st'>
            
            <span class="tag"><a href="/tag/<s:property value="cnSpell"/>"><s:property value="name"/></a></span>
            <s:if test="#st.Last!=true">
            <span class="split">|</span>
            </s:if>
        </s:iterator>
        </p>
        <ul class="content-titles gclear">
                    <s:if test="preArticle!=null&&preArticle.id>0">
                    <li class="gfl gellipsis">上一篇：<a href="<%=request.getContextPath()%>/article/<s:property value="preArticle.id" />/"><s:property value="preArticle.title" /></a></li>
                    </s:if>
                    <s:if test="nextArticle!=null&&nextArticle.id>0">
                    <li class="gfr gellipsis">下一篇：<a href="<%=request.getContextPath()%>/article/<s:property value="nextArticle.id" />/"><s:property value="nextArticle.title" /></a></li>
                    </s:if>
                </ul>
                <div class="recommend-articles">
                    <h2>相关阅读</h2>
                    <ul id="recommendArticleRelated" class="recommend-articles-list gclear">
                    <s:iterator value="relatedArticles" var="relatedArticle" status="st"> 
                    <s:if test="#st.count<=4">
                    <li>
                    <a target="_blank" href="<%=request.getContextPath()%>/article/<s:property value="#relatedArticle.id" />/"><img height="102" width="132" title="<s:property value="#relatedArticle.title" />" alt="<s:property value="#relatedArticle.title" />" src="<s:property value="#relatedArticle.cover160" />"></a>
                        <a target="_blank" href="<%=request.getContextPath()%>/<s:property value="#relatedArticle.id" />/"><s:property value="#relatedArticle.title" /></a>
                    </li>
                    </s:if>
                    </s:iterator>
                    </ul>
                </div>
                <div class="document-do gclear">
                <div class="gfl">
						<span><s:property value="article.viewCount" />人已浏览</span>
					</div>
                <div class="gfr">
                       <a href="javascript:void(0)" class="gbtn" data-operation="doRecommend" data-params="id=<s:property value="article.id" />&count=<s:property 
						value="article.recommendCount" />"> 推荐&nbsp;&nbsp;<s:property value="article.recommendCount" /></a><a data-operation="jumpToComment" href="javascript:void(0)" class="gbtn-primary">发表评论</a>
                    </div>
                </div>
           </div>
<div id="comments" class="cmts">
   <div class="cmts-title">
   <s:if test="article.commentCount>0">
                <div class="gfl"><s:property value="article.commentCount"/>条评论</div>
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
                            <img height="48" width="48" src="<s:property value="#comment.postedByUser.avatar48"/>">
                        </a>
                        <span class="cmt-floor"><s:property value="pageSize*(pageNo-1)+#st.count"/>楼</span>
                    </div>
                    <div class="pt-txt">
                        <span class="cmt-info"><s:property value="#comment.postedDateF"/></span>
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
			 <form action="<%=request.getContextPath()%>/article/<s:property value="article.id"/>/newComment" method="POST" id="replyForm">
                         <textarea  name="articleComment.content" id="editor"></textarea>
                         <input type="submit" data-operation="addArticleComment" value="发布" class="gbtn-primary">
                        </form>
			</s:else>
			</div>
</div>
        </div>
        <div class="side gspan-10 gprefix-1">
        <div class="side-title">
                <h2>同主题站文章</h2>
        </div>
        <ul id="recommendArticle" class="related_article">
        <s:iterator value="relatedArticles"> 
             <li><a target="_blank" href="<%=request.getContextPath()%>/article/<s:property value="id"/>/"><s:property value="title"/></a>作者：<span class="related_article-author"><s:property value="postedByUser.nickName"/></span></li>
        </s:iterator>
        </ul>
            <div class="side-title">
                <h2>作者信息</h2>
            </div>
            <ul class="side-author">
                <li>
                <a class="pt-pic" title="<s:property value="article.postedByUser.nickName" />" target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="article.postedByUserId" />/"><img height="48" width="48" src="<s:property value="article.postedByUser.avatar48" />" alt="<s:property value="article.postedByUser.nickName" />"></a>
                <div class="pt-txt">
                    <h3><a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="article.postedByUserId" />/"><s:property value="article.postedByUser.nickName" /></a></h3>
                    <span><s:property value="article.postedByUser.followedCount" />人关注</span>
                    <p class="pt-txt-d"><s:property value="article.postedByUser.title" /></p>
                </div>
                </li>
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
			src="<%=request.getContextPath()%>/staticFile/js/article.js"></script>
		<s:if test="curUser!=null">
			<script type="text/javascript">
		var kEditor;
                        	KindEditor.ready(function(K) {
                        		kEditor=K.create('#editor', {
                        			uploadJson:'<%=request.getContextPath()%>/ajax/keImageUpload/upload',
                        			cssPath : ['<%=request.getContextPath()%>/staticFile/css/kind-code.css' ]
                        		,items : [
             							'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
             							'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
             							'insertunorderedlist', '|', 'emoticons', 'image', 'link','code']		});

						});
			</script>
		</s:if>
</body>
</html>