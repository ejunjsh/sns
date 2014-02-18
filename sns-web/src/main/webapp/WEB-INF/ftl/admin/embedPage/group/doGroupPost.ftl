<div class="pageContent">
	
	<form method="post" action="${request.contextPath}/admin/embedPage/group/doGroupPost" class="pageForm required-validate" onsubmit="return doGroupCallback(this)">
		<div class="pageFormContent nowrap" layoutH="58">
                        <@s.if test="groupPost!=null&&groupPost.id>0">
                        <input name="groupPost.id" value="<@s.property value="groupPost.id" />" type="hidden" />
                        </@s.if>
			<dl>
				<dt>标题：</dt>
				<dd>
					<textarea  name="groupPost.title" style="width: 99%;" cols="40" rows="2" class="required"><@s.property value="groupPost.title" /></textarea>
				</dd>
			</dl>
			<dl>
				<dt>描述：</dt>
				<dd>
					<textarea id="groupPostEditor" style="width: 99%;" class="kindEditor" name="groupPost.content" rows="10" cols="40"  class="required"><@s.property value="groupPost.content" /></textarea>
				</dd>
			</dl>
			<dl>
				<dt>小组：</dt>
				<dd>
					<input id="groupValue" value="<@s.property value="groupPost.groupId" />" name="groupPost.groupId" type="hidden"/>
					<input readonly="true" class="required" id="groupName" value="<@s.property value="groupPost.group.name" />" type="text" />
					<a class="btnLook" width="750" height="400" title="选择小组" mask="true" resource="getBack.js"  rel="choseGroup" href="${request.contextPath}/admin/embedPage/group/searchGroup?searchType=1" target="dialog"><span>查找</span></a>
				</dd>
			</dl>
			<dl>
				<dt>标签：</dt>
				<dd>
					<input type="text"  suggesturl="${request.contextPath}/admin/embedPage/tag/getTagByKey" suggestfields="name" callBack="selectTagCallback" class="textInput" autocomplete="off">
					<@s.iterator value="groupPost.tags">
				     <span id="tag<@s.property value="id" />" class="tag"><@s.property value="name" /><a href="javascript: void 0;" class="icon-close" title="移除标签" onclick="$(this).parent().remove();">X</a><input type="hidden" name="tags" value="<@s.property value="id" />"></span>
				    </@s.iterator>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

