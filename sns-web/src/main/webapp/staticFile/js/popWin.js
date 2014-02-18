var popWin = function() {

	var mashHtml = '<div id="mashDiv" style="position:fixed;width:100%;height:100%;left:0px;top:0px; background-color:white;z-index:599;opacity: 0.5; filter:alpha(opacity=50);">&nbsp;</div>';
	var popAjaxHtml = '<div id="{id}Pop" class="gui-block" style="z-index: 9999;"><a onclick="popWin.{id}Close();" href="javascript:;" class="gui-block-close">X</a><div style="padding:20px 60px;background-color:white;width:100px;"><img width="16px" height="16px" src="/staticFile/img/loading.gif" />加载数据...</div></div>';
	var popTipHtml = '<div id="{id}Pop" class="gui-block" style="z-index: 9999;"><div style="padding:10px 20px;font-size: 14px;background-color:white;"><span class="tip {tipType}"></span>{content}</div></div>';
	var popComfirmHtml='<div id="{id}Pop" class="gui-block" style="z-index: 9999;"><div class="gui-block-b" style="width:400px;"> <h4 class="gui-block-hd"><span id="blockTitle">提示</span><a class="blockClose gui-block-close" href="javascript:void(0)" title="关闭">X</a></h4><div id="blockContent" class="gui-block-bd"><p class="gui-block-bd-content"><span style="float:left;" class="tip bigConfirm"></span>{content}</p><p class="gui-block-bd-do" style="float: right"><a id="blockYes" href="javascript:void(0)" class="gbtn-primary">确定</a><a id="blockNo" href="javascript:void(0)">取消</a></p></div></div></div>';
	var popCustomHtml='<div id="{id}" class="gui-block" style="z-index: 9999;"><div class="gui-block-b" style="width:{width}px;"><h3 class="gui-block-hd"><a onclick="popWin.customCloseForCloseButton(this); return false;" href="javascript:;" class="gui-block-close">X</a><span>{title}</span></h3><div id="pop_win_custom" class="gui-block-bd">{content}</div></div></div>';
	var $mash;
	var $customWin;
    

    var iframeSubmit= function (array, formId, formAction, iframeId) {
    	  var formStr = "<form name=\"" + formId + "\" id=\"" + formId + "\" method=\"post\" action=\"" + formAction + "\" style=\"display:none\" target=\"" + iframeId + "\">";
          for (var p in array) {
              formStr += "<input type=\"password\" id=\"" +formId+ p + "\" name=\"" + p + "\" />";
          }
          formStr += "</form>";
          lib.appendToBody($("#" + formId), formStr);
          var iframeStr = "<iframe name=\"" + iframeId + "\" id=\"" + iframeId + "\" style=\"display:none\"></iframe>";
          lib.appendToBody($("#" + iframeId), iframeStr);
        for (var p in array) {
        	document.getElementById(formId+p).value=array[p];
        }
        if ($.browser.msie) {
            eval("window." + formId + ".submit()");
        } else {

            $("#" + formId).submit();
        }
    };
	var ajax_IsExsitEmail= function (callback, async, val) {
	        $.ajax(
	       {
	           async: async,
	           url:application.contextPath+ "/ajax/user/checkEmail",
	           datatype: "json",
	           cache: false,
	           data: { "user.email": val },
	           type: "post",
	           success: function (o) {
	               callback(o);
	           },
	           error: function (request) {

	           }
	       }
	    );
	    };
	  var  ajax_IsExsitNickName= function (callback, async, val, url) {
	        if (!url) {
	            url = application.contextPath+"/ajax/user/checkNickName";
	        }
	        $.ajax(
	       {
	           async: async,
	           url: url,
	           datatype: "json",
	           cache: false,
	           data: { "user.nickName": val },
	           type: "post",
	           success: function (o) {
	               callback(o);
	           },
	           error: function (request) {

	           }
	       }
	    );
	    };
	   var ajax_IsValidCode= function (callback, async, val) {
	        $.ajax(
	       {
	           async: async,
	           url: application.contextPath+"/ajax/user/checkCode",
	           datatype: "json",
	           cache: false,
	           data: { code: val },
	           type: "post",
	           success: function (o) {
	               callback(o);
	           },
	           error: function (request) {
	           }
	       }
	    );
	    };
    var checkRegObj= { isMail: false, isPwd: false, isNickName: false, isVailCode: false};
    var initCheckRegObj= function () {
        for (var p in checkRegObj) {
            checkRegObj[p] = false;
        }
    };
    var isValiPass=function()
    {
    	  for (var p in checkRegObj) 
    	  {
              if(!checkRegObj[p])
            	  return false;
          }
    	  return true;
    };
    var checkIsMail=function (inputCtr, plus) {
        checkRegObj.isMail = false;
        var regStr = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
        if ($.trim(inputCtr.val()) == "") {
            plus.attr("class","validate-error");
            plus.html("邮箱是必须的。");
            plus.show();
        }
        else if (!regStr.test(inputCtr.val())) {
        	plus.attr("class","validate-error");
            plus.html("请输入正确的邮箱地址。");
            plus.show();
        }
        else {
            ajax_IsExsitEmail(function (o) {
                if (o.data == 1) {
                	plus.attr("class","validate-error");
                	plus.html("该邮箱地址已被注册。");
                    plus.show();
                }
                else {
                	checkRegObj.isMail = true;
                    plus.attr("class","validate-option");
                	plus.html(plus.attr("defValue"));
                    plus.hide();
                }
            }, arguments[2] != null ? arguments[2] : true, $.trim(inputCtr.val()));

        }

    };

   var checkIsPwd= function (inputCtr, plus) {
        checkRegObj.isPwd = false;
        var regStr = /\s+/;
        if (regStr.test(inputCtr.val())) {
        	plus.attr("class","validate-error");
            plus.html("密码请勿使用特殊字符。");
            plus.show();
        }
        else if (inputCtr.val() == "") {
        	plus.attr("class","validate-error");
            plus.html("密码太短了，最少6位。");
            plus.show();
        }
        else if (inputCtr.val().length < 6) {
        	plus.attr("class","validate-error");
            plus.html("密码太短了，最少6位。");
            plus.show();
        }
        else if (inputCtr.val().length > 16) {
        	plus.attr("class","validate-error");
            plus.html("密码太长了，最多16位。");
            plus.show();
        }
        else {
        	checkRegObj.isPwd = true;
            plus.attr("class","validate-option");
        	plus.html(plus.attr("defValue"));
            plus.hide();
        }
    };
    var checkNickName = function  (inputCtr, plus){
    	checkRegObj.isNickName = false;
        if ($.trim(inputCtr.val()) == "") {
        	plus.attr("class","validate-error");
        	 plus.html("请输入昵称");
        	 plus.show();
        }
        else if (/^[0-9]*$/.test($.trim(inputCtr.val()))) {
        	plus.attr("class","validate-error");
        	 plus.html("昵称不能全是数字");
        	 plus.show();
        }
        else if (!/^[0-9a-zA-Z\u4e00-\u9fa5_]*$/.test($.trim(inputCtr.val()))) {
        	plus.attr("class","validate-error");
        	 plus.html("支持中英文、数字或者“_”。");
       	 plus.show();
        }
        else if ($.trim(inputCtr.val()).replace(/[^\x00-\xff]/g, 'xx').length > 20) {
        	plus.attr("class","validate-error");
       	 plus.html("不能超过20个字母或10个汉字");
      	 plus.show();
        }
        else {
            ajax_IsExsitNickName(function (o) {
                if (o.data == "1") {
                	plus.attr("class","validate-error");
                	plus.html("此昵称太受欢迎，已有人抢了");
                	 plus.show();
                }
                else {
                	checkRegObj.isNickName = true;
                    plus.attr("class","validate-option");
                	plus.html(plus.attr("defValue"));
                    plus.hide();
                }

            }, arguments[2] != null ? arguments[2] : true, $.trim(inputCtr.val()));
        }
    };
    var checkIsVailCode=function (inputCtr, plus) {
       checkRegObj.isVailCode = false;
        if (inputCtr.val() == "") {
        	plus.attr("class","validate-error");
            plus.html("请输入验证码。");
            plus.show();
        }
        else {
            ajax_IsValidCode(function (o) {
                if (o.data == "1") {
                	plus.attr("class","validate-error");
                    plus.html("验证码不正确。");
                    plus.show();
                }
                else {
                    checkRegObj.isVailCode = true;
                    plus.attr("class","validate-option");
                	plus.html(plus.attr("defValue"));
                    plus.hide();
                }
            }, arguments[2] != null ? arguments[2] : true, $.trim(inputCtr.val()));

        }
    };
    var pwdPower=function (l) {
        function k(n) {
            if (n >= 65 && n <= 90) {
                return 2
            } else {
                if (n >= 97 && n <= 122) {
                    return 4
                } else {
                    if (n >= 48 && n <= 57) {
                        return 1
                    } else {
                        return 8
                    }
                }
            }
        }
        function j(n) {
            var o = 0;
            for (var i = 0; i < 4; i++) {
                if (n & 1) {
                    o++
                }
                n >>>= 1
            }
            return o
        }
        var h = 0,
            g = l.length;
        if (g < 6) {
            return 1
        }
        for (i = 0; i < g; i++) {
            h |= k(l.charCodeAt(i))
        }
        var m = j(h);
        if (l.length >= 10) {
            m++
        }
        switch (m) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
            case 4:
                return 3;
            default:
                return 1
        }
    };
   var bindVailCtrl= function () {
        var code=$("#form_code").blur(function(){
        	checkIsVailCode($(this),$(this).next().next());
        }).focus(function(){
        	var $tip=$(this).next().next().attr("class","validate-option");
        	$tip.html($tip.attr("defValue")).show();
        });
       var nickname= $("#form_nickname").blur(function(){
        	checkNickName($(this),$(this).next());
        }).focus(function(){
        	var $tip=$(this).next().attr("class","validate-option");
        	$tip.html($tip.attr("defValue")).show();
        });
       var password= $("#form_password").blur(function(){
        	checkIsPwd($(this),$(this).next());
        }).focus(function(){
        	var $tip=$(this).next().attr("class","validate-option");
        	$tip.html($tip.attr("defValue")).show();
        }).keyup(function () {
        	var $tip=$(this).next().attr("class","validate-option");
            var power = pwdPower(this.value);
            if (power == 1) {
            	$tip.html("密码强度：低");
            }
            else if (power == 2) {
            	$tip.html("密码强度：中");
            }
            else if (power == 3) {
            	$tip.html("密码强度：强");
            }
            $tip.show();
        });;
       var email= $("#form_email").blur(function(){
        	checkIsMail($(this),$(this).next());
        }).focus(function(){
        	var $tip=$(this).next().attr("class","validate-option");
        	$tip.html($tip.attr("defValue")).show();
        });
        
        
        $("#form_submit").click(function()
        {
        	if(!lib.disableBtn($(this)))
            {
        	    initCheckRegObj();
        	    checkIsVailCode(code,code.next().next(),false);
        	    checkNickName(nickname,nickname.next(),false);
        	    checkIsPwd(password,password.next());
        	    checkIsMail(email,email.next(),false);
        	    if(!isValiPass())
        	    {
        	    	lib.enableBtn($(this));
        	    }
        	    else
        	    {
        	    	iframeSubmit({"user.email":email.val(),"user.passWord":password.val(),
						"user.nickName":nickname.val(),"code":code.val()},"regForm","/ajax/user/reg","regIframe");
        	    }
            }
        	return false;
        });
    };
    
	var showMash = function() {
		if (lib.appendToBody($mash, mashHtml)) {
			$mash = $("#mashDiv");
		}
		if ($.browser.msie && ($.browser.version == "6.0")) {
			$mash.css("position", "absolute").width($(window).width()).height(
					$(document).height());
		}
		$mash.show();
	};
	var closeMash = function() {
		if ($mash != null && $mash[0] != null) {
			$mash.hide();
		}
	};

	var centerInScreen = function(obj,notFix) {
		if (typeof obj == "object") {
			var w = obj.width();
			var h = obj.height();
			var size = {
				left : (($(document).width()) / 2 - (parseInt(w) / 2)),
				top : (((($(window).height() - h) / 2) + (notFix?$(document).scrollTop():0)))
			};
			obj.css("left", size.left).css("top", size.top);
		}
	};

	var topAndHideInScreen = function(obj,startHeight) {
		if (typeof obj == "object") {
			var w = obj.width();
			var h = obj.height();
			var size = {
				left : (($(document).width()) / 2 - (parseInt(w) / 2)),
				top : -h+startHeight
			};

				obj.css("left", size.left).css("top", size.top);
		}
	};
	var scrollDown = function($pop,startHeight, callBack) {
		$pop.animate({
			"top" : startHeight
		}, 300, function() {
			if (callBack)
				callBack();
		});
	};
	var scrollUp = function( $pop,startHeight, callBack) {
		$pop.animate({
			"top" : -$pop.height() +startHeight
		}, 300, function() {
			if (callBack)
				callBack();
		});
	};
	return {
		loginShow : function() {
			showMash();
			var $pop = $("#loginPop");
				if (lib.appendToBody($pop, popAjaxHtml.replace(/\{id\}/g, "login"))) {
		
					$pop = $("#loginPop");
					
					centerInScreen($pop);
					
					$.ajax({
						dataType : "json",
						data : {
							Name : name
						},
						url :application.contextPath+ "/ajax/user/getLogin",
						cache : false,
						type : "post",
						success : function(o) {
							$pop.html(o.data);
							
							centerInScreen($pop);
						
							$("#login_email").JQP_HighLineInput({nullCon:"邮箱/昵称"});
							$("#loginSubmit").click(function()
							{
								if(!lib.disableBtn($(this)))
								{
								    iframeSubmit({"user.email":$("#login_email").val(),"user.passWord":$("#login_password").val(),
								    "remember":$("#login_remember")[0].checked},"loginForm",application.contextPath+"/ajax/user/login","loginIframe");
								}
								return false;
							});
							$("#login_email").keyup(function(e)
							{
								if(e.which==13)
									$("#loginSubmit").click();
							});
							$("#login_password").keyup(function(e)
							{
										if(e.which==13)
											$("#loginSubmit").click();
							});
						},
						error : function() {

						}
					});
				}
			
			$pop.show();
		},
		loginClose : function() {
			closeMash();
			$("#loginBg").hide();
			$("#loginPop").hide();
		},
		regShow : function() {
			showMash();
			var $pop = $("#regPop");
				if (lib.appendToBody($pop, popAjaxHtml.replace(/\{id\}/g, "reg"))) {
					
					$pop = $("#regPop");
					centerInScreen($pop);
					$.ajax({
						dataType : "json",
						data : {
							Name : name
						},
						url : application.contextPath+"/ajax/user/getReg",
						cache : false,
						type : "post",
						success : function(o) {
							$pop.html(o.data);
							centerInScreen($pop);
							bindVailCtrl();
						},
						error : function() {

						}
					});
				}
			
			$pop.show();
		},
		regClose : function() {
			closeMash();
			$("#regPop").hide();
		},
		tipShow : function(tipType, content, timeOut,callBack) {
			var $pop = $("#tipPop");
				if (lib.appendToBody($pop, popTipHtml.replace(/\{id\}/g, "tip")
						.replace(/\{tipType\}/g, tipType).replace(
								/\{content\}/g, content))) {
					$pop = $("#tipPop");
					$pop.show();
					topAndHideInScreen($pop,38);
					scrollDown( $pop, 38, function() {
						setTimeout(function() {
							scrollUp($pop, 38,  function() {
								$pop.remove();
								if(callBack)
									callBack();
							});
						}, timeOut);
					});
				}
				else{
					if(callBack)
						callBack();
				}
		},
		confirmShow : function(content, callBack) {
			var $pop = $("#confirmPop");
				if (lib.appendToBody($pop, popComfirmHtml.replace(/\{id\}/g, "confirm").replace(
								/\{content\}/g, content))) {
					$pop = $("#confirmPop");
					$pop.show();
					showMash();
					centerInScreen($pop);
					var close=function(){
						$pop.remove();
						closeMash();
					};
					$pop.find("#blockYes").click(function(){
						if(callBack)
					    {
					       callBack();	
					       $pop.remove();
					   	   closeMash();
						}
					}).next().click(close);
					$pop.find(".blockClose").click(close);
				}
		},
		loginCallBack:function(isSuccess){
			if(isSuccess)
		    {
				this.tipShow('suc', "登陆成功。", 1000,function(){
					window.location.reload();
				});
				
		    }
			else
			{
				
				this.tipShow('alert', "账号和密码不匹配。", 1000,function(){
					lib.enableBtn($("#loginSubmit"));
				});
			}
		},
		regCallBack:function(isSuccess){
			if(isSuccess)
		    {
				this.tipShow('suc', "注册成功。", 1000,function(){
					window.location.reload();
				});
				
		    }
			else
			{
				
				this.tipShow('alert', "注册失败，请稍后再试。", 1000,function(){
					lib.enableBtn($("#loginSubmit"));
				});
			}
		},
		initialCustom:function(obj,initalFunc)
		{
			if(obj)
			{
				if (lib.appendToBody(null, popCustomHtml
						.replace(/\{content\}/g, obj.content)
						.replace(/\{title\}/g, obj.title)
						.replace(/\{width\}/g, obj.width)
						.replace(/\{id\}/g, obj.id?obj.id:"customPop"))) {
					$customWin=$("#"+ (obj.id?obj.id:"customPop"));
                        if(initalFunc)
                        	{
                        	initalFunc($customWin);
                        	}
				}
			}
		},
		customClose:function(id)
		{
			$customWin=$("#"+ (id?id:"customPop"));
			if($customWin[0])
				{
			$customWin.hide();
			closeMash();
				}
		},
		customCloseForCloseButton:function(ctrl)
	    {
			$(ctrl).parents(".gui-block").hide();
			closeMash();
	    },
		customShow:function(callBack,id)
		{
			$customWin=$("#"+ (id?id:"customPop"));
			if($customWin[0])
			{
				$customWin.show();
				showMash();
				centerInScreen($customWin);
				if(callBack){
					callBack($customWin);
				}
			}
		}
	};
}();