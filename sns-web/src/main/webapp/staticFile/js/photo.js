var photo=function(){
	return {
		addPhotoComment:function(ctrl,params)
	{
		if (!lib.disableBtn($(ctrl))) {
			kEditor.sync();
		    if($("#editor").val())
		    	{
		    	return true;
		    	}
		    else
		    	{
		    	
		    	popWin.tipShow('alert',"你必须写点什么吧", 1000,
						function(){
			    	lib.enableBtn($(ctrl));
				});
		    	return false;
		    	}
		}
		return false;
	},
	quoteComment:function(ctrl,params)
	{
		var $container=$("#comment"+params.id);
		var content=$container.find(".cmtContent").html();
		var userName= $container.find(".cmt-author").html();
		var c="<blockquote>引用<a href='"+application.contextPath+"/n/"+userName+"'>@"+userName+"</a>的话：<br />"+content+"</blockquote><br/>";

			if(kEditor)
				kEditor.insertHtml(c);
		$(document).scrollTop(1000000);
		return false;
	},
	jumpToComment:function(ctrl,params)
	{
	    if(kEditor)
				kEditor.focus();
		$(document).scrollTop(1000000);
		return false;
	},
	deleteComment : function(ctrl, params) {
		popWin.confirmShow(params.confirm, function() {
		if (!lib.disableBtn($(ctrl))) {
			$.ajax({
				url : application.contextPath+"/ajax/photo/deleteComment",
				datatype : "json",
				cache : false,
				data : {
					"comment.id" : params.id
				},
				type : "post",
				success : function(o) {
					if (o.status == "A00002") {
						popWin.tipShow('alert', lib.ajaxCode[o.status],
								1000, null);
						lib.enableBtn($(ctrl));
					}
					else if (o.status == "A00001") {
						popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
								function(){
							popWin.loginShow();
						});
						lib.enableBtn($(ctrl));
					}
					else {
						popWin.tipShow('suc', lib.ajaxCode[o.status],
								1000, null);
						lib.enableBtn($(ctrl));
						$("#comment"+params.id).fadeOut();
					}
				},
				error : function(request) {
					popWin.tipShow('alert', lib.ajaxCode["A00002"],
							1000, null);
					lib.enableBtn($(ctrl));
				}
			});
	
		}
		});
    	return false;
},
doRecommend : function(ctrl, params) {
	if (!lib.disableBtn($(ctrl))) {
		$.ajax({
			url :application.contextPath+ "/ajax/photo/doRecommend",
			datatype : "json",
			cache : false,
			data : {
				"photo.id" : params.id
			},
			type : "post",
			success : function(o) {
				if (o.status == "A00002") {
					popWin.tipShow('alert', lib.ajaxCode[o.status],
							1000, null);
					lib.enableBtn($(ctrl));
				}
				else if (o.status == "A00001") {
					popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
							function(){
						popWin.loginShow();
					});
					lib.enableBtn($(ctrl));
				}
				else if (o.status == "A00005") {
					popWin.tipShow('alert', "你已经推荐过了哦！！", 1000,
							null);
					lib.enableBtn($(ctrl));
				}
				else 
				{
					popWin.tipShow('suc', lib.ajaxCode[o.status],
							1000, null);
					lib.enableBtn($(ctrl));
					$(ctrl).html("推荐&nbsp;&nbsp;"+(parseInt(params.count)+1));
				}
			},
			error : function(request) {
				popWin.tipShow('alert', lib.ajaxCode["A00002"],
						1000, null);
				lib.enableBtn($(ctrl));
			}
		});

	}
	return false;
}
	};
}();

if (photo)
	lib.eventEngine(photo,null);