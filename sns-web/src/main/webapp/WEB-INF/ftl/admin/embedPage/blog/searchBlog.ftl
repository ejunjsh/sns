<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${request.contextPath}/admin/embedPage/blog/searchBlog" method="post">
	<input type="hidden" name="params.pageIndex" value="${params.pageIndex+1}" />
	<input type="hidden" name="params.pageSize" value="${params.pageSize}" />
	<input type="hidden" name="params.order" value="${params.order}" />
	<input type="hidden" name="params.sort" value="${params.sort}" />
	<div class="searchBar">
		<ul class="searchContent" style="height:75px;">
			<li>发布人:<input type="text" value="<@s.property value="params.postedByUser.nickName" />" name="params.postedByUser.nickName"/>
    <li>内容:<input type="text" value="${params.content!""}" name="params.content"/></li>
    <li>标签:<input type="text"  value="${params.tagName!""}" name="params.tagName"/></li>
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
			<li><a class="add" width="650" height="550" title="添加日志" mask="true" resource="tag.js,blog.js"  rel="addBlog" href="${request.contextPath}/admin/embedPage/blog/doBlog" target="dialog"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个用户"><span>删除</span></a></li>
			<li><a class="edit" width="650" height="550" title="修改日志" mask="true" resource="tag.js,blog.js"  rel="editBlog" href="${request.contextPath}/admin/embedPage/blog/doBlog?blog.id={bid}" target="dialog" warn="请选择一个日志"><span>修改</span></a></li>
		</ul>
	</div>

	<div id="w_list_print">
	<table class="list" width="98%" targetType="navTab" asc="asc" desc="desc" layoutH="166">
		<thead>
			<tr>
			<#if params.sort=="b.id">
			<th orderfield="b.id" class="${params.order}">id</th>
			 <#else>
            <th orderfield="b.id">id</th>
			</#if>
            <#if params.sort=="b.title">
			<th orderfield="b.title" class="${params.order}">标题</th>
			<#else>
            <th orderfield="b.title">标题</th>
			</#if>
            <th>内容</th>
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
			<#if params.sort=="b.viewCount">
			<th orderfield="b.viewCount" class="${params.order}">浏览数</th>
			<#else>
            <th orderfield="b.viewCount">浏览数</th>
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
             <#if params.sort=="b.postedDate">
			<th orderfield="b.postedDate" class="${params.order}">发布时间</th>
			<#else>
            <th orderfield="b.postedDate">发布时间</th>
			</#if> 
            <#if params.sort=="b.updatedDate">
			<th orderfield="b.updatedDate" class="${params.order}">更新时间</th>
			<#else>
            <th orderfield="b.updatedDate">更新时间</th>
			</#if>  
             <#if params.sort=="b.status">
			<th orderfield="b.status" class="${params.order}">状态</th>
			  <#else>
            <th orderfield="b.status">状态</th>
			</#if>
			</tr>
		</thead>
		<tbody>
		<#list blogs as blog>
			<tr target="bid" rel="${blog.id}">
				<td >${blog.id}</td>
				<td >${blog.title}</td>
				<td ><#if blog.content??>${blog.contentNoHtml20}</#if></td>
				<td>${blog.postedByUser.nickName}</td>
				<td >${blog.commentCount}</td>
				<td >${blog.viewCount}</td>
				<td >${blog.recommendCount}</td>
				<td ><#if blog.tagString??>${blog.tagString}</#if></td>
				<td >${blog.postedDateF}</td>
				<td >${blog.updatedDateF}</td>
				<td >${blog.statusName}</td>
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
