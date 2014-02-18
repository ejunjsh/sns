var doGroupCallback=function(form)
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

var selectCategoryCallback=function(json,$ctrl){
	if(!$("#category"+json.id)[0])
	{
	var str='<span id="category'+json.id+ '" class="tag">'+json.name+'</a><a href="javascript: void 0;" class="icon-close" title="移除分类">X</a><input type="hidden" name="categorys" value="'+json.id+'"></span>';
	$(str).appendTo($ctrl.parent()).find(".icon-close").click(function(){
	$(this).parent().remove();
	});
	$ctrl.val(""); 
	}
};

var group=function(){
	return {
		    uploadImg:function($ctrl,params)
		    {
		    	fileUpload.openWin($ctrl,params);
		    },
			deleteImg:function($ctrl,params)
			{
				$("#previewImg").attr("src","");
		    	$("#groupIcon").val("");
			},
			imgUploadCallBack:function(json)
			{

					    if(json.status==DWZ.status.ok)
					    {
					    	$("#previewImg").attr("src",json.data);
					    	$("#groupIcon").val(json.data);
					    	
					    }
					    else
					    {
					    	DWZ.ajaxDone(json);
					    }
				
			}
	};
}();