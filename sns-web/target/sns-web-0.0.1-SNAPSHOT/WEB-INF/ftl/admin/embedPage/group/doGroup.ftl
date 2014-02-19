<div class="pageContent">
	
	<form method="post" action="${request.contextPath}/admin/embedPage/group/doGroup" class="pageForm required-validate" onsubmit="return doGroupCallback(this)">
		<div class="pageFormContent nowrap" layoutH="58">
                        <@s.if test="group!=null&&group.id>0">
                        <input name="group.id" value="<@s.property value="group.id" />" type="hidden" />
                        </@s.if>
			<dl>
				<dt>名称：</dt>
				<dd>
					<textarea  name="group.name" style="width: 99%;" cols="40" rows="2" class="required"><@s.property value="group.name" /></textarea>
				</dd>
			</dl>
			<dl>
				<dt>描述：</dt>
				<dd>
					<textarea  style="width: 99%;"  name="group.description" rows="10" cols="40"  class="required"><@s.property value="group.description" /></textarea>
				</dd>
			</dl>
			<dl>
				<dt>原因：</dt>
				<dd>
					<textarea  style="width: 99%;"  name="group.reason" rows="10" cols="40"  class="required"><@s.property value="group.reason" /></textarea>
				</dd>
			</dl>
			<dl>
				<dt>是否公开内容：</dt>
				<dd>
					<@s.select id="isOpenContent" name="group.isOpenContent" value="group.isOpenContent" list=r"#{1:'是',2:'否'}" listKey="key" listValue="value"></@s.select>
				</dd>
			</dl>
			<dl>
				<dt>是否需要验证：</dt>
				<dd>
					<@s.select id="isNeedValidate" name="group.isNeedValidate" value="group.isNeedValidate" list=r"#{1:'是',2:'否'}" listKey="key" listValue="value"></@s.select>
				</dd>
			</dl>
			<dl>
				<dt>分类：</dt>
				<dd>
					<input type="text"  suggesturl="${request.contextPath}/admin/embedPage/group/getGroupCategoryByKey" suggestfields="name" callBack="selectCategoryCallback" class="textInput" autocomplete="off">
					<@s.iterator value="group.categorys">
				     <span id="category<@s.property value="id" />" class="tag"><@s.property value="name" /><a href="javascript: void 0;" class="icon-close" title="移除分类" onclick="$(this).parent().remove();">X</a><input type="hidden" name="categorys" value="<@s.property value="id" />"></span>
				    </@s.iterator>
				</dd>
			</dl>
			<dl>
				<dt>封面：</dt>
				<dd>
					 <img id="previewImg" width="160px" height="160px" src="<@s.property value="group.cover48" />"/>
					 <input type="hidden" id="groupIcon" name="tmpUrl" value="" />
                     <div class="button"><div class="buttonContent"><button data-operation="group.deleteImg" type="button">删除</button></div></div>
				     <div class="button"><div class="buttonContent"><button data-operation="group.uploadImg" data-params="action=/admin/embedPage/fileUpload/uploadToTemporary&callBack=group.imgUploadCallBack" type="button">上传</button></div></div>
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

