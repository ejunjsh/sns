<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title> 站内信|消息中心-- XX网</title>
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
				<li class="gactived">
<span class="gicon-message"></span>
<span>站内信</span>
</li>
					<li>
<a class="gicon-notice" href="/infoCenter/notice/"></a>
<a href="/infoCenter/notice/">通知</a>
</li>

				</ul>
			</div>
			<div class="gspan-24 gprefix-1 message-content-page">
                <div class="gbtitle">
             <s:if test="toUser==null">
            <h2>发站内信<a href="/infoCenter/message/">[返回站内信]</a></h2>
            </s:if>
            <s:else>
             <h2>和<s:property value="toUser.nickName" />对话<a href="/infoCenter/message/">[返回站内信]</a></h2>
            </s:else>
        </div>
        <div class="recent-list">
        
        <form class="gform gclear" method="POST" action="" id="newMessage">
        <s:if test="toUser==null">
        
 <label for="addressee">发给</label>
 <div class="gform-box">
            <input class="gstxt" id="addressee" name="toUser.nickName" type="text"> 
        </div>  
        </s:if>
    <label for="msgTxt"></label>
    
<div class="gform-box">
            <textarea class="" id="msgTxt" name="message.content">
            <s:property value="message.content" />
            </textarea>
        </div>
        <label for="www"></label>
    
            <p class="main-form-submit">
                <input type="submit" class="gbtn-primary gform-submit" value="发送">
            </p>
        </form>
        
        <ul class="main-message" id="msgList">
            
            <s:iterator value="messages">
            <s:if test="fromUserId==curUser.id">
            <li class="sent">
            </s:if>
            <s:else>
            <li>
            </s:else>
            
                <div class="main-message-avatar">
                    <a href="/i/<s:property value="fromUserId" />/" target="_blank" title="<s:property value="fromUser.nickName" />"><img onerror="lib.errorImg(this)" src="<s:property value="fromUser.avatar48" />" width="48" height="48" alt="<s:property value="fromUser.nickName" />"></a>
                    <b class="arrow"><s class="arrow"></s></b>
                </div>
                <div class="main-message-content">
                    <p><a href="/i/<s:property value="fromUserId" />/" target="_blank"><s:property value="fromUser.nickName" /></a>：<s:property value="content" escape="false" /></p>
                    <p class="main-message-content-do">
                    <span class="gfl"><s:property value="createdDateF" /></span>
                    <span class="gfr"> <a href="javascript: void 0;" data-operation="doDeleteMessage" data-params="id=<s:property value="id" />&confirm=删除这条信息？" class="ghide">删除</a></span>
                    </p>
                </div>
            </li>
           
            </s:iterator>
            
        </ul>
        
 <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${recordCount}"></p:pages>


    </div>
              </div>
            </div>
		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
	</div>
	<script type="text/javascript" src="/staticFile/js/message1.js"></script>
	<script type="text/javascript" src="/staticFile/js/kindeditor-min.js"></script>
<script type="text/javascript" src="/staticFile/js/zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	KindEditor.ready(function(K) {
		K.create('#msgTxt', {
			uploadJson:'<%=request.getContextPath()%>/ajax/keImageUpload/upload',
			cssPath : ['<%=request.getContextPath()%>/staticFile/css/kind-code.css'],
			items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link','code']
		});

	});
});
	</script>
</body>
</html>