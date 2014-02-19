<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title> 标签 | <s:property value="tag.name" />- XX网</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/tag.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
	<div class="grow gmt40 ask-tagcontent-page">
	   <div class="main gspan-21">
	   <div class="gbreadcrumb">
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>">XX网</a>
                </li>
                <li>
                <a href="<%=request.getContextPath()%>/tag/all">标签</a>
                </li>
                <li>
                <s:property value="tag.name" />
                </li>
        </ul>
    </div>
    <div  class="main-tag" id="definition">
        <div class="main-tag-info">
            <div class="main-tag-avatar">
                <img src="<%=request.getContextPath()%><s:property value="tag.cover48" />" id="tagAvatar" onerror="lib.errorImg(this)"  width="48" height="48">
                
                <a href="javascript:void 0;" data-operation="uploadTagImg" data-params="id=<s:property value="tag.id" />" id="editPic">编辑图片</a>
                
            </div>
            <div class="main-tag-title">
                <h1 ><s:property value="tag.name" /></h1>
                <p id="followCounter"><s:property value="tag.followCount" />人关注</p>
                <s:if test="tag.isFollowed==1">
                <a href="javascript:void 0;" data-params="id=<s:property value="tag.id" />&className=gbtn-primary&txt=<b>+</b> 关注" class="quit-tag" data-operation="doUnfollow" id="followBt">取消关注</a>
                </s:if>
                <s:else>
                <a href="javascript:void 0;" data-params="id=<s:property value="tag.id" />&className=quit-tag&txt=取消关注" class="gbtn-primary" data-operation="doFollow" id="followBt"><b>+</b> 关注</a>
                </s:else>
            </div>
        </div>
        <div class="main-tag-board" id="boardEditor">
            <div  id="tagDesc" class="main-board-editor"><s:property value="tag.description" escape="false" /></div>
        </div>
        <div class="main-tag-ft" id="tagFt">
            <a href="javascript:void(0);" data-params="id=<s:property value="tag.id" />" data-operation="editDesc" class="main-board-edit">修改描述</a>
        </div>
    </div>
    <ul class="gtabs">
        
    <s:if test="tabName==null||tabName==''" >
    <li class="gtabs-curr">相关问题</li>
    </s:if>
    <s:else>
     <li><a href="<%=request.getContextPath()%>/tag/<s:property value="tag.cnSpell" />/">相关问题</a></li>
    </s:else>
    <s:if test="tabName=='post'" >
    <li class="gtabs-curr">相关帖子</li>
    </s:if>
    <s:else>
     <li><a href="<%=request.getContextPath()%>/tag/<s:property value="tag.cnSpell" />/post/">相关帖子</a></li>
    </s:else>
    <s:if test="tabName=='article'" >
    <li class="gtabs-curr">相关文章</li>
    </s:if>
    <s:else>
     <li><a href="<%=request.getContextPath()%>/tag/<s:property value="tag.cnSpell" />/article/">相关文章</a></li>
    </s:else>
    <s:if test="tabName=='blog'" >
    <li class="gtabs-curr">相关日志</li>
    </s:if>
    <s:else>
     <li><a href="<%=request.getContextPath()%>/tag/<s:property value="tag.cnSpell" />/blog/">相关日志</a></li>
    </s:else>
     <s:if test="tabName=='user'" >
    <li class="gtabs-curr">相关用户</li>
    </s:if>
    <s:else>
     <li><a href="<%=request.getContextPath()%>/tag/<s:property value="tag.cnSpell" />/user/">相关用户</a></li>
    </s:else>
    </ul>
    <s:if test="tabName==null||tabName==''">
    <ul class="ask-list">
       <s:iterator value="questions">
          <li>
             <div class="ask-list-nums">
                <p class="ask-focus-nums">
                <span class="num"><s:property value="followCount"/></span>关注
                </p>
                <p class="ask-answer-nums">
                <span class="num"><s:property value="answerCount"/></span>回答
                </p>
                
            </div>
            <div class="ask-list-detials">
                <h2><a target="_blank" href="<%=request.getContextPath()%>/question/<s:property value="id"/>/"><s:property value="title"/></a></h2>
                <div class="ask-list-legend">
                    <p class="ask-list-tags">
                    标签：
                    
                      <s:iterator value="tags" status="st">  
                        <a href="<%=request.getContextPath()%>/tag/<s:property value="cnSpell"/>/" target="_blank"><s:property value="name"/></a>
                        <s:if test="!#st.last">
                        <span class="split">|</span>
                        </s:if>
                    </s:iterator>
                    </p>
                    <span class="ask-list-time">
                        <s:property value="postedDateF"/>
                    </span>
                </div>
            </div>
          </li>
       </s:iterator>
    </ul>
    </s:if>
    <s:if test="tabName=='post'">
<ul class="titles">
<s:iterator value="posts">
<li>
<h3 class="titles-txt"><a target="_blank" href="<%=request.getContextPath()%>/post/<s:property value="id" />/"><s:property value="title" />
                        </a></h3>
                        <div class="titles-r-grey"><s:property value="commentCount" /><span class="titles-comment-icon">&nbsp;</span></div>
