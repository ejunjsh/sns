<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title><s:property value="question.title" /> - XX问答 - XX网</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/ask1.css"
	rel="stylesheet" />
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/js/sh/sh.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
<div class="grow gmt60 ask-content-page">
<div class="main gspan-21">
    <div class="gbreadcrumb">
                <ul>
                  <li><a href="/question">问答</a></li>
					<li><s:if test="question.title.length()>10">
							<s:property value="question.title.substring(0,10)+'...'" />
						</s:if> <s:else>
							<s:property value="question.title" />
						</s:else></li>
                </ul>
    </div>
	<div class="post">
        <div class="post-title">
            <h1 id="articleTitle"><s:property value="question.title" /></h1>
            
        </div>
        
        
        <p id="tags" class="post-tags tags">
        <s:iterator value="question.tags" status='st'>
            
            <span class="tag"><a href="/tag/<s:property value="cnSpell"/>"><s:property value="name"/></a></span>
            <s:if test="#st.Last!=true">
            <span class="split">|</span>
            </s:if>
        </s:iterator>
        </p>
        
        
        <div id="articleContent" class="post-detail">
            <p id="questionDesc">
            <s:property value="question.contentWithAtLink" escape="false" />
            </p>
            
        </div>
        <div id="questionCmts" class="cmtsBody">
            <div class="cmts-t cmtsTitle">
                <p class="gfl">
                  
								<a href="javascript: void 0;" data-params="refId=<s:property value="question.id"/>&commentType=1" data-operation="getComments"
									class="cmts-num"><s:if test="question.commentCount==0">添加讨论</s:if><s:else><s:property value="question.commentCount"/>条讨论</s:else></a>
								<s:if test="question.postedByUser.id==curUser.id">
									<span class="split">|</span>
									<a href="/question/<s:property value="question.id" />/edit">修改</a>
								</s:if>
								<s:if test="question.postedByUser.id==curUser.id">
								<span class="split">|</span>
									<s:if test="question.status==3">
										<a href="javascript:void(0);" data-params="id=<s:property value="question.id"/>&confirm=是否解锁问题？" data-operation="doLock">解锁问题</a>
									</s:if>
									<s:else>
										<a href="javascript:void(0);" data-params="id=<s:property value="question.id"/>&confirm=是否锁定问题？" data-operation="doLock">锁定问题</a>
									</s:else>
								</s:if>
                </p>

                <p class="gfr">
                    <span class="post-user">
                        
                        <a target="_blank" href="/i/<s:property
									value="question.postedByUser.id" />" id="articleAuthor"><s:property
									value="question.postedByUser.nickName" /></a>
                        
                    </span>
                    <span class="post-date">发表于&nbsp;&nbsp;<s:property
								value="question.postedDateF"/></span>
                </p>

            </div>
            <div id="cmtListQ<s:property value="question.id"/>" class="cmtsList ghide cmts-bd gclear">
                <b class="arrow_up">
                    <s class="arrow_up"></s>
                </b>
                <div class="cmts-do cmtsDo">
				 <s:if test="question.status==3">
								<p>此问题目前不允许回应</p>
								</s:if>
								<s:else>
                    <textarea class="gbtxt" cols="30" rows="10" name="cmt"></textarea>
                    <p>
                        <a data-operation="cancelCmt" class="cancel-cmt" href="javascript:void 0;">取消</a>
                         <input type="submit" value="讨论" class="gbtn-primary" class="mw_btn" data-params="refId=<s:property value="question.id"/>&commentType=1" data-operation="addComment">
                                     <input type="hidden" name="replyName"/>
                                    <input type="hidden" name="replyId"/>
					</p>
					</s:else>
                </div>
                <ul class="cmts-list"></ul>
            </div>
            
            
        </div>
        
    </div>
	<div class="document-do">
        <div class="gfr">
		    <span class="focused-num"><s:property value="question.viewCount" />人浏览</span>
		    <span class="split">|</span>
            <span id="followNum" class="focused-num"><s:property value="question.followCount"/>人关注</span>
            <s:if test="question.isFollow<1">
            <a class="gbtn-primary" data-operation="doFollow" data-params="id=<s:property value="question.id"/>&className=gbtn&txt=取消关注" id="followBt" href="javascript:void 0;">关注</a>
            </s:if>
            <s:else>
            <a href="javascript:void 0;" id="followBt" data-params="id=<s:property value="question.id"/>&className=gbtn-primary&txt=关注" data-operation="doUnfollow" class="gbtn">取消关注</a>
            </s:else>
        </div>
    </div>
	<div id="answers" class="answers">
        <div class="answers-do">
           <span class="answers-num gfl">
		   <s:if test="question.answerCount==0">
		   目前还没有回答
           </s:if>
           <s:else>
		   <s:property value="question.answerCount" />个答案
           </s:else>		   
		   </span>
       </div>
	   <s:iterator value="answers" var="answer">
					<div class="answer" id="answer<s:property value="#answer.id"/>">
						<div class="answer-digg digg">
							<a href="javascript:void 0;" data-operation="doVoteAnswer"
											data-params="id=<s:property value="#answer.id" />&value=1&count=<s:property value="#answer.votedUpCount" />"  class="answer-digg-up" title="支持(+1)">
							<s:if test="#answer.votedUpCount>0"><s:property value="#answer.votedUpCount" /></s:if>
							<s:else>顶</s:else>
								</a>
							<a href="javascript:void 0;" data-operation="doVoteAnswer"
											data-params="id=<s:property value="#answer.id" />&value=2&count=<s:property value="#answer.votedDownCount" />"  class="answer-digg-dw" title="反对(-1)">
                            <s:if test="#answer.votedDownCount>0"><s:property value="#answer.votedDownCount" /></s:if>
							<s:else>踩</s:else>
                            </a>
						</div>
						<div class="answer-r">
							<div class="answer-t">
								<a class="answer-img" href="/i/<s:property value="#answer.createdByUser.id" />/" title="<s:property value="#answer.createdByUser.nickName" />"
									target="_blank"> <img width="24" height="24"
									src="<s:property value="#answer.createdByUser.avatar24" />">
								</a>
								<p class="answer-usr">
									<a class="answer-usr-name" href="/i/<s:property value="#answer.createdByUserId" />/"
										title="<s:property value="#answer.createdByUser.nickName" />" target="_blank"><s:property value="#answer.createdByUser.nickName" /></a>
								</p>
								<p class="answer-date"><s:property
								value="#answer.postedDateF"/></p>
							</div>
							<div class="answer-txt answerTxt">
								<s:property value="#answer.contentWithAtLink" escape="false"/><br />
							</div>
							<div class="cmts cmtsBody">
								<div class="cmts-t cmtsTitle">
									<p class="gfl">

								<a href="javascript: void 0;" data-params="refId=<s:property value="#answer.id"/>&commentType=2" data-operation="getComments"
									class="cmts-num"><s:if test="#answer.commentCount==0">添加讨论</s:if><s:else><s:property value="#answer.commentCount"/>条讨论</s:else></a>
											<s:if test="#answer.createdByUserId==curUser.id"> 
											<span class="split">
											|</span> <a
											href="javascript: void 0;" data-operation="deleteAnswer"
											data-params="id=<s:property value="#answer.id" />&confirm=是否删除你的回答？" >删除</a>
											 <span class="split">
											|</span> <a
											href="javascript: void 0;" >修改</a>
											</s:if>


									</p>
									<p class="gfr">
										<a href="javascript: void 0;" data-operation="doUselessAnswer"
											data-params="id=<s:property value="#answer.id" />" class="cmts-t-grey answer-hover">没有帮助</a>
									</p>
								</div>
								<div  id="cmtListA<s:property value="#answer.id"/>" class="cmtsList cmts-bd gclear ghide">
									<b class="arrow_up"> <s class="arrow_up"></s>
									</b>
									<div class="cmts-do cmtsDo">
										<s:if test="question.status==3">
								<p>此问题目前不允许回应</p>
								</s:if>
								<s:else>
                                <textarea name="cmt" rows="10" cols="30" class="gbtxt"></textarea>
                                <p>
								<a class="cancel-cmt" data-operation="cancelCmt" href="javascript:void 0;">取消</a>
                                    <input type="submit" value="讨论" class="gbtn-primary" data-params="refId=<s:property value="#answer.id"/>&commentType=2" data-operation="addComment">
                                    <input type="hidden" name="replyName"/>
                                    <input type="hidden" name="replyId"/>
                                </p>
                                </s:else>
									</div>
									<ul class="cmts-list">
									</ul>
								</div>
							</div>
						</div>
					</div>
