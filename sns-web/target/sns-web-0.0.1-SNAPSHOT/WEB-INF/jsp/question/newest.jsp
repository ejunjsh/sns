<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>XX问题- 最新问题</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="/staticFile/css/ask1.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
	<div class="grow gmt60 ask-newest-page">
	  <div class="main gspan-21">
	  <div class="ask-title gclear">
        <h2>最新问题</h2>
        <a href="/question/ask/" class="gbtn-primary" id="newPost">提问</a>
    </div>
    <ul class="ask-list">
       <s:iterator value="newestQuestion">
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
                <h2><a href="/question/<s:property value="id"/>/"><s:property value="title"/></a></h2>
                <div class="ask-list-legend">
                    <p class="ask-list-tags">
                    标签：
                    
                      <s:iterator value="tags" status="st">  
                        <a href="/tag/<s:property value="cnSpell"/>/" target="_blank"><s:property value="name"/></a>
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
    <p:pages pageSize="${ pageSize}" pageNo="${ pageNo}" recordCount="${ recordCount}"></p:pages>
	  </div>
	</div>
	 <%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="/staticFile/js/question.js"></script>
	</div>
</body>
</html>