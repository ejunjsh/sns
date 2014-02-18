<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title>XX小组 - 全部小组</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="<%=request.getContextPath()%>/staticFile/css/group1.css"
	rel="stylesheet" />
</head>
<body>
<div class="container">
<div class="grow group-list-page">
        <div class="main gspan-21 gmt60">
            <h1>全部小组</h1>
            <ul class="gtabs gclear">
                <s:iterator value="allCategorys">
                  <s:if test="id==curCategory.id">
                    <li class="gtabs-curr"><s:property value="name" /></li>
                  </s:if>
                  <s:else>
                    <li>
                    <a href="/group/all/<s:property value="id" />/"><s:property value="name" /></a>
                   </li>
                  </s:else>
                </s:iterator>
            </ul>
            <ul class="group-list gpack">
            <s:iterator value="allGroups">
                <li>
                    <div class="group-list-options">
                        <a target="_blank" href="/group/<s:property value="id" />"><img src="<s:property value="cover48" />" class="group-icon" width="48" height="48"></a>
                       <s:if test="isJoined==1">
                     <a class="quit-group joinBt" data-operation="doQuit" data-params="id=<s:property value="id" />&className=gbtn-join-gray joinBt&txt=加入"  href="javascript:void 0;" >退出</a>
                       </s:if>
                       <s:else>  
                        <a class="gbtn-join-gray joinBt" data-operation="doJoin" data-params="id=<s:property value="id" />&className=quit-group joinBt&txt=退出" href="javascript: void 0;">加入</a>
                       </s:else>
                    </div>
                    <div class="group-list-desc">
                        <p>
                            <a target="_blank" href="/group/<s:property value="id" />"><s:property value="name" /></a>
                            <span><s:property value="joinedUserCount"/>人加入</span>
                        </p>
                        <p><s:property value="description30" />
                        </p>
                    </div>
                </li>
                </s:iterator>
            </ul>
<p:pages pageSize="${pageSize }" pageNo="${pageNo }" recordCount="${recordCount}"></p:pages>
        </div>
        <div class="side gspan-10 gprefix-1 gmt60">
            <div class="side-links">
                <a href="/group/hot_posts/">查看全部小组热贴 &gt;</a>
            </div>
            <form id="groupSearch" action="/group/search/" class="gsearch" method="get">
                <p>
                <input id="word" type="text" class="gsearch-txt" name="key" maxlength="10" placeholder="搜索小组" value="">
                <input type="submit" value="搜索" class="gsearch-bt gicon-search">
                </p>
            </form>
            <div class="side-title">
                
                <h2>热门小组</h2>
                
                <a class="side-title-txt" data-operation="changeData" data-params="id=hotGroups" id="refreshHot" href="javascript: void 0;">换一换</a>
                
            </div>
            <div id="hotGroups">
                
                <ul class="side-groups ">
                <s:iterator value="hotGroups" var="group" status="st">
                    <li>
                        <a class="pt-pic" href="/group/<s:property value="#group.id"/>/" title="<s:property value="#group.name"/>" target="_blank"><img width="48" height="48" src="<s:property value="#group.cover48"/>" alt="<s:property value="#group.name"/>" ></a>
                        <div class="pt-txt">
                            <h3><a href="/group/<s:property value="#group.id"/>/" target="_blank"><s:property value="#group.name"/></a></h3>
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
            <div class="side-title">
                
                <h2>新创建的小组</h2>
                
                <a class="side-title-txt" id="refreshNew" data-operation="changeData" data-params="id=newGroups" href="javascript: void 0;">换一换</a>
                
            </div>
            <div id="newGroups">
                
                              <ul class="side-groups ">
                <s:iterator value="newGroups" var="group" status="st">
                    <li>
                        <a class="pt-pic" href="/group/<s:property value="#group.id"/>/" title="<s:property value="#group.name"/>" target="_blank"><img width="48" height="48" src="<s:property value="#group.cover48"/>" alt="<s:property value="#group.name"/>" ></a>
                        <div class="pt-txt">
                            <h3><a href="/group/<s:property value="#group.id"/>/" target="_blank"><s:property value="#group.name"/></a></h3>
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
            <div class="apply-holder">
                <a href="/group/apply/" class="new-group-link">申请创建小组 &gt;</a>
            </div>
            <div id="bdadm-559021" class="side-advert"></div>
        </div>
    </div>
		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="/staticFile/js/group.js"></script>
	</div>
</body>
</html>

</body>
</html>