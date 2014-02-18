var autoComplete=function(){
	  var curGetLink=null;
	  var curAutoCompleteTxtBox=null;
	  var curAutoCompleteCallBack=null;
	  var curTemplate=null;
	  var OriKey= "";
	  var autoIsKeyUp= false;
	  var clickDoc = function () {
	    	 $("#myDropdown").hide();
	        $(document).unbind("click", clickDoc);
	        OriKey = "";
	    };
      var autoCompleteTimerEvent= function () {
          if (!autoIsKeyUp)
              return;
          if (!curGetLink)
              return;
          if (!curAutoCompleteTxtBox)
              return;
          if ($.trim(curAutoCompleteTxtBox.val()) == "")
              return;
          if (curAutoCompleteTxtBox.val() == OriKey)
              return;
          OriKey = curAutoCompleteTxtBox.val();
          var layer = $("#myDropdown");
         autoIsKeyUp = false;
          $.ajax({
              dataType: "json",
              data: { key: OriKey },
              url: "/ajax/" + curGetLink,
              cache: false,
              type: "post",
              success: function (o) {
                  if (o.status == "A00003") {
                	  clickDoc();
                      return;
                  }
                  if (o.status == "A00001") {
                      ;
                      return;
                  }
                  if (o.status == "A00002") {
                      ;
                      return;
                  }
                  var str = "";
                  for (var i = 0; i < o.data.length; i++) {
                	  var s=curTemplate;
                	  var curData=o.data[i];
                     for(var attr in curData)
                     { 
                        s=s.replace(new RegExp("{"+attr+"}","g"),curData[attr]);
                     }
                     
                     if(i==0){
                    	 s=s.replace("{selected}","selected");
                     }
                     else{
                    	 s=s.replace("{selected}","");
                     }
                     str+=s;
                  }

                  layer.show();
                  var offset = curAutoCompleteTxtBox.offset();
                  layer.css("left", offset.left).css("top", offset.top + curAutoCompleteTxtBox.height() + 5);
                  $(document).bind("click", clickDoc);
                  layer.find(".gui-menu-list").html(str).find("li").hover(function () {
                      layer.find("li").removeClass("selected");
                      $(this).addClass("selected");
                  }).click(function () {
                	  clickDoc();
                      OriKey = "";
                      if (curAutoCompleteCallBack) {
                          curAutoCompleteCallBack($(this).attr("id"), $(this).attr("val"));
                      }
                  });
              }
          });
      };
      var autoCompleteLayer= '<div class="post-autoComp_tags" id="myDropdown" style="display:none;position: absolute; z-index: 9999; width: 200px; "><div class="gui-menu gui-autocomplete-menu"><ul class="gui-menu-list"></ul></div></div>';
      return {
    	  bind: function (ctrl, callBack,requestAction,itemTemplate) {
          var layer = $("#myDropdown");
          if (lib.appendToBody(layer,autoCompleteLayer)) {
              layer = $("#myDropdown");
              if(timerUtility.IsStart())
              {
            	  timerUtility.AddFunc("autoComplete",autoCompleteTimerEvent);
              }
              else
              {
            	  timerUtility.AddFunc("autoComplete",autoCompleteTimerEvent);
            	  timerUtility.StartTimer();
              }
          }
          $(ctrl).keydown(function (e) {
              autoIsKeyUp = true;
              var curLi = layer.find(".selected");
              var index = layer.find("li").index(curLi);
              var length = layer.find("li").length;
              var lis = layer.find("li").removeClass("selected");
              switch (e.which) {
                  case 38:
                      if (index == 0) {
                          $(lis[length - 1]).addClass("selected");

                      }
                      else
                          $(lis[index - 1]).addClass("selected");
                      e.preventDefault();
                      break;
                  case 40:
                      if (index == length - 1) {
                          $(lis[0]).addClass("selected");

                      }
                      else
                          $(lis[index + 1]).addClass("selected");
                      e.preventDefault();
                      break;
                  case 13:
                      OriKey = "";
                      if (callBack) {
                          if (curLi.attr("val"))
                          callBack(curLi.attr("id"), curLi.attr("val"));
                      }
                      e.preventDefault();
                      clickDoc();
                      autoIsKeyUp = false;
                      break;

              }
          }).focus(function () {
              curAutoCompleteTxtBox = $(this);
              if (requestAction) 
                 curGetLink = requestAction;
              if (callBack)
                  curAutoCompleteCallBack = callBack;
              curTemplate=itemTemplate;
          });
      }
      };
}();