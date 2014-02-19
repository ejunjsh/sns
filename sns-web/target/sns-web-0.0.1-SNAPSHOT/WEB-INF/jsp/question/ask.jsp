<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<s:if test="question==null">
<title>XX问答 - 新建问题</title>
</s:if>
<s:else>
<title>XX问答 - 修改问题</title>
</s:else>
<%@ include file="/WEB-INF/jsp/common/head.jsp" %>
<link  type="text/css" href="<%=request.getContextPath()%>/staticFile/css/ask1.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
<div class="grow gmt60 ask-new-page">
        <div class="main gspan-21">
            <div class="gbreadcrumb">
                <ul>
                    <li>
                        <a href="<%=request.getContextPath()%>/question/">XX问答</a>
                    </li>
                    <s:if test="question==null">
<li> 提新问题</li>
</s:if>
<s:else>
<li>修改问题</li>
</s:else>
                </ul>
            </div>
            
    <div class="">
       <form id="editor" class="gform" action="<%=request.getContextPath()%>/question/ask" method="POST">
        <s:if test="question!=null&&question.id>0">
                        <input name="question.id" value="<s:property value="question.id" />" type="hidden" />
                        </s:if>
    <label for="askTitle">问题</label>
    
<div class="gform-box gclear">
            <textarea  name="question.title" class="gttxt" autofocus="" autocomplete="off" style="resize: none; overflow: hidden; height: 70px;"id="editorTitle"><s:property value="question.title" /></textarea>
</div>

    <label for="editorContent">描述</label>
            <div class="gform-box gclear">
               <textarea class="gttxt"  name="question.content" id="editorContent"><s:property value="question.content" /></textarea>
            </div>
            <label>标签</label>
            <div class="gform-box gclear">
                    <p id="tagContent" class="post-tags tags tags-edit">
                     <s:iterator value="question.tags">
                    <span id= tag<s:property value="id" /> class="tag"><a href="<%=request.getContextPath()%>/tag/<s:property value="id" />"><s:property value="name" /></a><a  title="移除标签" class="icon-close" onclick="$(this).parent().remove();" href="javascript: void 0;">X</a><input type="hidden" value="<s:property value="id" />" name="tags"></span>
                    </s:iterator>
                    </p>   
             <input type="text" class="gstxt" id="tagAdd" autocomplete="off">
             <p class="editor-tags-i">给问题打上正确的标签有助于更快获得解答</p>

            </div>
            <div class="ask-ft">
                <input type="submit" id="submitBtn" value="发布" class="gbtn-submit">
                <a class="cancel-new" href="<%=request.getContextPath()%>/ask/newest/">取消</a>
            </div>
        </form>
    </div>

        </div>
        
    <div class="side gspan-10 gprefix-1">
        <div class="side-summary">
          
        </div>
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

