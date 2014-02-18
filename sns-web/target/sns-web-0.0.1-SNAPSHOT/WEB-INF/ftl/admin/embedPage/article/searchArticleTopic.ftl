<div class="pageHeader">
	<form id="pagerForm" onsubmit="return <#if (searchType>0)>dialogSearch(this);<#else>navTabSearch(this);</#if>" action="/admin/embedPage/article/searchArticleTopic<#if (searchType>0)>?searchType=1</#if>" method="post">
	<input type="hidden" name="params.pageIndex" value="${params.pageIndex+1}" />
	<input type="hidden" name="params.pageSize" value="${params.pageSize}" />
	<input type="hidden" name="params.order" value="${params.order}" />
	<input type="hidden" name="params.sort" value="${params.sort}" />
	<div class="searchBar">
		<ul class="searchContent" style="height:75px;">
    <li>名称:<input type="text" value="${params.name!""}" name="params.name"/></li>
    <li>描述:<input type="text"  value="${params.description!""}" name="params.description"/></li>
    <li>发布时间:<input readonly="true" class="date" value="<#if params.createdDateMin??>${params.createdDateMin?datetime}</#if>" name="params.createdDateMin" type="text"></input></li><li><input readonly="true" class="date" value="<#if params.createdDateMax??>${params.createdDateMax?datetime}</#if>"   name="params.createdDateMax" ></input></li>
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
<#if (searchType!=1)>
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" width="650" height="550" title="添加主题" mask="true" resource="article.js,fileUpload.js"  rel="addArticleTopic" href="/admin/embedPage/article/doArticleTopic" target="dialog"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个用户"><span>删除</span></a></li>
		     <li><a class="edit" width="650" height="550" title="修改主题" mask="true" resource="article.js,fileUpload.js"  rel="editArticleTopic" href="/admin/embedPage/article/doArticleTopic?topic.id={tid}" target="dialog" warn="请选择一个主题"><span>修改</span></a></li>
		</ul>
	</div>
</#if>
	<div id="w_list_print">
	<table class="list" width="98%" targetType="<#if (searchType>0)>dialog<#else>navTab</#if>" asc="asc" desc="desc" layoutH="<#if (searchType>0)>146<#else>166</#if>">
		<thead>
			<tr>
			<#if params.sort=="id">
			<th orderfield="id" class="${params.order}">id</th>
			 <#else>
            <th orderfield="id">id</th>
			</#if>
            <#if params.sort=="t.name">
			<th orderfield="t.name" class="${params.order}">名称</th>
			<#else>
            <th orderfield="t.name">名称</th>
			</#if>
            <th>描述</th>
            <#if params.sort=="t.articleCount">
			<th orderfield="t.articleCount" class="${params.order}">文章数</th>
			<#else>
            <th orderfield="t.articleCount">文章数</th>
			</#if>
             <#if params.sort=="t.createdDate">
			<th orderfield="t.createdDate" class="${params.order}">发布时间</th>
			<#else>
            <th orderfield="t.createdDate">发布时间</th>
			</#if> 
            <#if params.sort=="t.updatedDate">
			<th orderfield="t.updatedDate" class="${params.order}">更新时间</th>
			<#else>
            <th orderfield="t.updatedDate">更新时间</th>
			</#if>
			<#if (searchType>0)>
			<th>选择</th>
			</#if>  
			</tr>
		</thead>
		<tbody>
		<#list topics as topic>
			<tr target="tid" rel="${topic.id}">
				<td >${topic.id}</td>
				<td >${topic.name}</td>
				<td ><#if topic.description??>${topic.description}</#if></td>
				<td >${topic.articleCount}</td>
				<td >${topic.createdDateF}</td>
				<td >${topic.updatedDateF}</td>
				<#if (searchType>0)>
			    <td >
			    <a href="javascript:void(0);" class="btnSelect" data-operation="getBack" data-params="id=${topic.id}&name=${topic.name}&nameCtrl=topicName&valueCtrl=topicValue" title="选择">选择</a>
			    </td>
			    </#if>  
			</tr>
			</#list>
		</tbody>
	</table>
	</div>
	<div class="panelBar" >
		<div class="pages">
			<span>显示</span>
			<#if (searchType>0)>
			<@s.select list="{'20','50','100','200'}" value="params.pageSize"  name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})"></@s.select>
			<#else>
			<@s.select list="{'20','50','100','200'}" value="params.pageSize"  name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})"></@s.select>
			</#if>
			<span>条，共${params.total}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${params.total}" numPerPage="${params.pageSize}" pageNumShown="10" currentPage="${params.pageIndex+1}"></div>
	</div>
</div>
