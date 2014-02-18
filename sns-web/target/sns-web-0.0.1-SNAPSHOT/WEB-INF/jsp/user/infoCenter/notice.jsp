<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title> 通知|消息中心-- XX网</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css" href="/staticFile/css/settings.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
	
		<div class="grow gmt60 gpack">
			<div class="side gspan-6">
				<h1>消息中心</h1>
				<ul>
					<li>
<a class="gicon-message" href="/infoCenter/message/"></a>
<a href="/infoCenter/message/">站内信</a>
</li>
<li class="gactived">
<span class="gicon-notice"></span>
<span>通知</span>
</li>
				</ul>
			</div>
			<div class="gspan-24 gprefix-1 notice-page">
                <div class="gbtitle">
            <h2>通知</h2>
        </div>
<ul class="gprefix-2 gsuffix-3 titles">
   <s:iterator value="notices">
      <s:if test="noticeType==@com.sky.sns.enumeration.NoticeTypeEnum@QuestionAtNotice.getValue()">
                    <li>
                        <h3>
                            <a target="_blank" href="/question/<s:property value="refId" />">问题"<s:property value="title" />"@到你哦，快去看看吧</a>
                        </h3>
                        <span class="titles-r"><s:property value="updatedDateF" /></span>
                    </li>
                    </s:if> 
                    <s:if test="noticeType==@com.sky.sns.enumeration.NoticeTypeEnum@AnswerAtNotice.getValue()">
                    <li>
                        <h3>
                            <a target="_blank" href="/answer/redirect/<s:property value="refId" />">问题"<s:property value="title" />"的答案@到你哦，快去看看吧</a>
                        </h3>
                        <span class="titles-r"><s:property value="updatedDateF" /></span>
                    </li>
                    </s:if> 
                    <s:if test="noticeType==@com.sky.sns.enumeration.NoticeTypeEnum@BlogAtNotice.getValue()">
                    <li>
                        <h3>
                         <a target="_blank" href="/blog/<s:property value="refId" />">日志"<s:property value="title" />"@到你哦，快去看看吧</a>
                        </h3>
                        <span class="titles-r"><s:property value="updatedDateF" /></span>
                    </li>
                    </s:if> 
                     <s:if test="noticeType==@com.sky.sns.enumeration.NoticeTypeEnum@BlogCommentAtNotice.getValue()">
                    <li>
                        <h3>
                         <a target="_blank" href="/blog/comment/redirect/<s:property value="refId" />">日志"<s:property value="title" />"的评论@到你哦，快去看看吧</a>
                        </h3>
                        <span class="titles-r"><s:property value="updatedDateF" /></span>
                    </li>
                    </s:if> 
                   <s:if test="noticeType==@com.sky.sns.enumeration.NoticeTypeEnum@ArticleAtNotice.getValue()">
                    <li>
                        <h3>
                         <a target="_blank" href="/article/<s:property value="refId" />">文章"<s:property value="title" />"@到你哦，快去看看吧</a>
                        </h3>
                        <span class="titles-r"><s:property value="updatedDateF" /></span>
                    </li>
                    </s:if> 
                   <s:if test="noticeType==@com.sky.sns.enumeration.NoticeTypeEnum@ArticleCommentAtNotice.getValue()">
                    <li>
                        <h3>
                         <a target="_blank" href="/article/comment/redirect/<s:property value="refId" />">文章"<s:property value="title" />"的评论@到你哦，快去看看吧</a>
                        </h3>
                        <span class="titles-r"><s:property value="updatedDateF" /></span>
                    </li>
                    </s:if> 
                   <s:if test="noticeType==@com.sky.sns.enumeration.NoticeTypeEnum@PostCommentAtNotice.getValue()">
                    <li>
                        <h3>
                         <a target="_blank" href="/post/comment/redirect/<s:property value="refId" />">帖子"<s:property value="title" />"的评论@到你哦，快去看看吧</a>
                        </h3>
                        <span class="titles-r"><s:property value="updatedDateF" /></span>
                    </li>
                    </s:if>
                <s:if test="noticeType==@com.sky.sns.enumeration.NoticeTypeEnum@PostAtNotice.getValue()">
                    <li>
                        <h3>
                         <a target="_blank" href="/post/<s:property value="refId" />">帖子"<s:property value="title" />"@到你哦，快去看看吧</a>
                        </h3>
                        <span class="titles-r"><s:property value="updatedDateF" /></span>
                    </li>
                </s:if>
                <s:if test="noticeType==@com.sky.sns.enumeration.NoticeTypeEnum@QuestionCommentAtNotice.getValue()">
                    <li>
                        <h3>
                         <a target="_blank" href="/question/<s:property value="refId" />">有问题评论@到你哦，快去看看吧</a>
                        </h3>
                        <span class="titles-r"><s:property value="updatedDateF" /></span>
                    </li>
                </s:if> 
                <s:if test="noticeType==@com.sky.sns.enumeration.NoticeTypeEnum@AnswerCommentAtNotice.getValue()">
                    <li>
                        <h3>
                         <a target="_blank" href="/answer/redirect/<s:property value="refId" />">有答案评论@到你哦，快去看看吧</a>
                        </h3>
                        <span class="titles-r"><s:property value="updatedDateF" /></span>
                    </li>
                </s:if>
                <s:if test="noticeType==@com.sky.sns.enumeration.NoticeTypeEnum@PhotoCommentAtNotice.getValue()">
                    <li>
                        <h3>
                         <a target="_blank" href="/photo/redirect/<s:property value="refId" />">有图片评论@到你哦，快去看看吧</a>
                        </h3>
                        <span class="titles-r"><s:property value="updatedDateF" /></span>
                    </li>
                </s:if>                   
     </s:iterator>               
        </ul>
          <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${recordCount}"></p:pages>
            </div>
            </div>
		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
	</div>
</body>
</html>