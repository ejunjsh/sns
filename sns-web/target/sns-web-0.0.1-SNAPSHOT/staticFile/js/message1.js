var message1 = function() {
	$("#msgList li").bind("mouseenter",function(){
		$(this).find(" .gfr a")[0].className="";
	}).bind("mouseleave",function(){
		$(this).find(" .gfr a")[0].className="ghide";
	});

	return {
		doDeleteMessage : function(ctrl, params) {
			popWin.confirmShow(params.confirm, function() {
			if (!lib.disableBtn($(ctrl))) {
				$.ajax({
					url : "/ajax/user/doDeleteMessage",
					datatype : "json",
					cache : false,
					data : {
						"message.id" : params.id
					},
					type : "post",
					success : function(o) {
						if (o.status == "A00004") {
							popWin.tipShow('suc', lib.ajaxCode[o.status],
									1000, function(){
								lib.enableBtn($(ctrl));
								$(ctrl).parents("li").fadeOut();
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
			});
			return false;
		}
		};
		}();


		if (message1)
			lib.eventEngine(message1,null);