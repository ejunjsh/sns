var popWinUploader=function(){
	$(function(){
		popWin.initialCustom({id:"popWinUploader",title:"上传",content:"<div style='line-height:30px;'><input type='file' name='file' class='gstxt' style='width:200px' /></div><div style='margin-top:10px;float:right;'><a href='javascript:void(0)'  class='gbtn-primary'>确定</a><div/>",width:"300"});
		   
	});
	  return {
	   show:function(action,sucCallback)
	   {
		   popWin.customShow(function($win){
				var $file= $win.find("input");
				var $btn=$win.find(".gbtn-primary");
				$btn.click(function(){
					if (!lib.disableBtn($btn)) {
						iframeUploader.upload($file, "fileForm",action,"fileIframe",function(json){
							if (json.status == "A00004") {
								popWin.tipShow('suc', lib.ajaxCode[json.status],
										1000, null);
								lib.enableBtn($btn);
								popWin.customClose("popWinUploader");
								if(sucCallback)
								{
									sucCallback(json);
								}
							}
							else if (json.status == "A00001") {
								popWin.tipShow('alert',lib.ajaxCode[json.status], 1000,
										function(){
									popWin.customClose("popWinUploader");
									popWin.loginShow();
								});
								lib.enableBtn($btn);
							}
							else {
								popWin.tipShow('alert', lib.ajaxCode[json.status],
										1000, null);
								lib.enableBtn($btn);
							}
						});
					}
					return false;
				});
		   },"popWinUploader");
	   }
   };
}();