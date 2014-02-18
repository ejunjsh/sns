var doQuestionCallback=function(form)
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