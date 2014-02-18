var group=function()
{
	return {
		uploadImg:function(ctrl,params)
		{
			if (!lib.disableBtn($(ctrl))) {
				iframeUploader.upload($("#trueFile"),"fileForm","/ajax/fileUpload/uploadToTemporary","fileIframe",function(json)
				{
					if (json.status == "A00004") {
						popWin.tipShow('suc', lib.ajaxCode[json.status],
								1000, null);
						lib.enableBtn($(ctrl));
						$("#groupIcon").attr("src",json.data);
						$("#icon").val(json.data);
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
		addGroupPostComment:function(ctrl,params)
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
			var c="<blockquote>引用<a href='/n/"+userName+"'>@"+userName+"</a>的话：<br />"+content+"</blockquote><br/>";

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
					url : "/ajax/group/post/deleteComment",
					datatype : "json",
					cache : false,
					data : {
						"comment.id" : params.id
					},
					type : "post",
					success : function(o) {
						if (o.status == "A00004") {
							popWin.tipShow('suc', lib.ajaxCode[o.status],
									1000, null);
							lib.enableBtn($(ctrl));
							$("#comment"+params.id).fadeOut();
						}
						else if (o.status == "A00001") {
							popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
									function(){
								popWin.loginShow();
							});
							lib.enableBtn($(ctrl));
						}
						else {
							popWin.tipShow('alert', lib.ajaxCode[o.status],
									1000, null);
							lib.enableBtn($(ctrl));
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
				url : "/ajax/group/post/doRecommend",
				datatype : "json",
				cache : false,
				data : {
					"groupPost.id" : params.id
				},
				type : "post",
				success : function(o) {
					if (o.status == "A00004") {
						popWin.tipShow('suc', lib.ajaxCode[o.status],
								1000, null);
						lib.enableBtn($(ctrl));
						$(ctrl).html("推荐&nbsp;&nbsp;"+(parseInt(params.count)+1));
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
						popWin.tipShow('alert', lib.ajaxCode[o.status],
								1000, null);
						lib.enableBtn($(ctrl));
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
	},
	doJoin : function(ctrl, params) {
		if (!lib.disableBtn($(ctrl))) {
			$.ajax({
				url : "/ajax/group/doJoin",
				datatype : "json",
				cache : false,
				data : {
					"group.id" : params.id
				},
				type : "post",
				success : function(o) {
					if (o.status == "A00004") {
						popWin.tipShow('suc', lib.ajaxCode[o.status],
								1000, function(){
							lib.enableBtn($(ctrl));
							$(ctrl).unbind();
							$(ctrl).attr("data-operation","doQuit");
							$(ctrl).attr("data-params","id="+params.id+"&className="+$(ctrl).attr("class")+"&txt="+$(ctrl).text());
							$(ctrl).attr("class",params.className?params.className:"");
							$(ctrl).text(params.txt);
							lib.eventEngine(group,$(ctrl).parent());
						});

					}
					else if (o.status == "A00001") {
						popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
								function(){
							popWin.loginShow();
						});
						lib.enableBtn($(ctrl));
					}
					else if (o.status == "A00005") {
						popWin.tipShow('alert', "你已经加入了哦！！", 1000,
								null);
						lib.enableBtn($(ctrl));
					}
					else 
					{
						popWin.tipShow('alert', lib.ajaxCode[o.status],
								1000, null);
						lib.enableBtn($(ctrl));
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
	},
	doQuit : function(ctrl, params) {
		if (!lib.disableBtn($(ctrl))) {
			$.ajax({
				url : "/ajax/group/doQuit",
				datatype : "json",
				cache : false,
				data : {
					"group.id" : params.id
				},
				type : "post",
				success : function(o) {
					if (o.status == "A00004") {
						popWin.tipShow('suc', lib.ajaxCode[o.status],
								1000, function(){
							lib.enableBtn($(ctrl));
							$(ctrl).unbind();
							$(ctrl).attr("data-operation","doJoin");
							$(ctrl).attr("data-params","id="+params.id+"&className="+$(ctrl).attr("class")+"&txt="+$(ctrl).text());
							$(ctrl).attr("class",params.className?params.className:"");
							$(ctrl).text(params.txt);
							lib.eventEngine(group,$(ctrl).parent());
						});

					}
					else if (o.status == "A00001") {
						popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
								function(){
							popWin.loginShow();
						});
						lib.enableBtn($(ctrl));
					}
					else 
					{
						popWin.tipShow('alert', lib.ajaxCode[o.status],
								1000, null);
						lib.enableBtn($(ctrl));
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
	},
	doTop : function(ctrl, params) {
		if (!lib.disableBtn($(ctrl))) {
			$.ajax({
				url : "/ajax/group/doTop",
				datatype : "json",
				cache : false,
				data : {
					"group.id" : params.id,
					"isCancel":params.isCancel,
					"groupPost.id":params.postId
				},
				type : "post",
				success : function(o) {
					if (o.status == "A00004") {
						popWin.tipShow('suc', lib.ajaxCode[o.status],
								1000, function(){
							window.location.reload();
						});

					}
					else if (o.status == "A00001") {
						popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
								function(){
							popWin.loginShow();
						});
						lib.enableBtn($(ctrl));
					}
					else 
					{
						popWin.tipShow('alert', lib.ajaxCode[o.status],
								1000, null);
						lib.enableBtn($(ctrl));
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
	},
	doBest : function(ctrl, params) {
		if (!lib.disableBtn($(ctrl))) {
			$.ajax({
				url : "/ajax/group/doBest",
				datatype : "json",
				cache : false,
				data : {
					"group.id" : params.id,
					"isCancel":params.isCancel,
					"groupPost.id":params.postId
				},
				type : "post",
				success : function(o) {
					if (o.status == "A00004") {
						popWin.tipShow('suc', lib.ajaxCode[o.status],
								1000, function(){
							window.location.reload();
						});

					}
					else if (o.status == "A00001") {
						popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
								function(){
							popWin.loginShow();
						});
						lib.enableBtn($(ctrl));
					}
					else 
					{
						popWin.tipShow('alert', lib.ajaxCode[o.status],
								1000, null);
						lib.enableBtn($(ctrl));
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

if (group)
	lib.eventEngine(group,null);