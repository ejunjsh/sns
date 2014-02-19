 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <s:iterator value="myActivities">
    <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@FollowQuestion.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	        关注问题<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/question/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	   <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@RecommendBlog.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	        推荐日志<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/blog/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	  <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@RecommendActicle.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	        推荐文章<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/article/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <div><a class="gactive-img" target="_blank" href="<%=request.getContextPath()%>/article/<s:property value="refId"/>/"><img onerror="lib.errorImg(this)" src="<%=request.getContextPath()%><s:property value="pic"/>"/></a></div>
	       <p>
	       <s:property value="description"  escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	  <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@UpholdAnswer.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	       支持答案<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/answer/redirect/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <p>
	       <s:property value="description"  escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	  <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@AnswerQuestion.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	      回答问题<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/answer/redirect/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	  <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@NewQuestion.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	      发问题<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/question/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	  <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@RecommendPost.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	      推荐帖子<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/post/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	  <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@NewPost.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	      发帖子<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/post/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	  <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@NewBlog.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	      发日志<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/blog/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	  <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@NewArticle.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	      发文章<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/article/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <div><a class="gactive-img" target="_blank" href="<%=request.getContextPath()%>/article/<s:property value="refId"/>/"><img onerror="lib.errorImg(this)" src="<%=request.getContextPath()%><s:property value="pic"/>"/></a></div>
	       
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	  <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@CommentPost.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	      评论帖子<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/post/comment/redirect/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	  <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@CommentArticle.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	      评论文章<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/article/comment/redirect/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <div><a class="gactive-img" target="_blank" href="<%=request.getContextPath()%>/article/comment/redirect/<s:property value="refId"/>/"><img onerror="lib.errorImg(this)" src="<%=request.getContextPath()%><s:property value="pic"/>"/></a></div>
	      
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	  <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@CommentBlog.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	      评论日志<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/blog/comment/redirect/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	  	  <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@CommentPhoto.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	      评论图片<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/photo/comment/redirect/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <div><a class="gactive-img" target="_blank" href="<%=request.getContextPath()%>/photo/comment/redirect/<s:property value="refId"/>/"><img onerror="lib.errorImg(this)"  src="<%=request.getContextPath()%><s:property value="pic"/>"/></a></div>
	      
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	    <s:if test="activityType==@com.sky.sns.enumeration.ActivityTypeEnum@RecommendPhoto.getValue()">
	  <div class="gactive">
	       <div class="gactive-hd">
	      推荐图片<a class="gactive-hd-title" target="_blank" href="<%=request.getContextPath()%>/photo/<s:property value="refId"/>/"><s:property value="title"/></a>
	       </div>
	       <div class="gactive-bd">
	       <div><a class="gactive-img" target="_blank" href="<%=request.getContextPath()%>/photo/<s:property value="refId"/>/"><img onerror="lib.errorImg(this)"  src="<%=request.getContextPath()%><s:property value="pic"/>"/></a></div>
	      
	       <p>
	       <s:property value="description" escape="false"/>
	       </p>
	       <span>
                  <s:property value="createdDateF"/>
                </span>
	       </div>
	  </div>
	  </s:if>
	 </s:iterator> 