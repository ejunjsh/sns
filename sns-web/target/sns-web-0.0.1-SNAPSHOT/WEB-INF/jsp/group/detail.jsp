<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>XX小组 - <s:property value="group.name" /></title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/group1.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
	<div class="grow gmt40 group-index-page">
	   <div class="main gspan-21 gsuffix-1">
	      <div class="gbtitle">
			<div class="gfl gbtitle-info">
			   <a class="pt-pic" href="<%=request.getContextPath()%>/group/<s:property value="group.id" />/">
<img width="48" height="48" alt="<%=request.getContextPath()%><s:property value="group.name" />" src="<s:property value="group.cover48" />">
</a>
			<div class="pt-txt">
<h1><s:property value="group.name" /> </h1>
<p id="memberCounter"><s:property value="group.joinedUserCount" />人加入此小组</p>
</div>
			</div>

			<div class="gfr gbtitle-btns">
			   <s:if test="group.isJoined==1">
<a  data-operation="doQuit" data-params="id=<s:property value="group.id" />&className=gbtn-ext&txt=加入小组"  href="javascript:void 0;">已加入 / 退出</a>
			   </s:if>
			   <s:else>
 <a data-operation="doJoin" data-params="id=<s:property value="group.id" />&className=&txt=已加入 / 退出" class="gbtn-ext"  href="javascript:void 0;">加入小组</a>
			   </s:else>
			</div>
	   </div>
	   <div class="main-body">
	   <s:if test="pageNo==1" >
	   <h2>黑板报</h2>
	   <div class="blackboard">
	   <div>
	   <s:property value="group.description" escape="false" />
	   </div>
	   <p class="blackboard-title">
		<span>组长</span>
		<a href="<%=request.getContextPath()%>/i/<s:property value="group.createdByUser.id" />/"><s:property value="group.createdByUser.nickName" /></a>
	   </div>
	   </s:if>
	   <div class="main-title">
<h2>帖子列表</h2>
<a id="newPost" class="gbtn" href="<%=request.getContextPath()%>/group/<s:property value="group.id" />/post">发新帖</a>
</div>
<ul class="gtabs">
<li class="gtabs-curr">全部帖子</li>
<li>
<a href="<%=request.getContextPath()%>/group/<s:property value="group.id" />/posts/digest/">精华区</a>
</li>
</ul>
<ul class="titles">
<s:iterator value="postInGroup">
<li>
<h3 class="titles-txt"><a target="_blank" href="<%=request.getContextPath()%>/post/<s:property value="id" />/"><s:property value="title" />
    <s:if test="isTop==1">
                                <span class="gicon-top"></span>
                                </s:if>
                                <s:if test="isBest==1">
                                <span class="gicon-best"></span>
                                </s:if>
                        </a></h3>
                        <div class="titles-r-grey"><s:property value="commentCount" /><span class="titles-comment-icon">&nbsp;</span></div>
<p class="titles-b">
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
<p:pages pageSize="${pageSize }" pageNo="${ pageNo}" recordCount="${recordCount }"></p:pages>
	   </div>
	   </div>
	   <div class="side gspan-9 gsuffix-1">
	   <div class="side-title">
<h2>小组活跃成员</h2>
</div>
<div class="gpack">
<s:iterator value="activeMembers" >
<dl class="gpack_u">
                    <dt>
                    <a href="<%=request.getContextPath()%>/i/<s:property value="id" />/" title="<s:property value="nickName" />"><img width="48" height="48" src="<%=request.getContextPath()%><s:property value="avatar" />"></a>
                    </dt>
                    <dd class="gpack_u-n">
                    <p><a href="<%=request.getContextPath()%>/i/<s:property value="id" />/" title="<s:property value="nickName" />"><s:property value="nickName" /></a></p>
                    </dd>
                </dl>
</s:iterator>
</div>
<p class="side-back"><a href="<%=request.getContextPath()%>/group/<s:property value="group.id" />/members/">所有小组成员（<s:property value="group.joinedUserCount" />）&nbsp;&nbsp;&gt;</a></p>
<div class="side-title">
                
                <h2>相关小组</h2>
                
                <a id="groupsRefresh"  data-operation="changeData" data-params="id=hotGroups"  href="javascript: void 0;" class="side-title-txt">换一换</a>
                
            </div>	   
            <div id="hotGroups">
                
                <ul class="side-groups ">
                <s:iterator value="relatedGroups" var="group" status="st">
                    <li>
                        <a class="pt-pic" href="<%=request.getContextPath()%>/group/<s:property value="#group.id"/>/" title="<s:property value="#group.name"/>" target="_blank"><img width="48" height="48" src="<%=request.getContextPath()%><s:property value="#group.cover48"/>" alt="<s:property value="#group.name"/>" ></a>
                        <div class="pt-txt">
                            <h3><a href="<%=request.getContextPath()%>/group/<s:property value="#group.id"/>/" target="_blank"><s:property value="#group.name"/></a></h3>
                            <span><s:property value="#group.joinedUserCount"/>人加入</span>
                            <div class="pt-txt-d">
                                <p class="gellipsis"><s:property value="#group.description10"/></p>
                            </div>
                        </div>
                    </li>
                <s:if test="#st.count%3==0&&!#st.last">
                </ul>
                <ul class="side-groups ghide">
                </s:if>
                </s:iterator >
                </ul>
                
            </div>
	   </div>
	</div>
	    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/group.js"></script>
	</div>
</body>
</html>