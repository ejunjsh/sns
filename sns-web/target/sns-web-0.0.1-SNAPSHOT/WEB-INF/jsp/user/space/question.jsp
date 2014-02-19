<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title><s:property value="spaceUser.nickName" />主页 - XX网</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/user.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
<div class="grow gmt60 ask-page">
 <%@ include file="/WEB-INF/jsp/user/space/common/leftSize.jsp"%>
            <div class="main gprefix-1 gspan-25">
            <div class="gbtitle">
        <h2><a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/" class="gbtitle-link">个人主页</a><span class="entities">&gt;</span>提问<span class="gbtitle-more">（全部<s:property value="spaceUser.questionCount" />个）</span></h2>
    </div>
 <ul class="ask-list">
       <s:iterator value="myQuestions">
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
                <h2><a href="<%=request.getContextPath()%>/question/<s:property value="id"/>/"><s:property value="title"/></a></h2>
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
    <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${spaceUser.questionCount}"></p:pages>
            </div>
           
</div>
	    <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/staticFile/js/space.js"></script>
</div>
</body>
</html>