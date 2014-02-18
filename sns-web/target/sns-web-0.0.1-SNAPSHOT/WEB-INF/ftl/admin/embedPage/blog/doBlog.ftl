<div class="pageContent">
	
	<form method="post" action="/admin/embedPage/blog/doBlog" class="pageForm required-validate" onsubmit="return doBlogCallback(this)">
		<div class="pageFormContent nowrap" layoutH="58">
                        <@s.if test="blog!=null&&blog.id>0">
                        <input name="blog.id" value="<@s.property value="blog.id" />" type="hidden" />
                        </@s.if>
			<dl>
				<dt>标题：</dt>
				<dd>
					<textarea  name="blog.title" style="width: 99%;" cols="40" rows="2" class="required"><@s.property value="blog.title" /></textarea>
				</dd>
			</dl>
			<dl>
				<dt>描述：</dt>
				<dd>
					<textarea id="blogEditor" style="width: 99%;" class="kindEditor" name="blog.content" rows="10" cols="40"  class="required"><@s.property value="blog.content" /></textarea>
				</dd>
			</dl>
			
			<dl>
				<dt>标签：</dt>
				<dd>
					<input type="text"  suggesturl="/admin/embedPage/tag/getTagByKey" suggestfields="name" callBack="selectTagCallback" class="textInput" autocomplete="off">
					<@s.iterator value="blog.tags">
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

