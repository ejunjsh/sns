var settings=function()
{
	return {
		uploadImg:function(ctrl,params)
		{
			if (!lib.disableBtn($(ctrl))) {
				iframeUploader.upload($("#trueFile"),"fileForm",application.contextPath+ "/ajax/fileUpload/uploadToTemporary","fileIframe",function(json)
				{
					if (json.status == "A00004") {
						popWin.tipShow('suc', lib.ajaxCode[json.status],
								1000, null);
						lib.enableBtn($(ctrl));
						
						$("#cutPanel").fadeIn();
						$("#hideField").val(json.data);
						$("#previewMiddle").attr("src",json.data);
						$("#previewTiny").attr("src",json.data);
						$("#previewBig").attr("src",json.data);
					}
					else if (json.status == "A00001") {
						popWin.tipShow('alert',lib.ajaxCode[json.status], 1000,
								function(){
							popWin.loginShow();
						});
						lib.enableBtn($(ctrl));
					}
					else {
						popWin.tipShow('alert', lib.ajaxCode[json.status],
								1000, null);
						lib.enableBtn($(ctrl));
					}
				});
			}
		},
		changeData:function(ctrl,params){
			var $container=$("#"+params.id);
			var $visibleElm=$container.find("ul:visible");
			var index=$visibleElm.index();
			var $uls=$container.find("ul");
			if($uls.length-1>index)
			{
				$visibleElm.addClass("ghide").next().removeClass("ghide");
			}
			else
			{
				$visibleElm.addClass("ghide");
				$container.find("ul:first").removeClass("ghide");
			}
		},
		countChar:function(ctrl,params)
		{
			var str=$(ctrl).val();
			var limit=parseInt(params.limit);
			if(limit>str.length)
			$("#"+params.relatedCtrl).html("<span>"+str.length+"/"+limit+"</span>");
			else
				$("#"+params.relatedCtrl).html("<span class='tip'>"+str.length+"/"+limit+"</span>");	
		}
	};
}();

if (settings)
	lib.eventEngine(settings,null);