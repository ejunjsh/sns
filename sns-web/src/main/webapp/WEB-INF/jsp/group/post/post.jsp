<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>XX小组- 发新帖子</title>
<%@ include file="/WEB-INF/jsp/common/head.jsp" %>
<link  type="text/css" href="<%=request.getContextPath()%>/staticFile/css/group1.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
<div class="wrap grow gmt60 post-edit-page">
    <div class="gspan-22 main">
	   <div class="gbreadcrumb">
                <ul>
                    <li>
                        <a href="<%=request.getContextPath()%>/group/all">小组</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/group/<s:property value="group.id" />"><s:property value="group.name" /></a>
                    </li>
                    <s:if test="groupPost!=null&&groupPost.id>0">
					<li>编辑帖子</li>
					</s:if>
					<s:else>
					<li>发新帖子</li>
					</s:else>
                </ul>
        </div>
		<div>
		<form id="editor" class="gform" method="post" action="<%=request.getContextPath()%>/group/<s:property value="group.id" />/post">
				<s:if test="groupPost!=null&&groupPost.id>0">
                        <input name="groupPost.id" value="<s:property value="groupPost.id" />" type="hidden" />
        </s:if>
		<label for="title">标题</label>
    
<div class="gform-box gclear">
<input id="editorTitle" class="gstxt" type="text" name="groupPost.title" autocomplete="off" autofocus="true" value="<s:property value="groupPost.title" />">
			</div>

    <label for="editorContent">内容</label>
            <div class="gform-box gclear">
               <textarea class="gttxt"  name="groupPost.content" id="editorContent"><s:property value="groupPost.content" /></textarea>
            </div>
            <label>标签</label>
            <div class="gform-box gclear tag-box">
                    <p id="tagContent" class="post-tags tags tags-edit">
                     <s:iterator value="groupPost.tags">
                    <span id= tag<s:property value="id" /> class="tag"><a href="/tag/<s:property value="id" />"><s:property value="name" /></a><a  title="移除标签" class="icon-close" onclick="$(this).parent().remove();" href="javascript: void 0;">X</a><input type="hidden" value="<s:property value="id" />" name="tags"></span>
                    </s:iterator>
                    </p>   
             <input type="text" class="gstxt" id="tagAdd" autocomplete="off">
            </div>
            <div class="gform-box gclear">
                <input type="submit" id="submitBtn" value="发布" class="gbtn-submit">
            </div>
		</form>
		</div>
	</div>
	<div class="gspan-8 side gprefix-1">
        <a href="<%=request.getContextPath()%>/group/<s:property value="group.id" />">返回 &gt;<s:property value="group.name" /></a>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/common/bottom.jsp" %>

</div>


<script type="text/javascript" src="<%=request.getContextPath()%>/staticFile/js/kindeditor-min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/staticFile/js/zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	KindEditor.ready(function(K) {
		K.create('#editorContent', {
			uploadJson:'<%=request.getContextPath()%>/ajax/keImageUpload/upload',
			cssPath : ['<%=request.getContextPath()%>/staticFile/css/kind-code.css'],
			items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link','code']
		});

	});
	
	autoComplete.bind($("#tagAdd"),function(id,name){
		if(!$("#tag"+id)[0])
	    {
		var str='<span id="tag'+id+ '" class="tag"><a target="_blank" href="<%=request.getContextPath()%>/tag/'+id+'/">'+name+'</a><a href="javascript: void 0;" class="icon-close" title="移除标签">X</a><input type="hidden" name="tags" value="'+id+'"></span>';
	    var tagContent=$("#tagContent");
		$(str).appendTo(tagContent).find(".icon-close").click(function(){
	    	$(this).parent().remove();
	    });
		tagContent.addClass("tags-edit");
		$("#tagAdd").val("");
	    }
	},"tag/getTagByKey",'<li id="{id}" val="{name}" class="{selected}"><div class="pt-txt tag-list-item"><h4  class="tag-list-name">{name}</h4><p>{followCount}人关注</p></div></li>');
	
	$("#submitBtn").click(function(){
		if(!lib.disableBtn($(this)))
	    {
			if(!$.trim($("#editorTitle").val()))
			{
				 popWin.tipShow('alert', "标题不能为空", 1000,null);
				   lib.enableBtn($(this));
				   return false;
			}	
		   else if($("#editorTitle").val().replace(/[^\x00-\xff]/g, 'xx').length >100)
		   {
			   popWin.tipShow('alert', "标题不能大于100个字", 1000,null);
			   lib.enableBtn($(this));
			   return false;
		   }
		   else if(!$('input[name="tags"]')[0])
		   {
			   popWin.tipShow('alert', "请选择标签", 1000,null);
			   lib.enableBtn($(this));
			   return false;
		   }
		   else
		   {
			   return true;
		   }
		  
	    }
		return false;
	});
});
</script>
</body>
</html>

