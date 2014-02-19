<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>XX- 搜索</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/search.css" rel="stylesheet" />
</head>
<body>
<div class="gcontainer gmt60 search-page">
<div class="form-wp">
            <form class="grow" id="search">
                <input type="text" name="key" maxlength="30" placeholder="寻找你喜欢的内容或人" value="<s:property value="key" />" class="search-text gspan-18" id="searchTxt">
                <input type="submit" value="搜索" class="search-submit">
            </form>
        </div>
        <div class="tabs-wp">
            <ul class="grow">
               <s:if test="searchType==0">
                <li class="current">
                </s:if>
                <s:else>
                <li>
                </s:else>
                    <a href="<%=request.getContextPath()%>/search/all/?key=<s:property value="encodeKey" />">全站</a>
                    <b class="garrow_up arrow1"></b>
                    <b class="garrow_up arrow2"></b>
                </li>
                 <s:if test="searchType==@com.sky.sns.enumeration.DocTypeEnum@Article.getValue()">
                <li class="current">
                </s:if>
                <s:else>
                <li>
                </s:else>
                    <a href="<%=request.getContextPath()%>/search/article/?key=<s:property value="encodeKey" />">文章</a>
                    <b class="garrow_up arrow1"></b>
                    <b class="garrow_up arrow2"></b>
                </li>
                 <s:if test="searchType==@com.sky.sns.enumeration.DocTypeEnum@Question.getValue()">
                <li class="current">
                </s:if>
                <s:else>
                <li>
                </s:else>
                    <a href="<%=request.getContextPath()%>/search/question/?key=<s:property value="encodeKey" />">问答</a>
                    <b class="garrow_up arrow1"></b>
                    <b class="garrow_up arrow2"></b>
                </li>
                 <s:if test="searchType==@com.sky.sns.enumeration.DocTypeEnum@Post.getValue()">
                <li class="current">
                </s:if>
                <s:else>
                <li>
                </s:else>
                    <a href="<%=request.getContextPath()%>/search/post/?key=<s:property value="encodeKey" />">帖子</a>
                    <b class="garrow_up arrow1"></b>
                    <b class="garrow_up arrow2"></b>
                </li>
                <s:if test="searchType==@com.sky.sns.enumeration.DocTypeEnum@Blog.getValue()">
                <li class="current">
                </s:if>
                <s:else>
                <li>
                </s:else>
                    <a href="<%=request.getContextPath()%>/search/blog/?key=<s:property value="encodeKey" />">日志</a>
                    <b class="garrow_up arrow1"></b>
                    <b class="garrow_up arrow2"></b>
                </li>
                 <s:if test="searchType==@com.sky.sns.enumeration.DocTypeEnum@User.getValue()">
                <li class="current">
                </s:if>
                <s:else>
                <li>
                </s:else>
                    <a href="<%=request.getContextPath()%>/search/user/?key=<s:property value="encodeKey" />">用户</a>
                    <b class="garrow_up arrow1"></b>
                    <b class="garrow_up arrow2"></b>
                </li>
            </ul>
        </div>
        <div class="grow search-page">
        <div class="main gspan-21 gsuffix-1">
        <ul class="items">
        <s:if test="searchType==0&&recommendGroupDoc!=null">
        <li class="items-sticky">
                        <div class="item-sticky">
                            <a href="<%=request.getContextPath()%>/group/<s:property value="recommendGroupDoc.id" />/" class="item-pic" target="_blank"><img onerror="lib.errorImg(this)" src="<%=request.getContextPath()%><s:property value="recommendGroupDoc.remark" />" width="48" height="48" ></a>
                                <div class="group-desc">
                                    <h2><a target="_blank" href="<%=request.getContextPath()%>/group/<s:property value="recommendGroupDoc.id" />/"><s:property value="recommendGroupDoc.title" escape="false"/></a><span>小组</span></h2>
                                    <p>
<s:property value="recommendGroupDoc.content" escape="false" /></p>
                                </div>
                        </div>
                    </li>
        </s:if>
        <s:iterator value="docs">
         <s:if test="searchType!=@com.sky.sns.enumeration.DocTypeEnum@User.getValue()">
        <li class="items-post">
        <s:if test="type==@com.sky.sns.enumeration.DocTypeEnum@Blog.getValue()">
        <h2>
        
