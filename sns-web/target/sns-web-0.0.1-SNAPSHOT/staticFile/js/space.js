var space = function() {
	return {
		followEnter:function(ctrl,params)
		{
			$(ctrl).attr("class","gbtn-cancel").text("取 消");
		},
		followLeave:function(ctrl,params)
		{
			$(ctrl).attr("class","gbtn-complete").text("已关注");
		},
		doFollow : function(ctrl, params) {
			if (!lib.disableBtn($(ctrl))) {
				$.ajax({
					url : application.contextPath+ "/ajax/user/doFollow",
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
								$(ctrl).attr("data-operation","doUnfollow,followEnter,followLeave");
								$(ctrl).attr("data-event","click,mouseenter,mouseleave");
								$(ctrl).attr("data-params","id="+params.id);
								$(ctrl).attr("class","gbtn-complete");
								$(ctrl).text("已关注");
								lib.eventEngine(space,$(ctrl).parent());
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
					url : application.contextPath+ "/ajax/user/doUnfollow",
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
								$(ctrl).removeAttr("data-event");
								$(ctrl).attr("data-operation","doFollow");
								$(ctrl).attr("data-params","id="+params.id);
								$(ctrl).attr("class","gbtn-focus");
								$(ctrl).text("加关注");
								lib.eventEngine(space,$(ctrl).parent());
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
		 updateCategory:function(ctrl,params)
		   {
			   popWin.customShow(function($win){
					var $txt= $win.find("input");
					var $ctrl=$win.find(".gbtn-primary");
					$txt.val(params.txt);
					$ctrl.unbind("click");
					$ctrl.click(function(){
						if(!$txt.val())
						{
							popWin.tipShow('alert', '你总要写点什么吧',
									1000, null);
							return false;
						}
						if(lib.getTxtLen($txt)>50)
						{
						popWin.tipShow('alert',"只能输入50个字符的哦",
								1000, null);
						return false;
						}
						if (!lib.disableBtn($ctrl)) {
							$.ajax({
								url : application.contextPath+ "/ajax/blog/updateCategory",
								datatype : "json",
								cache : false,
								data : {
				                      "category.name":$txt.val(),
				                      "category.id":params.id
								},
								type : "post",
								success : function(o) {
									if (o.status == "A00002") {
										popWin.tipShow('alert', lib.ajaxCode[o.status],
												1000, null);
										lib.enableBtn($ctrl);
									}
									else if (o.status == "A00001") {
										popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
												function(){
											popWin.customClose();
											popWin.loginShow();
										});
										lib.enableBtn($ctrl);
									}
									else if (o.status == "A00005") {
										popWin.tipShow('alert', "不能创建同名的分类哦", 1000,
												null);
										lib.enableBtn($ctrl);
									} 
									else {
										popWin.tipShow('suc', lib.ajaxCode[o.status],
												1000, null);
										window.location.reload();
									}	
								},
								error : function(request) {
									popWin.tipShow('alert', lib.ajaxCode["A00002"],
											1000, null);
									lib.enableBtn($ctrl);
								}
							});

						}
						return false;
					});
					
				}
		   );
			   return false;
		   },
		   doFollowTag : function(ctrl, params) {
				if (!lib.disableBtn($(ctrl))) {
					$.ajax({
						url : application.contextPath+ "/ajax/tag/doFollow",
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
									$(ctrl).attr("data-operation","doUnfollowTag");
									$(ctrl).attr("data-params","id="+params.id+"&className="+$(ctrl).attr("class")+"&txt="+$(ctrl).text());
									$(ctrl).attr("class",params.className?params.className:"");
									$(ctrl).html(params.txt);
									lib.eventEngine(space,$(ctrl).parent());
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
			doUnfollowTag : function(ctrl, params) {
				if (!lib.disableBtn($(ctrl))) {
					$.ajax({
						url : application.contextPath+ "/ajax/tag/doUnfollow",
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
									$(ctrl).attr("data-operation","doFollowTag");
									$(ctrl).attr("data-params","id="+params.id+"&className="+$(ctrl).attr("class")+"&txt="+$(ctrl).text());
									$(ctrl).attr("class",params.className?params.className:"");
									$(ctrl).html(params.txt);
									lib.eventEngine(space,$(ctrl).parent());
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
			 addAlbum:function(ctrl,params)
			   {
				   popWin.customShow(function($win){
						var $txt= $win.find("input");
						var $txt1= $win.find("textarea");
						var $ctrl=$win.find(".gbtn-primary");
						$ctrl.unbind("click");
						$ctrl.click(function(){
							if(!$txt.val()||!$txt1.val())
							{
								popWin.tipShow('alert', '你总要写点什么吧',
										1000, null);
								return false;
							}
							if(lib.getTxtLen($txt)>50)
							{
							popWin.tipShow('alert',"标题只能输入50个字符的哦",
									1000, null);
							return false;
							}
							if(lib.getTxtLen($txt1)>500)
							{
							popWin.tipShow('alert',"描述只能输入500个字符的哦",
									1000, null);
							return false;
							}
							if (!lib.disableBtn($ctrl)) {
								$.ajax({
									url : application.contextPath+ "/ajax/photo/addAlbum",
									datatype : "json",
									cache : false,
									data : {
					                      "album.title":$txt.val(),
					                      "album.description":$txt1.val()
									},
									type : "post",
									success : function(o) {
										if (o.status == "A00002") {
											popWin.tipShow('alert', lib.ajaxCode[o.status],
													1000, null);
											lib.enableBtn($ctrl);
										}
										else if (o.status == "A00001") {
											popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
													function(){
												popWin.customClose();
												popWin.loginShow();
											});
											lib.enableBtn($ctrl);
										}
										else {
											popWin.tipShow('suc', lib.ajaxCode[o.status],
													1000, null);
											window.location.reload();
										}	
									},
									error : function(request) {
										popWin.tipShow('alert', lib.ajaxCode["A00002"],
												1000, null);
										lib.enableBtn($ctrl);
									}
								});

							}
							return false;
						});
						
					}
			   );
				   return false;
			   },
			   addPhoto:function(ctrl, params)
				{
					popWinUploader.show(application.contextPath+ "/ajax/fileUpload/uploadToTemporary",function(json){
						 popWin.customShow(function($win){
							    var $img=$win.find("img");
								var $txt= $win.find("input");
								var $txt1= $win.find("textarea");
								var $ctrl=$win.find(".gbtn-primary");
								$img[0].src=json.data;
								$ctrl.unbind("click");
								$ctrl.click(function(){
									if(!$txt.val()||!$txt1.val())
									{
										popWin.tipShow('alert', '你总要写点什么吧',
												1000, null);
										return false;
									}
									if(lib.getTxtLen($txt)>50)
									{
									popWin.tipShow('alert',"标题只能输入50个字符的哦",
											1000, null);
									return false;
									}
									if(lib.getTxtLen($txt1)>500)
									{
									popWin.tipShow('alert',"描述只能输入500个字符的哦",
											1000, null);
									return false;
									}
									if (!lib.disableBtn($ctrl)) {
										$.ajax({
											url : application.contextPath+ "/ajax/photo/addPhoto",
											datatype : "json",
											cache : false,
											data : {
							                      "photo.title":$txt.val(),
							                      "photo.description":$txt1.val(),
							                      "photo.albumId":params.albumId,
							                      "tmpUrl":json.data
											},
											type : "post",
											success : function(o) {
												if (o.status == "A00004") {
													popWin.tipShow('suc', lib.ajaxCode[o.status],
															1000, null);
													window.location.reload();
												}
												else if (o.status == "A00001") {
													popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
															function(){
														popWin.customClose();
														popWin.loginShow();
													});
													lib.enableBtn($ctrl);
												}
												else {
													popWin.tipShow('alert', lib.ajaxCode[o.status],
															1000, null);
													lib.enableBtn($ctrl);
												}	
											},
											error : function(request) {
												popWin.tipShow('alert', lib.ajaxCode["A00002"],
														1000, null);
												lib.enableBtn($ctrl);
											}
										});
									}
								});
						 },"addPhotoWin");
					});
					return false;
				},
				 addPhotos:function(ctrl, params)
					{
						multipleImageUploader.show(null,function($btn,tmpUrls){
							$.ajax({
								url : application.contextPath+ "/ajax/photo/addPhotos",
								datatype : "json",
								cache : false,
								data : {
				                      "photo.albumId":params.albumId,
				                      "tmpUrls":tmpUrls
								},
								type : "post",
								success : function(o) {
									if (o.status == "A00004") {
										popWin.tipShow('suc', lib.ajaxCode[o.status],
												1000, null);
										window.location.reload();
									}
									else if (o.status == "A00001") {
										popWin.tipShow('alert',lib.ajaxCode[o.status], 1000,
												function(){
											popWin.customClose("multipleImageUploader");
											popWin.loginShow();
										});
										lib.enableBtn($btn);
									}
									else {
									popWin.tipShow('alert', lib.ajaxCode[o.status],
												1000, null);
										lib.enableBtn($btn);
									}	
								},
								error : function(request) {
									popWin.tipShow('alert', lib.ajaxCode["A00002"],
											1000, null);
									lib.enableBtn($btn);
								}
							});
						});	
						return false;
						
					}
	};
}();


if (space)
	lib.eventEngine(space,null);