<p class="titles-b">
<span class="titles-b-l">
来自：
<a target="_blank" href="<%=request.getContextPath()%>/group/<s:property value="group.id" />/"><s:property value="group.name" /></a>
小组
</span>
<span class="titles-b-c">|</span>
<span class="titles-b-l">
发表：
<a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="postedByUserId" />/"><s:property value="postedByUser.nickName" /></a>
</span>
<s:if test="lastCommentDateF==null">
<span class="titles-b-r"> 发表时间：<s:property value="postedDateF" /> </span>
</s:if>
<s:else>
<span class="titles-b-r"> 最后回应：<s:property value="lastCommentDateF" /> </span>
</s:else>
</p>
</li>
</s:iterator>
</ul>
    </s:if>
    <s:if test="tabName=='blog'">
    <ul class="blog_list">
    <s:iterator value="blogs">
    <li class="blog">
    <h2><a target="_blank" href="<%=request.getContextPath()%>/blog/<s:property value="id" />/"><s:property value="title"/></a></h2>
    <p class="blog-meta"><a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="postedByUserId" />/"><s:property value="postedByUser.nickName" /></a>发表于<span><s:property value="postedDateF" /></span></p>
    <p class="blog-num">评论&nbsp;<s:property value="commentCount" /><span class="blog-num-sp">|</span>推荐&nbsp;<s:property value="recommendCount" /></p>
    <p><s:property value="contentNoHtml100" /><a target="_blank" href="<%=request.getContextPath()%>/blog/<s:property value="id" />/">查看全文</a></p>
    </li>
    </s:iterator>
    </ul>
    </s:if>
    <s:if test="tabName=='article'">
    <div class="article_list">
    <s:iterator value="articles">
     <div class="article">
                    <a class="article-pic" href="<%=request.getContextPath()%>/article/<s:property value="id"/>/" target="_blank">
                        <img height="129" width="166" onerror="lib.errorImg(this)" alt="<s:property value="title"/>" src="<%=request.getContextPath()%><s:property value="cover160"/>">
                    </a>
                    <h2>
                        <a target="_blank" href="<%=request.getContextPath()%>/article/<s:property value="id"/>/"><s:property value="title"/></a>
                    </h2>
                    <p class="article-info">
                        评论&nbsp;<s:property value="commentCount"/>
                        <span class="article-info-sp">|</span>
                        推荐&nbsp;<s:property value="recommendCount"/>
                    </p>
                    <p class="article-meta">
                    <a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="postedByUser.id"/>/"><s:property value="postedByUser.nickName"/></a>&nbsp;发表于&nbsp;<s:property value="postedDateF"/>
                    </p>
                    <p><s:property value="ContentNoHtml100"/><a target="_blank" href="<%=request.getContextPath()%>/article/<s:property value="id"/>/">查看全文</a></p>
                    <p class="article-from">来自：<a href="<%=request.getContextPath()%>/site/<s:property value="articleTopic.id"/>/" target="_blank"><s:property value="articleTopic.name"/></a>&nbsp;主题站</p>
                </div>
    </s:iterator>
    </div>
    </s:if>
    <s:if test="tabName=='user'">
    <ul class="nut_list gpack">
      <s:iterator value="users">
      <li class="nut">
      <div class="nut-options">
         <a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="id" />/"><img onerror="lib.errorImg(this)" src="<%=request.getContextPath()%><s:property value="avatar48" />" width="48" height="48"></a>
          <s:if test="curUser.id==id">
          </s:if>
          <s:else> 
          <s:if test="isFollow==0">         
         <a class="gbtn-join-gray" data-params="id=<s:property value="id" />&className=quit-tag&txt=取消关注"  data-operation="doFollowUser"  href="javascript: void 0;">关注</a>
         </s:if>
         <s:else>
         <a class="quit-tag" data-params="id=<s:property value="id" />&className=gbtn-join-gray&txt=关注"  data-operation="doUnfollowUser"  href="javascript: void 0;">取消关注</a>
         </s:else>
         </s:else>  
         </div>
         <div class="nut-desc">
                    <p><a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="id" />/"><s:property value="nickName" /></a><span><s:property value="followedCount" />人关注</span></p>
                    <p><s:property value="title" /></p>
                    <a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="id" />/blog/"><s:property value="blogCount" />篇日志</a>
                </div>
      </li>
      </s:iterator>
    </ul>
    </s:if>
        <p:pages pageSize="${pageSize }" pageNo="${ pageNo}" recordCount="${recordCount }"></p:pages>
	   </div>
	   <div class="side gspan-10 gprefix-1">
	   <div class="side-title"> 
        <h2>相关标签</h2>
        </div>
        
        <ul class="side-tags">
       
    </ul>
	   </div>
    </div>
	    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
	    <script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/resourceUtils.js"></script>
			<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/iframeUploader.js"></script>
			<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/popWinUploader.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/tag.js"></script>
	</div>
</body>
</html>