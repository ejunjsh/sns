var doArticleCallback=function(form)
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

var doArticleTopicCallback=function(form)
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

var article=function(){
	return {
		    uploadImg:function($ctrl,params)
		    {
		    	fileUpload.openWin($ctrl,params);
		    },
			deleteImg:function($ctrl,params)
			{
				$("#previewImg").attr("src","");
		    	$("#articleIcon").val("");
			},
			imgUploadCallBack:function(json)
			{

					    if(json.status==DWZ.status.ok)
					    {
					    	$("#previewImg").attr("src",json.data);
					    	$("#articleIcon").val(json.data);
					    	
					    }
					    else
					    {
					    	DWZ.ajaxDone(json);
					    }
				
			}
	};
}();