var addTagCallback=function(form)
{
	 return validateCallback(form,function(json){
	    if(json.status==DWZ.status.ok)
	    {
	    	$.pdialog.closeCurrent();
	    	navTab.reload();
	    }
	    else
	    {
	    	DWZ.ajaxDone(json);
	    }
	});
};

var selectTagCallback=function(json,$ctrl){
	if(!$("#tag"+json.id)[0])
	{
	var str='<span id="tag'+json.id+ '" class="tag">'+json.name+'</a><a href="javascript: void 0;" class="icon-close" title="移除标签">X</a><input type="hidden" name="tags" value="'+json.id+'"></span>';
	$(str).appendTo($ctrl.parent()).find(".icon-close").click(function(){
	$(this).parent().remove();
	});
	$ctrl.val(""); 
	}
};