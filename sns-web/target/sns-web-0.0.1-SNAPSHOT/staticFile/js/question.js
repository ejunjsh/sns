var question = function() {
	if(typeof template !='undefined')
	{
	template.openTag = '@!';
	template.closeTag = '!@';
	}
	return {
		doLock : function(ctrl, params) {
			popWin.confirmShow(params.confirm, function() {
				if (!lib.disableBtn($(ctrl))) {

					$.ajax({
						url : application.contextPath+"/ajax/question/doQuestionLock",
						datatype : "json",
						cache : false,
						data : {
							id : params.id
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
								popWin.tipShow('alert', "一个小时只能操作一次", 1000,
										null);
								lib.enableBtn($(ctrl));
							} else {
								popWin.tipShow('suc', lib.ajaxCode[o.status],
										1000, function() {
											window.location.reload();
										});
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
		doVoteAnswer : function(ctrl, params) {
				if (!lib.disableBtn($(ctrl))) {

					$.ajax({
						url :application.contextPath+ "/ajax/question/doVoteAnswer",
						datatype : "json",
						cache : false,
						data : {
							id : params.id,
							value:params.value
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
								popWin.tipShow('alert', "你已经投过票了！！", 1000,
										null);
								lib.enableBtn($(ctrl));
							} else {
								popWin.tipShow('suc', lib.ajaxCode[o.status],
										1000, null);
								lib.enableBtn($(ctrl));
								$(ctrl).html(parseInt(params.count)+1);
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
		doUselessAnswer : function(ctrl, params) {
			if (!lib.disableBtn($(ctrl))) {

				$.ajax({
					url : application.contextPath+"/ajax/question/doUselessAnswer",
					datatype : "json",
					cache : false,
					data : {
						id : params.id
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
							popWin.tipShow('alert', "你已经点过了哦！！", 1000,
									null);
							lib.enableBtn($(ctrl));
						} else {
							popWin.tipShow('suc', lib.ajaxCode[o.status],
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
	addAnswer:function(ctrl,params)
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
	deleteAnswer : function(ctrl, params) {
		popWin.confirmShow(params.confirm, function() {
		if (!lib.disableBtn($(ctrl))) {
			$.ajax({
				url :application.contextPath+ "/ajax/question/deleteAnswer",
				datatype : "json",
				cache : false,
				data : {
					id : params.id
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
						$("#answer"+params.id).fadeOut();
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
getComments : function(ctrl, params) {
	var $cmtList;
	if(params.commentType==1)
    {
	    $cmtList=$("#cmtListQ"+params.refId);
	   
    }
	else
		{
		$cmtList=$("#cmtListA"+params.refId);
		}
	 if($cmtList)
	    {
	    	if($cmtList.attr("class").indexOf("ghide")==-1)
	    	{
	    		$cmtList.addClass("ghide");
	    		return;
	    	}
	    }
	 else
		 {
		 return;
		 }
	if (!lib.disableBtn($(ctrl))) {
		$.ajax({
			url : "/ajax/question/listComments",
			datatype : "json",
			cache : false,
			data : {
				"comment.refId" : params.refId,
				"comment.commentType":params.commentType
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
					lib.enableBtn($(ctrl));
					$cmtList.removeClass("ghide");
					if(o.data&&o.data.length>0)
						{
						o.userState={};
						o.userState.id=userState.id;
					var html=template.render('c-list', o);
					var $container=$cmtList.find(".cmts-list").html(html);
					lib.eventEngine(question,$container);
				}	
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
cancelCmt:function(ctrl,params)
{
	$(ctrl).parents(".cmtsList").addClass("ghide");
},
addComment : function(ctrl, params) {
	var $cmtList;
	if(params.commentType==1)
    {
	    $cmtList=$("#cmtListQ"+params.refId);
	   
    }
	else
		{
		$cmtList=$("#cmtListA"+params.refId);
		}
	$txt=$cmtList.find("textarea");
	 var $replyName=$cmtList.find("input[name='replyName']");
	   var $replyId=$replyName.next();
	if(!$txt.val()||!$txt.val().replace($replyName.val(),""))
		{
		popWin.tipShow('alert',"你总该填点什么吧",
				1000, null);
		return;
		}
	if(lib.getTxtLen($txt)>200)
		{
		popWin.tipShow('alert',"只能输入200个字符的哦",
				1000, null);
		return;
		}
	var rid=0;
	if($txt.val().indexOf($replyName.val())==0)
		rid=$replyId.val();
	if (!lib.disableBtn($(ctrl))) {
		$.ajax({
			url : "/ajax/question/addComment",
			datatype : "json",
			cache : false,
			data : {
				"comment.refId" : params.refId,
				"comment.commentType":params.commentType,
				"comment.content":$txt.val(),
				"comment.replyId":rid
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
					popWin.tipShow('alert', "问题已经被锁,不能回复了", 1000,
							null);
					lib.enableBtn($(ctrl));
				} 
				else {
					popWin.tipShow('suc', lib.ajaxCode[o.status],
							1000, null);
					lib.enableBtn($(ctrl));
					$cmtList.removeClass("hide");
					if(o.data&&o.data.length>0)
						{
						o.userState={};
					o.userState.id=userState.id;
					var html=template.render('c-list', o);
					var $container=$cmtList.find(".cmts-list").html(html);
					lib.eventEngine(question,$container);
					$txt.val("");
					$replyName.val("");
					$replyId.val("");
				}	
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
deleteComment : function(ctrl, params) {
	popWin.confirmShow(params.confirm, function() {
	if (!lib.disableBtn($(ctrl))) {
		$.ajax({
			url : "/ajax/question/deleteComment",
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
					var parent=$(ctrl).parent();
					parent.fadeOut(function(){
						parent.remove();
					});
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
replyComment : function(ctrl, params) {
	var $cmtList;
	if(params.commentType==1)
    {
	    $cmtList=$("#cmtListQ"+params.refId);
	   
    }
	else
	{
		$cmtList=$("#cmtListA"+params.refId);
	}
	if($cmtList[0])
	{
	   $txt=$cmtList.find("textarea");
	   if($txt[0])
		   {
	   var $replyName=$cmtList.find("input[name='replyName']");
	   var $replyId=$replyName.next();
	   $replyName.val("回复@"+params.check+":");
	   $replyId.val(params.id);
	   if($txt.val().indexOf($replyName.val())!=0)
		   {
	   $txt.val( $replyName.val()+$txt.val());
	   lib.setCursorPosition($txt[0],$txt[0].value.length);
		   }
	}
	}
	return false;
},
doFollow : function(ctrl, params) {
	if (!lib.disableBtn($(ctrl))) {
		$.ajax({
			url : "/ajax/question/doFollow",
			datatype : "json",
			cache : false,
			data : {
				"question.id" : params.id
			},
			type : "post",
			success : function(o) {
				if (o.status == "A00004") {
					popWin.tipShow('suc', lib.ajaxCode[o.status],
							1000, function(){
						lib.enableBtn($(ctrl));
						$(ctrl).unbind();
						$(ctrl).attr("data-operation","doUnfollow");
						$(ctrl).attr("data-params","id="+params.id+"&className="+$(ctrl).attr("class")+"&txt="+$(ctrl).text());
						$(ctrl).attr("class",params.className?params.className:"");
						$(ctrl).text(params.txt);
						lib.eventEngine(question,$(ctrl).parent());
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
					popWin.tipShow('alert', "你已经关注哦！！", 1000,
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
doUnfollow : function(ctrl, params) {
	if (!lib.disableBtn($(ctrl))) {
		$.ajax({
			url : "/ajax/question/doUnfollow",
			datatype : "json",
			cache : false,
			data : {
				"question.id" : params.id
			},
			type : "post",
			success : function(o) {
				if (o.status == "A00004") {
					popWin.tipShow('suc', lib.ajaxCode[o.status],
							1000, function(){
						lib.enableBtn($(ctrl));
						$(ctrl).unbind();
						$(ctrl).attr("data-operation","doFollow");
						$(ctrl).attr("data-params","id="+params.id+"&className="+$(ctrl).attr("class")+"&txt="+$(ctrl).text());
						$(ctrl).attr("class",params.className?params.className:"");
						$(ctrl).text(params.txt);
						lib.eventEngine(question,$(ctrl).parent());
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


if (question)
	lib.eventEngine(question,null);