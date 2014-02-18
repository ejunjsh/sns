var lib = function() {
	return {
		ajaxCode : {
			A00001 : "你需要登录才能继续",
			A00002 : "处理失败，请稍后再试",
			A00003 : "没有找到数据",
			A00004 : "处理成功",
			A00005 : "你的操作被禁止，请联系管理员"
		},
		getTxtLen: function (obj) {
	        return $(obj)[0].value.replace(/[^\x00-\xff]/g, 'xx').length;
	    },
	    setCursorPosition: function (ctrl, pos) {
	        if (ctrl.setSelectionRange) {
	            ctrl.focus();
	            ctrl.setSelectionRange(pos, pos);
	        }
	        else if (ctrl.createTextRange) {
	            var length = pos;
	            for (var i = 1; i < length; i++) {
	                if (ctrl.value.charAt(i) == '\n') {
	                    pos--;
	                }
	            }
	            var range = ctrl.createTextRange();
	            range.collapse(true);
	            range.moveEnd('character', pos);
	            range.moveStart('character', pos);
	            range.select();
	        }
	    },
		disableBtn : function($btn) {
			if (!$btn[0].locked) {
				$btn[0].locked = true;
				lib.appendToBody($("#wait"),"<div id='wait'>请稍后...</div>");
				$("#wait").fadeIn();
				return false;
			}
			return true;
		},
		enableBtn : function($btn) {
			$btn[0].locked = false;
			$("#wait").fadeOut();
		},
		appendToBody : function(obj, str) {
			if (typeof str == "string") {
				if (obj == null || obj[0] == null) {
					$("body").append(str);
					return true;
				}
			}
			return false;
		},
		errorImg:function(obj){
			obj.src="/staticFile/img/defaultAvatar.gif";
		},
		eventEngine:function(funcs,$container)
		{
			if(!funcs) return;
			var $ctrls;
			if(!$container)
				$ctrls=$("[data-operation]");
			else
				$ctrls=$container.find("[data-operation]");
			if($ctrls)
			{
				for(var i=0;i<$ctrls.length;i++)
				{
					var $ctrl=$($ctrls[i]);
					var eventNames=$ctrl.attr("data-event");
					if(eventNames)
				    {
						eventNames=eventNames.split(',');
				    }
					else
					{
						eventNames=["click"];
					}
				    $ctrl[0].h={};
					for(var a=0;a<eventNames.length;a++){
						var eventName=eventNames[a];
						var opers=$ctrl.attr("data-operation").split(',');
						$ctrl[0].h[eventName]=opers[a];
					$ctrl.bind(eventName,function(e){
						$c=$(this);
						var oper=this.h[e.type];
                        
						var params=$c.attr("data-params");
						if(params)
						{
							var o={};
							var arr = params.split('&');
					        for (var j = 0; j < arr.length; j++) {
					            if (arr[j]) {
					                var key = arr[j].split('=')[0];
					                var value = arr[j].split('=')[1];
					                if (key && value) {
					                    o[key] = value;
					                }
					            }
					        }
					        params=o;
						}
						
						return funcs[oper](this,params);
					});
					}
				}
			}
		}
	};
}();