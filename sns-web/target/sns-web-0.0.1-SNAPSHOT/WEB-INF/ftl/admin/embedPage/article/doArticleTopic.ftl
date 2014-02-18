<div class="pageContent">
	
	<form method="post" action="/admin/embedPage/article/doArticleTopic" class="pageForm required-validate" onsubmit="return doArticleTopicCallback(this)">
		<div class="pageFormContent nowrap" layoutH="58">
                        <@s.if test="topic!=null&&topic.id>0">
                        <input name="topic.id" value="<@s.property value="topic.id" />" type="hidden" />
                        </@s.if>
			<dl>
				<dt>名称：</dt>
				<dd>
					<textarea  name="topic.name" style="width: 99%;" cols="40" rows="2" class="required"><@s.property value="topic.name" /></textarea>
				</dd>
			</dl>
			<dl>
				<dt>描述：</dt>
				<dd>
					<textarea  style="width: 99%;"  name="topic.description" rows="10" cols="40"  class="required"><@s.property value="topic.description" /></textarea>
				</dd>
			</dl>
			<dl>
				<dt>封面：</dt>
				<dd>
					 <img id="previewImg" width="160px" height="160px" src="<@s.property value="topic.cover160" />"/>
					 <input type="hidden" id="articleIcon" name="tmpUrl" value="" />
                     <div class="button"><div class="buttonContent"><button data-operation="article.deleteImg" type="button">删除</button></div></div>
				     <div class="button"><div class="buttonContent"><button data-operation="article.uploadImg" data-params="action=/admin/embedPage/fileUpload/uploadToTemporary&callBack=article.imgUploadCallBack" type="button">上传</button></div></div>
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

