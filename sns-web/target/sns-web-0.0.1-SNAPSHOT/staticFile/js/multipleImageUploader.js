
var multipleImageUploader=function(){
	var progressbars={};
	
		var showError= function(itemDiv, msg) {
			itemDiv.find('.ke-progressbar').hide();
			var message=itemDiv.find('.ke-message');
			message.addClass('ke-error').show();
			if(msg)
			message.html(msg);
			else
				message.html("上传错误");	
		};
		var removeFile= function(fileId) {
			swfu.cancelUpload(fileId);
			var itemDiv = $('div[data-id="' + fileId + '"]');
			itemDiv.remove();
		};
		var removeFiles=function() {
			var bodyDiv=$('.ke-swfupload-body');
			bodyDiv.find('.ke-item').each(function() {
				removeFile($(this).attr('data-id'));
			});
		};
		var appendFile = function(file) {
			var bodyDiv=$('.ke-swfupload-body');
			var itemDiv = $('<div class="ke-inline-block ke-item" data-id="' + file.id + '"></div>');
			bodyDiv.append(itemDiv);
			var photoDiv = $('<div class="ke-inline-block ke-photo"></div>')
				.mouseover(function(e) {
					$(this).addClass('ke-on');
				})
				.mouseout(function(e) {
					$(this).removeClass('ke-on');
				});
			itemDiv.append(photoDiv);

			var img =$('<img  src="' + file.url + '" class="ke-img" data-status="' + file.filestatus + '" width="80" height="80" alt="' + file.name + '" />');
			photoDiv.append(img);
			$('<span class="ke-delete">X</span>').appendTo(photoDiv).click(function() {
				removeFile(file.id);
			});
			var statusDiv = $('<div class="ke-status"></div>').appendTo(photoDiv);
			// progressbar
			$(['<div class="ke-progressbar">',
				'<div class="ke-progressbar-bar"><div class="ke-progressbar-bar-inner"></div></div>',
				'<div class="ke-progressbar-percent">0%</div></div>'].join('')).hide().appendTo(statusDiv);
			// message
			$('<div class="ke-message">' +'等待上传' + '</div>').appendTo(statusDiv);

			itemDiv.append('<div class="ke-name">' + file.name + '</div>');

			progressbars[file.id] = {
				bar : photoDiv.find('.ke-progressbar-bar-inner'),
				percent : photoDiv.find('.ke-progressbar-percent')
			};
		};
		var remove = function() {
			this.removeFiles();
			this.swfu.destroy();
			this.div.html('');
		};
		var swfu=null;
	  return {
	   show:function(postParams,sucCallback)
	   {
		   var process=function()
		   {
			   popWin.customShow(function($win){
				   var $submitBtn=$win.find("#submitBtn");
				   $submitBtn.click(function(){
					   var $imgs=$('img[data-status="-4"]');
					   if(!$imgs[0])
					   {
						   popWin.tipShow('alert', "请上传图片",
									1000, null);
						   return;
					   }
					   var tmpUrls="";
					   $imgs.each(function()
							   {
						   tmpUrls+=($(this).attr("url")+"|");
							   });
					   if (!lib.disableBtn($submitBtn)) {
						   if(sucCallback)
						   {
							   sucCallback($submitBtn,tmpUrls);
						   }
					   }
					   });
			   },"multipleImageUploader");
		   };
		   if(typeof SWFUpload=="undefined")
		   {
			   resourceUtils.loadResource("swfupload.js,swfuploadCookies.js,swfuploadQueue.js".split(","),function(){
					popWin.initialCustom({id:"multipleImageUploader",title:"多图片上传",content:'<div class="swfupload"><div class="ke-swfupload"><div class="ke-swfupload-top"><div class="ke-inline-block ke-swfupload-button"><div></div></div><div class="ke-inline-block ke-swfupload-desc">允许用户同时上传20张图片，单张图片容量不超过1MB</div><span class="ke-button-common ke-button-outer ke-swfupload-startupload"><input type="button" class="ke-button-common ke-button" value="开始上传"></span></div><div class="ke-swfupload-body"></div><div style="margin-top:10px;float:right;"><a href="javascript:void(0)" id="submitBtn" class="gbtn-primary">保存</a><div></div></div></div></div>',width:"650"});
					var settings = {
							debug : false,
							upload_url : "/ajax/multipleImageUpload/upload/",
							flash_url : "/staticFile/js/SWFUpload/Flash/swfupload.swf",
							file_post_name : "file",
							button_placeholder : $('.ke-swfupload-button div')[0],
							button_image_url: '/staticFile/img/select-files-zh_CN.png',
							button_width: 72,
							button_height: 23,
							button_cursor : SWFUpload.CURSOR.HAND,
							file_types : '*.jpg;*.jpeg;*.gif;*.png;*.bmp',
							file_types_description : 'Image Files',
							file_upload_limit :20 ,
							file_size_limit :'1MB',
							post_params : postParams,
							file_queued_handler : function(file) {
								file.url = '/staticFile/img/image.png';
								appendFile(file);
							},
							file_queue_error_handler : function(file, errorCode, message) {
								var errorName = '';
								switch (errorCode) {
									case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
										errorName = "你只能上传20张";
										break;
									case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
										errorName ="你只能上传1mb的图片";
										break;
									case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
										errorName = "无效图片";
										break;
									case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
										errorName = "无效格式";
										break;
									default:
										errorName = "未知错误";
										break;
								}
								popWin.tipShow('alert', errorName,
										1000, null);
							},
							upload_start_handler : function(file) {
								var itemDiv = $('div[data-id="' + file.id + '"]');
								itemDiv.find('.ke-message').hide();
								itemDiv.find('.ke-progressbar').show();
							},
							upload_progress_handler : function(file, bytesLoaded, bytesTotal) {
								var percent = Math.round(bytesLoaded * 100 / bytesTotal);
								var progressbar = progressbars[file.id];
								progressbar.bar.css('width', Math.round(percent * 80 / 100) + 'px');
								progressbar.percent.html(percent + '%');
							},
							upload_error_handler : function(file, errorCode, message) {
								if (file && file.filestatus == SWFUpload.FILE_STATUS.ERROR) {
									var itemDiv = $('div[data-id="' + file.id + '"]');
									showError(itemDiv, message);
								}
							},
							upload_success_handler : function(file, serverData) {
								var itemDiv = $('div[data-id="' + file.id + '"]');
								var data = {};
								try {
									data = eval("("+serverData+")");;
								} catch (e) {
									
								}
								if (data.error !== 0) {
									showError(itemDiv,data.message);
									return;
								}
								file.url = data.url;
								itemDiv.find('.ke-img').attr('url',file.url).attr('src', file.url).attr('data-status', file.filestatus);
								itemDiv.find('.ke-status').hide();
							}
						};   
					swfu=new SWFUpload(settings);
					process();
					$(".ke-swfupload-startupload").click(function(){
						swfu.startUpload();
					});
			   });
		   }
		   else
		   {
			   process();
		   }

	   }
   };
}();