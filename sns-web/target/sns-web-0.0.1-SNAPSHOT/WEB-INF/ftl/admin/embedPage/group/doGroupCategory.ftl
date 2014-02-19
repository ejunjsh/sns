<div class="pageContent">
	<form method="post" action="${request.contextPath}/admin/embedPage/group/doGroupCategory" class="pageForm required-validate" onsubmit="return doGroupCallback(this);">
		  <@s.if test="groupCategory!=null&&groupCategory.id>0">
                        <input name="groupCategory.id" value="<@s.property value="groupCategory.id" />" type="hidden" />
                        </@s.if>
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>名称：</label>
				<input type="text" name="groupCategory.name" value="<@s.property value="groupCategory.name" />" class="required"/>
			</div>
			<div class="unit">
				<label>描述：</label>
				<textarea name="groupCategory.description" cols="30" rows="5" class="textInput"><@s.property value="groupCategory.description"/></textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>

