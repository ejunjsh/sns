<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${request.contextPath}/admin/embedPage/group/searchGroupPost" method="post">
	<input type="hidden" name="params.pageIndex" value="${params.pageIndex+1}" />
	<input type="hidden" name="params.pageSize" value="${params.pageSize}" />
	<input type="hidden" name="params.order" value="${params.order}" />
	<input type="hidden" name="params.sort" value="${params.sort}" />
	<div class="searchBar">
		<ul class="searchContent" style="height:75px;">
			<li>发布人:<input type="text" value="<@s.property value="params.postedByUser.nickName" />" name="params.postedByUser.nickName"/>
    <li>内容:<input type="text" value="${params.content!""}" name="params.content"/></li>
    <li>标签:<input type="text"  value="${params.tagName!""}" name="params.tagName"/></li>
    <li>小组:<input type="text"  value="<@s.property value="params.group.name" />" name="params.group.name"/></li>
    <li>发布时间:<input readonly="true" class="date" value="<#if params.postedDateMin??>${params.postedDateMin?datetime}</#if>" name="params.postedDateMin" type="text"></input></li><li><input readonly="true" class="date" value="<#if params.postedDateMax??>${params.postedDateMax?datetime}</#if>"   name="params.postedDateMax" ></input></li>
    <li>更新时间:<input readonly="true" value="<#if params.updatedDateMin??>${params.updatedDateMin?datetime}</#if>" class="date"  name="params.updatedDateMin" type="text"></input></li><li><input readonly="true" class="date"  value="<#if params.updatedDateMax??>${params.updatedDateMax?datetime}</#if>" name="params.updatedDateMax" type="text"></input></li>
    <li>状态:<@s.select id="status" cssClass="combox" name="params.status" value="params.status" list="statusMap" listKey="key" listValue="value" headerKey="0" headerValue="请选择"></@s.select></li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" width="650" height="550" title="添加帖子" mask="true" resource="tag.js,group.js"  rel="addGroupPost" href="${request.contextPath}/admin/embedPage/group/doGroupPost" target="dialog"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个用户"><span>删除</span></a></li>
			<li><a class="edit" width="650" height="550" title="修改帖子" mask="true" resource="tag.js,group.js"  rel="editGroupPost" href="${request.contextPath}/admin/embedPage/group/doGroupPost?groupPost.id={gpid}" target="dialog" warn="请选择一篇帖子"><span>修改</span></a></li>
		</ul>
	</div>

	<div id="w_list_print">
	<table class="list" width="98%" targetType="navTab" asc="asc" desc="desc" layoutH="166">
		<thead>
			<tr>
			<#if params.sort=="id">
			<th orderfield="id" class="${params.order}">id</th>
			 <#else>
            <th orderfield="id">id</th>
			</#if>
            <#if params.sort=="gp.title">
			<th orderfield="gp.title" class="${params.order}">标题</th>
			<#else>
            <th orderfield="gp.title">标题</th>
			</#if>
            <th>内容</th>
            <#if params.sort=="g.name">
			<th orderfield="g.name" class="${params.order}">主题</th>
			<#else>
            <th orderfield="g.name">小组</th>
			</#if>
            <#if params.sort=="u.nickName">
			<th orderfield="u.nickName" class="${params.order}">发布人</th>
			<#else>
            <th orderfield="u.nickName">发布人</th>
			</#if>
			<#if params.sort=="commentCount">
			<th orderfield="commentCount" class="${params.order}">评论数</th>
			<#else>
            <th orderfield="commentCount">评论数</th>
			</#if>
			<#if params.sort=="gp.viewCount">
			<th orderfield="gp.viewCount" class="${params.order}">浏览数</th>
			<#else>
            <th orderfield="gp.viewCount">浏览数</th>
			</#if>
			<#if params.sort=="recommendCount">
			<th orderfield="recommendCount" class="${params.order}">推荐数</th>
			<#else>
            <th orderfield="recommendCount">推荐数</th>
			</#if>
			<#if params.sort=="t.name">
			<th orderfield="t.name" class="${params.order}">标签</th>
			<#else>
            <th orderfield="t.name">标签</th>
			</#if>
             <#if params.sort=="gp.postedDate">
			<th orderfield="gp.postedDate" class="${params.order}">发布时间</th>
			<#else>
            <th orderfield="gp.postedDate">发布时间</th>
			</#if> 
            <#if params.sort=="gp.updatedDate">
			<th orderfield="gp.updatedDate" class="${params.order}">更新时间</th>
			<#else>
            <th orderfield="gp.updatedDate">更新时间</th>
			</#if>  
             <#if params.sort=="gp.status">
			<th orderfield="gp.status" class="${params.order}">状态</th>
			  <#else>
            <th orderfield="gp.status">状态</th>
			</#if>
			</tr>
		</thead>
		<tbody>
		<#list groupPosts as groupPost>
			<tr target="gpid" rel="${groupPost.id}">
				<td >${groupPost.id}</td>
				<td >${groupPost.title}</td>
				<td ><#if groupPost.content??>${groupPost.contentNoHtml20}</#if></td>
				<td ><#if groupPost.group??>${groupPost.group.name}</#if></td>
				<td>${groupPost.postedByUser.nickName}</td>
				<td >${groupPost.commentCount}</td>
				<td >${groupPost.viewCount}</td>
				<td >${groupPost.recommendCount}</td>
				<td ><#if groupPost.tagString??>${groupPost.tagString}</#if></td>
				<td >${groupPost.postedDateF}</td>
				<td >${groupPost.updatedDateF}</td>
				<td >${groupPost.statusName}</td>
			</tr>
			</#list>
		</tbody>
	</table>
	</div>
	
	<div class="panelBar" >
		<div class="pages">
			<span>显示</span>
			<@s.select list="{'20','50','100','200'}" value="params.pageSize"  name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})"></@s.select>
			<span>条，共${params.total}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${params.total}" numPerPage="${params.pageSize}" pageNumShown="10" currentPage="${params.pageIndex+1}"></div>

	</div>

</div>
