<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="/admin/embedPage/question/searchQuestion" method="post">
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
			<li><a class="add" width="650" height="550" title="添加问题" mask="true" resource="tag.js,question.js"  rel="addQuestion" href="/admin/embedPage/question/doQuestion" target="dialog"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个用户"><span>删除</span></a></li>
			<li><a class="edit" width="650" height="550" title="修改问题" mask="true" resource="tag.js,question.js"  rel="editQuestion" href="/admin/embedPage/question/doQuestion?question.id={qid}" target="dialog" warn="请选择一个问题"><span>修改</span></a></li>
		</ul>
	</div>

	<div id="w_list_print">
	<table class="list" width="98%" targetType="navTab" asc="asc" desc="desc" layoutH="166">
		<thead>
			<tr>
			<#if params.sort=="q.id">
			<th orderfield="q.id" class="${params.order}">id</th>
			 <#else>
            <th orderfield="q.id">id</th>
			</#if>
            <#if params.sort=="q.title">
			<th orderfield="q.title" class="${params.order}">标题</th>
			<#else>
            <th orderfield="q.title">标题</th>
			</#if>
            <th>内容</th>
            <#if params.sort=="u.nickName">
			<th orderfield="u.nickName" class="${params.order}">发布人</th>
			<#else>
            <th orderfield="u.nickName">发布人</th>
			</#if>
			<#if params.sort=="answerCount">
			<th orderfield="answerCount" class="${params.order}">回答数</th>
			<#else>
            <th orderfield="answerCount">回答数</th>
			</#if>
			<#if params.sort=="commentCount">
			<th orderfield="commentCount" class="${params.order}">评论数</th>
			<#else>
            <th orderfield="commentCount">评论数</th>
			</#if>
			<#if params.sort=="q.viewCount">
			<th orderfield="q.viewCount" class="${params.order}">浏览数</th>
			<#else>
            <th orderfield="q.viewCount">浏览数</th>
			</#if>
			<#if params.sort=="t.name">
			<th orderfield="t.name" class="${params.order}">标签</th>
			<#else>
            <th orderfield="t.name">标签</th>
			</#if>
             <#if params.sort=="q.postedDate">
			<th orderfield="q.postedDate" class="${params.order}">发布时间</th>
			<#else>
            <th orderfield="q.postedDate">发布时间</th>
			</#if> 
            <#if params.sort=="q.updatedDate">
			<th orderfield="q.updatedDate" class="${params.order}">更新时间</th>
			<#else>
            <th orderfield="q.updatedDate">更新时间</th>
			</#if>  
             <#if params.sort=="q.status">
			<th orderfield="q.status" class="${params.order}">状态</th>
			  <#else>
            <th orderfield="q.status">状态</th>
			</#if>
			</tr>
		</thead>
		<tbody>
		<#list questions as question>
			<tr target="qid" rel="${question.id}">
				<td >${question.id}</td>
				<td >${question.title}</td>
				<td ><#if question.content??>${question.contentNoHtml20}</#if></td>
				<td>${question.postedByUser.nickName}</td>
				<td >${question.answerCount}</td>
				<td >${question.commentCount}</td>
				<td >${question.viewCount}</td>
				<td ><#if question.tagString??>${question.tagString}</#if></td>
				<td >${question.postedDateF}</td>
				<td >${question.updatedDateF}</td>
				<td >${question.statusName}</td>
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
