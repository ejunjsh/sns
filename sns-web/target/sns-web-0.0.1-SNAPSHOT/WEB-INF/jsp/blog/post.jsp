<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<s:if test="blog==null">
<title>XX日志 - 写日志</title>
</s:if>
<s:else>
<title>XX日志 - 改日志</title>
</s:else>
<%@ include file="/WEB-INF/jsp/common/head.jsp" %>
<link  type="text/css" href="/staticFile/css/blog1.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
<div class="wrap grow gmt60 edit-blog-page">
    <div class="gspan-22 main">
	   <div class="gbreadcrumb">
                <ul>
                    <li>
                        <a href="/blog/">XX日志</a>
                    </li>
                    <s:if test="blog==null">
					<li>写日志</li>
					</s:if>
					<s:else>
					<li>改日志</li>
					</s:else>
                </ul>
        </div>
		<div>
		<form id="editor" class="gform" method="post" action="/blog/post">
		<s:if test="blog!=null&&blog.id>0">
                        <input name="blog.id" value="<s:property value="blog.id" />" type="hidden" />
        </s:if>
		<label for="title">标题</label>
    
<div class="gform-box gclear">
<input id="editorTitle" class="gstxt" type="text" value="<s:property value="blog.title" />"  name="blog.title" autocomplete="off" autofocus="true">
			</div>

    <label for="editorContent">内容</label>
            <div class="gform-box gclear">
               <textarea class="gttxt"  name="blog.content" id="editorContent"><s:property value="blog.content" /></textarea>
            </div>
			<label>分类</label>
			<div class="gform-box gclear">
			<s:select id="cateList" name="blog.blogCategoryId" cssClass="s_txt" list="categories" listKey="id" listValue="name" value="blog.blogCategoryId" headerKey="0" headerValue="未分类"></s:select>
            <a id="addCate" href="javascript:void(0)">添加分类</a>
			</div>
            <label>标签</label>
            <div class="gform-box gclear tag-box">
                    <p id="tagContent" class="post-tags tags tags-edit">
                     <s:iterator value="blog.tags">
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
	<s:if test="curUser!=null">
                <a href="/i/<s:property value="curUser.id"/>/">返回我的主页 &gt;</a>
    </s:if>
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
						'insertunorderedlist', '|', 'emoticons', 'image','link','code']
		});

	});
	
	autoComplete.bind($("#tagAdd"),function(id,name){
		if(!$("#tag"+id)[0])
	    {
		var str='<span id="tag'+id+ '" class="tag"><a target="_blank" href="/tag/'+id+'/">'+name+'</a><a href="javascript: void 0;" class="icon-close" title="移除标签">X</a><input type="hidden" name="tags" value="'+id+'"></span>';
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
	var $cateList=$("#cateList");
	popWin.initialCustom({title:"添加分类",content:"<div style='line-height:30px;'>分类：<input type='text' class='gstxt' style='width:200px' /></div><div style='margin-top:10px;float:right;'><a href='javascript:void(0)'  class='gbtn-primary'>确定</a><div/>",width:"300"},function($win){
		var $txt= $win.find("input");
		var $ctrl=$win.find(".gbtn-primary");
		$ctrl.click(function(){
			if(!$txt.val())
			{
				popWin.tipShow('alert', '你总要写点什么吧',
						1000, null);
				return false;
			}
			if(lib.getTxtLen($txt)>50)
			{
			popWin.tipShow('alert',"只能输入50个字符的哦",
					1000, null);
			return false;
			}
			if (!lib.disableBtn($ctrl)) {
				$.ajax({
					url : "/ajax/blog/addCategory",
					datatype : "json",
					cache : false,
					data : {
	                      "category.name":$txt.val()
					},
					type : "post",
					success : function(o) {
						if (o.status == "A00002") {
							popWin.tipShow('alert', lib.ajaxCode[o.status],
									1000, null);
							lib.enableBtn($ctrl);
						}
						else if (o.status == "A00001") {
							popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
									function(){
								popWin.customClose();
								popWin.loginShow();
							});
							lib.enableBtn($ctrl);
						}
						else if (o.status == "A00005") {
							popWin.tipShow('alert', "不能创建同名的分类哦", 1000,
									null);
							lib.enableBtn($ctrl);
						} 
						else {
							popWin.tipShow('suc', lib.ajaxCode[o.status],
									1000, null);
							lib.enableBtn($ctrl);
							$txt.val("");
							$cateList.append('<option value="'+o.data.id+'">'+o.data.name+'</option>');
							$cateList[0].value=o.data.id;
							popWin.customClose();
						}	
					},
					error : function(request) {
						popWin.tipShow('alert', lib.ajaxCode["A00002"],
								1000, null);
						lib.enableBtn($ctrl);
					}
				});

			}
			return false;
		});
		
	});
	$("#addCate").click(function(){
		popWin.customShow();
		return false;
	});
});
</script>
</body>
</html>