</s:iterator>
	   <div id="answerReplyer" class="post_commet">
            <h3 id="replyer">添加回答</h3>
            <s:if test="curUser==null">
						<p>请 <a href="javascript:popWin.loginShow();">登录</a> 后才能回答问题哦</p>
						</s:if>
						<s:elseif test="question.status==3">
						<p>此问题目前不允许回答</p>
						</s:elseif>
						<s:else>
						<form method="POST" action="/question/<s:property value="question.id" />/newAnswer">
                             <textarea rows="1" name="answer.content" cols="40" class="t_txt" id="editor"></textarea>
                            <input type="submit" data-operation="addAnswer" value="新增答案" class="gbtn-primary">
                        </form>
						</s:else>
		</div>
	</div>   
</div>
<div class="side gspan-10 gprefix-1">
<a id="newPost" class="gbtn-primary" href="/question/ask/">提问</a>
</div>
</div>


		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script id="c-list" type="text/html">
   @! for (i = 0; i < data.length; i ++) {!@
      <li> <a href="/i/@!=data[i].postedByUserId!@/" target="_blank">@!=data[i].postedByUserNickName!@</a>：@!=data[i].content!@&nbsp;-&nbsp; @!=data[i].postedDate!@
                   @!if(userState.id==data[i].postedByUserId){!@              
                                    <a href="javascript:void 0"  data-operation="deleteComment"
											data-params="id=@!=data[i].id!@&confirm=是否删除你的回复？" class="cmts-list-d">删除</a>
                                    @!}!@
                                &nbsp;<a href="javascript: void 0" data-operation="replyComment"
											data-params="refId=@!=data[i].refId!@&check=@!=data[i].postedByUserNickName!@&commentType=@!=data[i].commentType!@&id=@!=data[i].id!@">回复</a></li>
    @!} !@
</script> 
        <script type="text/javascript" src="/staticFile/js/kindeditor-min.js"></script>
<script type="text/javascript" src="/staticFile/js/zh_CN.js"></script>
		<script type="text/javascript" src="/staticFile/js/sh/sh.js"></script>
		<script type="text/javascript" src="/staticFile/js/template.min.js"></script>
			<script type="text/javascript" src="/staticFile/js/question.js"></script>
		<s:if test="question.status==1&&curUser!=null">
		<script type="text/javascript">
		var kEditor;
                        	KindEditor.ready(function(K) {
                        		kEditor=K.create('#editor', {
                        			uploadJson:'<%=request.getContextPath()%>/ajax/keImageUpload/upload',
                        			cssPath : ['<%=request.getContextPath()%>/staticFile/css/kind-code.css'],
                        			items : [
                 							'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                 							'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                 							'insertunorderedlist', '|', 'emoticons', 'image', 'link','code']
                        		});

                        	});
        </script>
		</s:if>
	</div>
</body>
</html>

