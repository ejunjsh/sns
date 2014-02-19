
			
			
var fileUpload=function(){
	var $curIframe;
	var appendToBody = function(obj, str) {
		if (typeof str == "string") {
			if (obj == null || obj[0] == null) {
				$("body").append(str);
				return true;
			}
		}
		return false;
	};
	var fileCallBack=null;
	var fileAction=null;
	var $document=$(document);
	return{
		openWin:function($ctrl,params){
			fileCallBack=eval(params.callBack);
			fileAction=params.action;
			var options={};
			options.max =false;
			options.mask =true;
			options.maxable =false;
			options.minable = false;
			options.fresh = true;
			options.resizable =false;
			options.drawable = true;
			options.width=280;
			options.height=150;
			$.pdialog.open(application.contextPath+"/admin/embedPage/fileUpload/fileUploadPage", "fileUpload", "上传", options);
		},
		upload:function($ctrl,params){
			var $fileCtrl=$("#fileCtrl");
			var formId="fileUploadForm";
			var formAction=fileAction;
			var iframeId="fileUploadIframe";
			var $fileContainer=$fileCtrl.parent();
	        var $form = $fileCtrl.wrap("<form/>").parent().css({
	            position: "absolute",
	            top: "-1200px",
	            left: "-1200px"
	        }).attr({
	            id: formId,
	            name: formId,
	            target: iframeId,
	            enctype: "multipart/form-data",
	            encoding: "multipart/form-data",
	            action: formAction,
	            method: "POST"
	        }).appendTo("body");
			var iframeStr = "<iframe name=\"" + iframeId + "\" id=\"" + iframeId
					+ "\" style=\"display:none\"></iframe>";
			$curIframe = $("#" + iframeId);
			if (appendToBody($curIframe, iframeStr)) {
				$curIframe = $("#" + iframeId);
				$curIframe.bind("load", function() {
					$document.trigger("ajaxStop");
					$.pdialog.closeCurrent();
					var msg = $(this).contents().text();
					var json={};
					try
					{
						json = $.parseJSON(msg);
					}
					catch(b)
					{
						json={status:DWZ.status.error,message:"上传失败"};
					}
					fileCallBack ? fileCallBack(json) : 1+2;
				});
			}
			$document.trigger("ajaxStart");
			$form.submit();
			$form.find("input:not(:file)").remove();
			$fileCtrl.unwrap();
			$fileCtrl.appendTo($fileContainer);
		}
	};
}();