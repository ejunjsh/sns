<div class="pageContent">
	
	<form method="post" action="${request.contextPath}/admin/embedPage/notice/doSysNotice" class="pageForm required-validate" onsubmit="return doNoticeCallback(this)">
		<div class="pageFormContent nowrap" layoutH="58">
                        <@s.if test="notice!=null&&notice.id>0">
                        <input name="notice.id" value="<@s.property value="notice.id" />" type="hidden" />
                        </@s.if>
			<dl>
				<dt>标题：</dt>
				<dd>
					<textarea name="notice.title" style="width: 99%;" cols="40" rows="2" class="required"><@s.property value="notice.title" /></textarea>
				</dd>
			</dl>
			<dl>
				<dt>内容：</dt>
				<dd>
					<textarea id="noticeEditor" style="width: 99%;" class="kindEditor" name="notice.content" rows="10" cols="40"  class="required"><@s.property value="notice.content" /></textarea>
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

