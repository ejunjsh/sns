<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="/admin/embedPage/tag/searchTag" method="post">
	<input type="hidden" name="params.pageIndex" value="${params.pageIndex+1}" />
	<input type="hidden" name="params.pageSize" value="${params.pageSize}" />
	<input type="hidden" name="params.order" value="${params.order}" />
	<input type="hidden" name="params.sort" value="${params.sort}" />
	<div class="searchBar">
		<ul class="searchContent" style="height:75px;">
			<li>名称:<input value="${params.name!""}" type="text" name="params.name"/></li>
    <li>拼音:<input value="${params.cnSpell!""}" type="text" name="params.cnSpell"/></li>
    <li>描述:<input value="${params.description!""}" type="text" name="params.description"/></li>
    <li>创建时间:<input readonly="true" class="date" value="<#if params.createdDateMin??>${params.createdDateMin?datetime}</#if>" name="params.createdDateMin" type="text"></input></li><li><input readonly="true" class="date" value="<#if params.createdDateMax??>${params.createdDateMax?datetime}</#if>"  name="params.createdDateMax" ></input></li>
    <li>更新时间:<input readonly="true" value="<#if params.updatedDateMin??>${params.updatedDateMin?datetime}</#if>" class="date"  name="params.updatedDateMin" type="text"></input></li><li><input readonly="true" class="date"  value="<#if params.updatedDateMax??>${params.updatedDateMax?datetime}</#if>" name="params.updatedDateMax" type="text"></input></li>		
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
			<li><a class="add" title="添加标签" mask="true" resource="tag.js" rel="addTag" href="/admin/embedPage/tag/doTag" target="dialog"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个用户"><span>删除</span></a></li>
			<li><a class="edit" title="修改标签" mask="true" resource="tag.js"  rel="editTag" href="/admin/embedPage/tag/doTag?tag.id={tid}" target="dialog" warn="请选择一个标签"><span>修改</span></a></li>
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
            <#if params.sort=="name">
			<th orderfield="name" class="${params.order}">名称</th>
			<#else>
            <th orderfield="name">名称</th>
			</#if>
            <#if params.sort=="cnSpell">
			<th orderfield="cnSpell" class="${params.order}">拼音</th>
			 <#else>
            <th orderfield="cnSpell">拼音</th>
			</#if>
            <#if params.sort=="description">
			<th orderfield="description" class="${params.order}">描述</th>
			<#else>
            <th orderfield="description">描述</th>
			</#if>
             <#if params.sort=="followCount">
			<th orderfield="followCount" class="${params.order}">被关注数</th>
			<#else>
            <th orderfield="followCount">被关注数</th>
			</#if> 
            <#if params.sort=="createdDate">
			<th orderfield="createdDate" class="${params.order}">创建时间</th>
			<#else>
            <th orderfield="createdDate">创建时间</th>
			</#if>
			<#if params.sort=="updatedDate">
			<th orderfield="updatedDate" class="${params.order}">更新时间</th>
			<#else>
            <th orderfield="updatedDate">更新时间</th>
			</#if>   
			</tr>
		</thead>
		<tbody>
		<#list tags as tag>
			<tr target="tid" rel="${tag.id}">
				<td >${tag.id}</td>
				<td >${tag.name}</td>
				<td >${tag.cnSpell}</td>
				<td>${tag.description}</td>
				<td >${tag.followCount}</td>
				<td >${tag.createdDateF}</td>
				<td >${tag.updatedDateF}</td>
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