<span>[日志]</span>
<a href="<%=request.getContextPath()%>/blog/<s:property value="id" />/" target="_blank">
<s:property value="title" escape="false" />
</a>
</h2>
</s:if>
<s:if test="type==@com.sky.sns.enumeration.DocTypeEnum@Question.getValue()">
        <h2>
        
<span>[问题]</span>
<a href="<%=request.getContextPath()%>/question/<s:property value="id" />/" target="_blank">
<s:property value="title" escape="false" />
</a>
</h2>
</s:if>
<s:if test="type==@com.sky.sns.enumeration.DocTypeEnum@Post.getValue()">
        <h2>
        
<span>[帖子]</span>
<a href="<%=request.getContextPath()%>/post/<s:property value="id" />/" target="_blank">
<s:property value="title" escape="false" />
</a>
</h2>
</s:if>
<s:if test="type==@com.sky.sns.enumeration.DocTypeEnum@Article.getValue()">
        <h2>
        
<span>[文章]</span>
<a href="<%=request.getContextPath()%>/article/<s:property value="id" />/" target="_blank">
<s:property value="title" escape="false" />
</a>
</h2>
</s:if>
<p>
<s:property value="content" escape="false" />
</p>
<p>
标签：<s:property value="tagString" escape="false" />
</p>
<p>
来自：
<s:if test="type==@com.sky.sns.enumeration.DocTypeEnum@Blog.getValue()">
<a href="<%=request.getContextPath()%>/i/<s:property value="fromValue" />/" target="_blank"><s:property value="fromName" /></a>
 个人主页  <s:property value="dateF" />
 </s:if>
 <s:if test="type==@com.sky.sns.enumeration.DocTypeEnum@Question.getValue()">
<a href="<%=request.getContextPath()%>/question/newest/" target="_blank">XX问答</a> <s:property value="dateF" />
 </s:if>
  <s:if test="type==@com.sky.sns.enumeration.DocTypeEnum@Article.getValue()">
<a href="<%=request.getContextPath()%>/site/<s:property value="fromValue" />/" target="_blank"><s:property value="fromName" /></a> 
主题站  <s:property value="dateF" />
 </s:if>
   <s:if test="type==@com.sky.sns.enumeration.DocTypeEnum@Post.getValue()">
<a href="<%=request.getContextPath()%>/group/<s:property value="fromValue" />/" target="_blank"><s:property value="fromName" /></a> 
小组  <s:property value="dateF" />
 </s:if>
</p>
        </li>
        </s:if>
        <s:else>
        <li class="items-guy">
        <div class="item-content">
        <s:set name="array" value='remark.split("\\\\,")' />
                            <a><img onerror="lib.errorImg(this)" src="<%=request.getContextPath()%><s:property value="#array[0]" />" alt="" width="48" height="48"></a>
                            <div class="guy-desc">
                                <a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="id" />/"><s:property value="title" escape="false" /></a>
                                <span><s:property value="#array[1]" />人关注</span>
                                <p>
                                <s:property value="content" escape="false"/>
                                </p>
                            </div>
          </div>
        </li>
        </s:else>
        </s:iterator>
        </ul>
        <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${recordCount}"></p:pages>
        </div>
        <div class="side gspan-10">
                <a href="<%=request.getContextPath()%>/tag/all" class="side-link" target="_blank">找标签？去标签广场&nbsp;&gt;</a>
                <a href="<%=request.getContextPath()%>/group/all/" class="side-link" target="_blank">找小组？去小组广场&nbsp;&gt;</a>
        
        <div class="side-title">
                <h2>热门搜索</h2>
        </div>
        <ul>
         <s:iterator value="statisticObject.keys">
        <li>
        <a href="<%=request.getContextPath()%>/search/all?key=<s:property value="keyWord" />"><s:property value="keyWord" /></a>
         <s:property value="count" />
        </li>
        </s:iterator>
        </ul>
        </div>
        </div>
	 <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/question.js"></script>
	</div>
</body>