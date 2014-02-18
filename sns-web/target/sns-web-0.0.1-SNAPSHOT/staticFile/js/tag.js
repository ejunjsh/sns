var tag = function() {
	return {
		editDesc:function(ctrl,params){
			var $editorTxtarea=$('<div id="edui66" class="edui-editor " style="z-index: 900; "><textarea id="keditor" class="gttxt" style="margin: 0px; width:98%; height: 150px; "></textarea></div>');
			var $editorDo=$('<p class="main-board-do" id="boardDo"><a href="javascript: void 0;" class="cancel-edit" id="cancelEditDesc">取消</a><a href="javascript: void 0;" class="gbtn-primary" id="editDescPush">保存</a></p>');
			var process=function(){
				var $con=$("#boardEditor");
				var $dec=$("#tagDesc").hide();
				var $textarea=$editorTxtarea.find("textarea");
				$textarea.val($dec.html());
				$con.append($editorTxtarea).append($editorDo);
				
			
				KindEditor.create('#'+$textarea.attr("id"), {
					    uploadJson:'/ajax/keImageUpload/upload',
						cssPath : ['/staticFile/css/kind-code.css'],
						afterBlur: function()
						{
							this.sync();
						},
						items : [
							'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
							'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
							'insertunorderedlist', '|', 'emoticons', 'image', 'link']
					});
			
			    $(ctrl).parent().hide();
			    var hideFuc=function()
			    {
			    	$editorTxtarea.remove();
			    	$editorDo.remove();
			    	$(ctrl).parent().show();
			    	$dec.show();
			    	KindEditor.remove('#'+$textarea.attr("id"));
			    };
			    $con.find("#cancelEditDesc").click(function(){
			    	hideFuc();
			    	return false;
			    });
			    $con.find("#editDescPush").click(function(){
			    	var $button=$(this);
			    	if($textarea.val())
			    	{
			    	if (!lib.disableBtn($button)) {
			    		$.ajax({
							url : "/ajax/tag/doEdit",
							datatype : "json",
							cache : false,
							data : {
								"tag.id" : params.id,
								"tag.description":$textarea.val()
							},
							type : "post",
							success : function(o) {
								if (o.status == "A00004") {
									$dec.html($textarea.val());
									hideFuc();
									lib.enableBtn($button);
								}
								else if (o.status == "A00001") {
									popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
											function(){
										popWin.loginShow();
									});
									lib.enableBtn($button);
								}
								else 
								{
									popWin.tipShow('alert', lib.ajaxCode[o.status],
											1000, null);
									lib.enableBtn($button);
								}
							},
						    error: function(request) {
								popWin.tipShow('alert', lib.ajaxCode["A00002"],
										1000, null);
								lib.enableBtn($button);	
						    }
			    		});
			    	}
			    	}
			    	else{
			    		popWin.tipShow('alert',"请输入点东西吧",
								1000, null);
			    	}
			    });
			};
			if(window.KindEditor)
			{
				process();
			}
			else
			{
				resourceUtils.loadResource("kindeditor-min.js,ke_zh_CN.js".split(","),process);	
			}
			
		    return false;
		},
		doFollow : function(ctrl, params) {
			if (!lib.disableBtn($(ctrl))) {
				$.ajax({
					url : "/ajax/tag/doFollow",
					datatype : "json",
					cache : false,
					data : {
						"tag.id" : params.id
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
								$(ctrl).html(params.txt);
								lib.eventEngine(tag,$(ctrl).parent());
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
							popWin.tipShow('alert', "你已经关注了哦！！", 1000,
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
					url : "/ajax/tag/doUnfollow",
					datatype : "json",
					cache : false,
					data : {
						"tag.id" : params.id
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
								$(ctrl).html(params.txt);
								lib.eventEngine(tag,$(ctrl).parent());
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
		doFollowUser : function(ctrl, params) {
			if (!lib.disableBtn($(ctrl))) {
				$.ajax({
					url : "/ajax/user/doFollow",
					datatype : "json",
					cache : false,
					data : {
						"user.id" : params.id
					},
					type : "post",
					success : function(o) {
						if (o.status == "A00004") {
							popWin.tipShow('suc', lib.ajaxCode[o.status],
									1000, function(){
								lib.enableBtn($(ctrl));
								$(ctrl).unbind();
								$(ctrl).attr("data-operation","doUnfollowUser");
								$(ctrl).attr("data-params","id="+params.id+"&className="+$(ctrl).attr("class")+"&txt="+$(ctrl).text());
								$(ctrl).attr("class",params.className?params.className:"");
								$(ctrl).html(params.txt);
								lib.eventEngine(tag,$(ctrl).parent());
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
							popWin.tipShow('alert', "你已经关注了哦！！", 1000,
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
		doUnfollowUser : function(ctrl, params) {
			if (!lib.disableBtn($(ctrl))) {
				$.ajax({
					url : "/ajax/user/doUnfollow",
					datatype : "json",
					cache : false,
					data : {
						"user.id" : params.id
					},
					type : "post",
					success : function(o) {
						if (o.status == "A00004") {
							popWin.tipShow('suc', lib.ajaxCode[o.status],
									1000, function(){
								lib.enableBtn($(ctrl));
								$(ctrl).unbind();
								$(ctrl).attr("data-operation","doFollowUser");
								$(ctrl).attr("data-params","id="+params.id+"&className="+$(ctrl).attr("class")+"&txt="+$(ctrl).text());
								$(ctrl).attr("class",params.className?params.className:"");
								$(ctrl).html(params.txt);
								lib.eventEngine(tag,$(ctrl).parent());
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
		uploadTagImg:function(ctrl, params)
		{
			popWinUploader.show("/ajax/fileUpload/uploadToTemporary",function(json){
				  $("#tagAvatar").attr("src",json.data);
                  popWin.confirmShow("是否保存？",function(){
                	  $ctrl=$(ctrl);
                	  if (!lib.disableBtn($ctrl)) {
  			    		$.ajax({
  							url : "/ajax/tag/doEdit",
  							datatype : "json",
  							cache : false,
  							data : {
  								"tag.id" : params.id,
  								"tmpUrl": json.data
  							},
  							type : "post",
  							success : function(o) {
  								if (o.status == "A00004") {
  									popWin.tipShow('suc', lib.ajaxCode[o.status],
  											1000, function(){
  									lib.enableBtn($ctrl);
  									});
  								}
  								else if (o.status == "A00001") {
  									popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
  											function(){
  										popWin.loginShow();
  									});
  									lib.enableBtn($ctrl);
  								}
  								else 
  								{
  									popWin.tipShow('alert', lib.ajaxCode[o.status],
  											1000, null);
  									lib.enableBtn($ctrl);
  								}
  							},
  						    error: function(request) {
  								popWin.tipShow('alert', lib.ajaxCode["A00002"],
  										1000, null);
  								lib.enableBtn($ctrl);	
  						    }
  			    		});
                	  }
                  });
			});
			return false;
		}
	};
}();

if (tag)
	lib.eventEngine(tag,null);