<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="p" uri="/page_tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<title>XX - 标签广场</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp"%>
<link type="text/css"
	href="/staticFile/css/tag.css"
	rel="stylesheet" />
</head>
<body>
<div class="container">
<div class="grow tag-square-page">
        <div class="main gspan-21 gmt60">
        <s:if test="key==null||key==''">
            <h1 class="gbtitle">标签广场</h1>
            </s:if>
            <s:else>
            <h1 class="gbtitle">搜索标签</h1>
            <p class="search_result">“<s:property value="key"/>相关的标签</p>
            </s:else>
           <ul class="tag-list gpack gclear">
    <s:iterator value="tags">
<li>
            <div class="tag-list-options">
                <a target="_blank" href="/tag/<s:property value="cnSpell" />/"><img src="<s:property value="cover48" />" onerror="lib.errorImg(this)" width="48" height="48"></a>
                <s:if test="isFollowed==0">
                 <a class="gbtn-join-gray" data-params="id=<s:property value="id" />&className=quit-tag&txt=取消关注"  data-operation="doFollow"  href="javascript: void 0;">关注</a>
         </s:if>
         <s:else>
         <a class="quit-tag" data-params="id=<s:property value="id" />&className=gbtn-join-gray&txt=关注"  data-operation="doUnfollow"  href="javascript: void 0;">取消关注</a>
         </s:else>
            </div>
            <div class="tag-list-desc">
                <p>
                    <a target="_blank" href="/tag/<s:property value="cnSpell" />/"><s:property value="name"/></a>
                </p>
                <p>
                 <s:property value="followCount"/>关注
                </p>
                <p><s:property value="description50"/></p>
            </div>
        </li>
	 </s:iterator> 
 </ul>
    <p:pages pageSize="${pageSize}" pageNo="${pageNo}" recordCount="${spaceUser.tagCount}"></p:pages>
      
        </div>
        <div class="side gspan-10 gprefix-1 gmt60">
            <form action="/tag/all/" class="gsearch" method="get">
                <p>
                <input id="word" type="text" class="gsearch-txt" name="key" maxlength="10" placeholder="搜索标签" value="">
                <input type="submit" value="搜索" class="gsearch-bt gicon-search">
                </p>
            </form>
            <s:if test="key!=null&&key!=''">
            <p><a href="/tag/all/">返回标签广场&nbsp;&gt;</a></p>
            </s:if>
        </div>
    </div>
		<%@ include file="/WEB-INF/jsp/common/bottom.jsp"%>
		<script type="text/javascript"
			src="/staticFile/js/tag.js"></script>
	</div>
</body>
</html>

</body>
</html>