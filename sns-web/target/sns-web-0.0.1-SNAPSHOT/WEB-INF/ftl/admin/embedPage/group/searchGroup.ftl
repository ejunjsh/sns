<div class="pageHeader">
	<form id="pagerForm" onsubmit="return <#if (searchType>0)>dialogSearch(this);<#else>navTabSearch(this);</#if>" action="/admin/embedPage/group/searchGroup<#if (searchType>0)>?searchType=1</#if>" method="post">
	<input type="hidden" name="params.pageIndex" value="${params.pageIndex+1}" />
	<input type="hidden" name="params.pageSize" value="${params.pageSize}" />
	<input type="hidden" name="params.order" value="${params.order}" />
	<input type="hidden" name="params.sort" value="${params.sort}" />
	<div class="searchBar">
		<ul class="searchContent" style="height:100px;">
    <li>名称:<input type="text" value="${params.name!""}" name="params.name"/></li>
    <li>创建人人:<input type="text" value="<@s.property value="params.createdByUser.nickName" />" name="params.createdByUser.nickName"/>
    <li>原因:<input type="text" value="${params.reason!""}" name="params.reason"/></li>
    <li>描述:<input type="text"  value="${params.description!""}" name="params.description"/></li>
    <li>分类:<input type="text"  value="${params.categoryName!""}" name="params.categoryName"/></li>
    <li>发布时间:<input readonly="true" class="date" value="<#if params.createdDateMin??>${params.createdDateMin?datetime}</#if>" name="params.createdDateMin" type="text"></input></li><li><input readonly="true" class="date" value="<#if params.createdDateMax??>${params.createdDateMax?datetime}</#if>"   name="params.createdDateMax" ></input></li>
    <li>更新时间:<input readonly="true" value="<#if params.updatedDateMin??>${params.updatedDateMin?datetime}</#if>" class="date"  name="params.updatedDateMin" type="text"></input></li><li><input readonly="true" class="date"  value="<#if params.updatedDateMax??>${params.updatedDateMax?datetime}</#if>" name="params.updatedDateMax" type="text"></input></li>
    <li>状态:<@s.select id="status" cssClass="combox" name="params.status" value="params.status" list="statusMap" listKey="key" listValue="value" headerKey="0" headerValue="请选择"></@s.select></li>	
	<li>是否需要验证:
	<@s.select id="isNeedValidate" cssClass="combox" name="params.isNeedValidate" value="params.isNeedValidate" list=r"#{1:'是',2:'否'}" listKey="key" listValue="value" headerKey="0" headerValue="请选择"></@s.select>
	</li>
	<li>是否公开内容:
	<@s.select id="isOpenContent" cssClass="combox" name="params.isOpenContent" value="params.isOpenContent" list=r"#{1:'是',2:'否'}" listKey="key" listValue="value" headerKey="0" headerValue="请选择"></@s.select>
	</li>
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
			<li><a class="add" width="650" height="550" title="添加小组" mask="true" resource="group.js,fileUpload.js"  rel="addGroup" href="/admin/embedPage/group/doGroup" target="dialog"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个用户"><span>删除</span></a></li>
		     <li><a class="edit" width="650" height="550" title="修改小组" mask="true" resource="group.js,fileUpload.js"  rel="editGroup" href="/admin/embedPage/group/doGroup?group.id={gid}" target="dialog" warn="请选择一个小组"><span>修改</span></a></li>
		</ul>
	</div>
</#if>
	<div id="w_list_print">
	<table class="list" width="98%" targetType="<#if (searchType>0)>dialog<#else>navTab</#if>" asc="asc" desc="desc" layoutH="<#if (searchType>0)>171<#else>191</#if>">
		<thead>
			<tr>
			<#if params.sort=="id">
			<th orderfield="id" class="${params.order}">id</th>
			 <#else>
            <th orderfield="id">id</th>
			</#if>
            <#if params.sort=="g.name">
			<th orderfield="g.name" class="${params.order}">名称</th>
			<#else>
            <th orderfield="g.name">名称</th>
			</#if>
            <#if params.sort=="postCount">
			<th orderfield="postCount" class="${params.order}">帖子数</th>
			<#else>
            <th orderfield="postCount">帖子数</th>
			</#if>
			 <#if params.sort=="joinedUserCount">
			<th orderfield="joinedUserCount" class="${params.order}">组员数</th>
			<#else>
            <th orderfield="joinedUserCount">组员数</th>
			</#if>
			<#if params.sort=="u.nickName">
			<th orderfield="u.nickName" class="${params.order}">发布人</th>
			<#else>
            <th orderfield="u.nickName">发布人</th>
			</#if>
			<#if params.sort=="gc.name">
			<th orderfield="gc.name" class="${params.order}">发布人</th>
			<#else>
            <th orderfield="gc.name">分类</th>
			</#if>
             <#if params.sort=="g.createdDate">
			<th orderfield="g.createdDate" class="${params.order}">创建时间</th>
			<#else>
            <th orderfield="g.createdDate">创建时间</th>
			</#if> 
            <#if params.sort=="g.updatedDate">
			<th orderfield="g.updatedDate" class="${params.order}">更新时间</th>
			<#else>
            <th orderfield="g.updatedDate">更新时间</th>
			</#if>
			<#if params.sort=="g.status">
			<th orderfield="g.status" class="${params.order}">状态</th>
			<#else>
            <th orderfield="g.status">状态</th>
			</#if>
			<#if params.sort=="g.isNeedValidate">
			<th orderfield="g.isNeedValidate" class="${params.order}">是否需要验证</th>
			<#else>
            <th orderfield="g.isNeedValidate">是否需要验证</th>
			</#if>
			<#if params.sort=="g.isOpenContent">
			<th orderfield="g.isOpenContent" class="${params.order}">是否公开内容</th>
			<#else>
            <th orderfield="g.isOpenContent">是否公开内容</th>
			</#if>
			<#if (searchType>0)>
			<th>选择</th>
			</#if>  
			</tr>
		</thead>
		<tbody>
		<#list groups as group>
			<tr target="gid" rel="${group.id}">
				<td >${group.id}</td>
				<td >${group.name}</td>
				<td >${group.postCount}</td>
				<td >${group.joinedUserCount}</td>
				<td >${group.createdByUser.nickName}</td>
				<td >${group.categoryString!""}</td>
				<td >${group.createdDateF}</td>
				<td >${group.updatedDateF}</td>
				<td >${group.statusName}</td>
				<td >${group.isNeedValidateName}</td>
				<td >${group.isOpenContentName}</td>
				<#if (searchType>0)>
			    <td >
			    <a href="javascript:void(0);" class="btnSelect" data-operation="getBack" data-params="id=${group.id}&name=${group.name}&nameCtrl=groupName&valueCtrl=groupValue" title="选择">选择</a>
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
