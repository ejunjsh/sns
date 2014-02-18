<div class="pageContent">
	<form method="post" action="/admin/embedPage/tag/doTag" class="pageForm required-validate" onsubmit="return addTagCallback(this);">
		  <@s.if test="tag!=null&&tag.id>0">
                        <input name="tag.id" value="<@s.property value="tag.id" />" type="hidden" />
                        </@s.if>
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>名称：</label>
				<input type="text" name="tag.name" value="<@s.property value="tag.name" />" class="required"/>
			</div>
			<div class="unit">
				<label>拼音：</label>
				<input type="text" name="tag.cnSpell"  value="<@s.property value="tag.cnSpell"/>" class="required"/>
			</div>
			<div class="unit">
				<label>描述：</label>
				<textarea name="tag.description" cols="30" rows="5" class="textInput"><@s.property value="tag.description"/></textarea>
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

