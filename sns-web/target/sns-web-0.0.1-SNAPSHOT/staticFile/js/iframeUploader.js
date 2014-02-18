var iframeUploader = function() {
	var $curIframe;
	var iframeSubmit = function($fileCtrl, formId, formAction, iframeId, callBack) {
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
		if (lib.appendToBody($curIframe, iframeStr)) {
			$curIframe = $("#" + iframeId);
			$curIframe.bind("load", function() {
				var msg = $(this).contents().text();
				var json={};
				try
				{
					json = $.parseJSON(msg);
				}
				catch(b)
				{
					json={status:"A00002",data:""};
				}
				callBack ? callBack(json) : 1+2;
			});
		}
		$form.submit();
		$form.find("input:not(:file)").remove();
		$fileCtrl.unwrap();
		$fileCtrl.appendTo($fileContainer);
	};
	return {
		upload : function(file, formId, formAction, iframeId, callBack) {
			iframeSubmit(file, formId, formAction, iframeId, callBack);
		}
	};
}();