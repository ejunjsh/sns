<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${request.contextPath}/admin/embedPage/user/searchUser" method="post">
	<input type="hidden" name="params.pageIndex" value="${params.pageIndex+1}" />
	<input type="hidden" name="params.pageSize" value="${params.pageSize}" />
	<input type="hidden" name="params.order" value="${params.order}" />
	<input type="hidden" name="params.sort" value="${params.sort}" />
	<div class="searchBar">
		<ul class="searchContent" style="height:75px;">
			<li>昵称:<input value="${params.nickName!""}" type="text" name="params.nickName"/></li>
    <li>邮箱:<input value="${params.email!""}" type="text" name="params.email"/></li>
    <li>ip:<input value="${params.ip!""}" type="text" name="params.ip"/></li>
    <li>注册时间:<input readonly="true" class="date" value="<#if params.regesiterDateMin??>${params.regesiterDateMin?datetime}</#if>"  id="regesiterDateMin" name="params.regesiterDateMin" type="text"></input></li><li><input readonly="true" class="date" value="<#if params.regesiterDateMax??>${params.regesiterDateMax?datetime}</#if>"  id="regesiterDateMax" name="params.regesiterDateMax" ></input></li>
    <li>上次登录时间:<input readonly="true" value="<#if params.lastLoginDateMin??>${params.lastLoginDateMin?datetime}</#if>" class="date"  id="lastLoginDateMin" name="params.lastLoginDateMin" type="text"></input></li><li><input readonly="true" class="date"  id="lastLoginDateMax" value="<#if params.lastLoginDateMax??>${params.lastLoginDateMax?datetime}</#if>" name="params.lastLoginDateMax" type="text"></input></li>
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
			<li><a class="add" href="demo_page4.html" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个用户"><span>删除</span></a></li>
			<li><a class="edit" href="demo_page4.html?uid={sid_user}" target="navTab" warn="请选择一个用户"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<li><a class="icon" href="javascript:$.printBox('w_list_print')"><span>打印</span></a></li>
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
            <#if params.sort=="email">
			<th orderfield="email" class="${params.order}">邮箱</th>
			<#else>
            <th orderfield="email">邮箱</th>
			</#if>
            <#if params.sort=="nickName">
			<th orderfield="nickName" class="${params.order}">昵称</th>
			 <#else>
            <th orderfield="nickName">昵称</th>
			</#if>
            <#if params.sort=="ip">
			<th orderfield="ip" class="${params.order}">ip</th>
			<#else>
            <th orderfield="ip">ip</th>
			</#if>
             <#if params.sort=="regesiterDate">
			<th orderfield="regesiterDate" class="${params.order}">注册时间</th>
			<#else>
            <th orderfield="regesiterDate">注册时间</th>
			</#if> 
            <#if params.sort=="lastLoginDate">
			<th orderfield="lastLoginDate" class="${params.order}">上次登录时间</th>
			<#else>
            <th orderfield="lastLoginDate">上次登录时间</th>
			</#if>  
             <#if params.sort=="status">
			<th orderfield="status" class="${params.order}">状态</th>
			  <#else>
            <th orderfield="status">状态</th>
			</#if>
			</tr>
		</thead>
		<tbody>
		<#list users as user>
			<tr target="sid_user" rel="${user.id}">
				<td >${user.id}</td>
				<td >${user.email}</td>
				<td >${user.nickName}</td>
				<td>${user.ip}</td>
				<td >${user.regesiterDateF}</td>
				<td >${user.lastLoginDateF}</td>
				<td >${user.statusName}</td>
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
