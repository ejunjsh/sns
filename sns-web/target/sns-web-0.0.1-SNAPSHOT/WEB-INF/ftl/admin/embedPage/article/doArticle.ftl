<div class="pageContent">
	
	<form method="post" action="/admin/embedPage/article/doArticle" class="pageForm required-validate" onsubmit="return doArticleCallback(this)">
		<div class="pageFormContent nowrap" layoutH="58">
                        <@s.if test="article!=null&&article.id>0">
                        <input name="article.id" value="<@s.property value="article.id" />" type="hidden" />
                        </@s.if>
			<dl>
				<dt>标题：</dt>
				<dd>
					<textarea  name="article.title" style="width: 99%;" cols="40" rows="2" class="required"><@s.property value="article.title" /></textarea>
				</dd>
			</dl>
			<dl>
				<dt>描述：</dt>
				<dd>
					<textarea id="articleEditor" style="width: 99%;" class="kindEditor" name="article.content" rows="10" cols="40"  class="required"><@s.property value="article.content" /></textarea>
				</dd>
			</dl>
			<dl>
				<dt>主题：</dt>
				<dd>
					<input id="topicValue" value="<@s.property value="article.articleTopicId" />" name="article.articleTopicId" type="hidden"/>
					<input readonly="true" class="required" id="topicName" value="<@s.property value="article.articleTopic.name" />" type="text" />
					<a class="btnLook" width="650" height="400" title="选择主题" mask="true" resource="getBack.js"  rel="choseTopic" href="/admin/embedPage/article/searchArticleTopic?searchType=1" target="dialog"><span>查找</span></a>
				</dd>
			</dl>
			<dl>
				<dt>标签：</dt>
				<dd>
					<input type="text"  suggesturl="/admin/embedPage/tag/getTagByKey" suggestfields="name" callBack="selectTagCallback" class="textInput" autocomplete="off">
					<@s.iterator value="article.tags">
				     <span id="tag<@s.property value="id" />" class="tag"><@s.property value="name" /><a href="javascript: void 0;" class="icon-close" title="移除标签" onclick="$(this).parent().remove();">X</a><input type="hidden" name="tags" value="<@s.property value="id" />"></span>
				    </@s.iterator>
				</dd>
			</dl>
			<dl>
				<dt>封面：</dt>
				<dd>
					 <img id="previewImg" width="160px" height="160px" src="<@s.property value="article.cover160" />"/>
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

