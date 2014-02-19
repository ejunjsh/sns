<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="gtop-wp gsuffix-4">
<div class="gtop pack gclear gmt20">
                <div class="gtop-main gfl">
                   <h1>
                        <span><s:property value="spaceUser.nickName" /></span>
                    </h1>
                </div>
                
                <div id="gtopBtns" class="gtop-side">
                    <s:if test="curUser.id==spaceUser.id">
                   
                    </s:if>
                    <s:else>
                     <s:if test="spaceUser.isFollow==0">
                    <a  data-operation="doFollow" data-params="id=<s:property value="spaceUser.id"/>" class="gbtn-focus" href="javascript: void 0;">加关注</a>
                    </s:if>
                    <s:else>
                    <a class="gbtn-complete" data-event="click,mouseenter,mouseleave" data-operation="doUnfollow,followEnter,followLeave" data-params="id=<s:property value="spaceUser.id"/>"  href="javascript: void 0;">已关注</a>
                    </s:else>
                    <a href="<%=request.getContextPath()%>/infoCenter/message/send/<s:property value="spaceUser.id" />/" class="gbtn-nobg">站内信</a>
                    </s:else>
                </div>
                </div>
                </div>
                <div class="side gspan-6">
                <div class="side-head">
                <a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/">
                    <img width="160" height="160" onerror="lib.errorImg(this)" src="<%=request.getContextPath()%><s:property value="spaceUser.avatar160" />" alt="<s:property value="spaceUser.nickName" />" >
                </a>
            </div>
                <ul class="side-nav focus">
                <li class="gclear">
                <a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/following/" class="side-title">关注<span class="side-title-more">（全部<s:property value="spaceUser.followingCount" />人）</span></a>
                <p class="focus_list">
                <s:iterator value="sideFollowingUsers">
                   <a  target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="id" />/"><img onerror="lib.errorImg(this)" width="24px" height="24px" src="<%=request.getContextPath()%><s:property value="avatar24" />" alt="<s:property value="nickName" />"></a>
                </s:iterator>
                </p>
                </li>
                <li class="gclear">
                <a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/follower/" class="side-title">被关注<span class="side-title-more">（全部<s:property value="spaceUser.followedCount" />人）</span></a>
                  <p class="focused_list">
                <s:iterator value="sideFollowedUsers">
                   <a target="_blank" href="<%=request.getContextPath()%>/i/<s:property value="id" />/"><img onerror="lib.errorImg(this)" width="24px" height="24px" src="<%=request.getContextPath()%><s:property value="avatar24" />" alt="<s:property value="nickName" />"></a>
                </s:iterator>
                </p>
                </li>
                </ul>
                <ul class="side-nav">
                
                <li>
                  <s:if test="curPage!='blog'" >
                    <a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/blog/" class="side-title">
                        <span class="gicon-blog">&nbsp;</span>日志：
                        <span class="side-title-more"><s:property value="spaceUser.blogCount" />篇</span>
                    </a>
                  </s:if>
                  <s:else>
                  <span class="side-title">
                        <span class="gicon-blog-actived">&nbsp;</span>日志：
                        <span class="side-title-more"><s:property value="spaceUser.blogCount" />篇</span>
                    </span>
                  </s:else>
                </li>
                
                
                <li>
                <s:if test="curPage!='answer'" >
                    <a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/answer/" class="side-title">
                        <span class="gicon-answer">&nbsp;</span>回答：
                        <span class="side-title-more"><s:property value="spaceUser.answerCount" />条</span>
                    </a>
                  </s:if>
                  <s:else>
                    <span class="side-title">
                        <span class="gicon-answer-actived">&nbsp;</span>回答：
                        <span class="side-title-more"><s:property value="spaceUser.answerCount" />条</span>
                    </span>
                    </s:else>
                </li>
                
                
                <li>
                 <s:if test="curPage!='question'" >
                    <a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/question/" class="side-title">
                        <span class="gicon-question">&nbsp;</span>提问：
                        <span class="side-title-more"><s:property value="spaceUser.questionCount" />条</span>
                    </a>
                     </s:if>
                  <s:else>
                    <span class="side-title">
                        <span class="gicon-question-actived">&nbsp;</span>提问：
                        <span class="side-title-more"><s:property value="spaceUser.questionCount" />条</span>
                    </span>
                    </s:else>
                </li>
                
                
                <li>
                <s:if test="curPage!='post'" >
                    <a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/post/" class="side-title">
                        <span class="gicon-post">&nbsp;</span>帖子：
                        <span class="side-title-more"><s:property value="spaceUser.postCount" />个</span>
                    </a>
                 </s:if>
                  <s:else>
                    <span class="side-title">
                        <span class="gicon-post-actived">&nbsp;</span>帖子：
                        <span class="side-title-more"><s:property value="spaceUser.postCount" />个</span>
                    </span>
                    </s:else>
                </li>
                 <li>
                <s:if test="curPage!='albumList'&&curPage!='albumDetail'&&curPage!='viewPhoto'" >
                    <a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/album/" class="side-title">
                        <span class="gicon-tag">&nbsp;</span>相册：
                        <span class="side-title-more"><s:property value="spaceUser.photoCount" />张</span>
                    </a>
                 </s:if>
                  <s:else>
                    <span class="side-title">
                        <span class="gicon-tag-actived">&nbsp;</span>相册：
                        <span class="side-title-more"><s:property value="spaceUser.photoCount" />张</span>
                    </span>
                    </s:else>
                </li>
            </ul>
            <ul class="side-nav">
                
                <li>
                <s:if test="curPage!='activity'" >
                    <a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/activity/" class="side-title">
                        <span class="gicon-news">&nbsp;</span>动态：
                        <span class="side-title-more"><s:property value="spaceUser.activityCount" />条</span>
                    </a>
                                     </s:if>
                  <s:else>
                    <span class="side-title">
                        <span class="gicon-news-actived">&nbsp;</span>动态：
                        <span class="side-title-more"><s:property value="spaceUser.activityCount" />条</span>
                    </span>
                    </s:else>
                </li>
                
                
                <li>
                <s:if test="curPage!='tag'" >
                    <a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/tag/" class="side-title">
                        <span class="gicon-tag">&nbsp;</span>标签：
                        <span class="side-title-more"><s:property value="spaceUser.tagCount" />个</span>
                    </a>
                                        </s:if>
                  <s:else>
                    <span class="side-title">
                        <span class="gicon-tag-actived">&nbsp;</span>标签：
                        <span class="side-title-more"><s:property value="spaceUser.tagCount" />个</span>
                    </span>
                    </s:else>
                </li>
                
                
                <li>
                  <s:if test="curPage!='group'" >
                    <a href="<%=request.getContextPath()%>/i/<s:property value="spaceUser.id" />/group/" class="side-title">
                        <span class="gicon-group">&nbsp;</span>小组：
                        <span class="side-title-more"><s:property value="spaceUser.groupCount" />个</span>
                    </a>
                                       </s:if>
                  <s:else>
                    <span class="side-title">
                        <span class="gicon-group-actived">&nbsp;</span>小组：
                        <span class="side-title-more"><s:property value="spaceUser.groupCount" />个</span>
                    </span>
                    </s:else>
                </li>
                
            </ul>
            </div>