var resourceUtils=function(){
	var resource={
			"kindeditor-min.js":{url:"/staticFile/js/kindeditor-min.js",cache:true,type:"js"},
			"ke_zh_CN.js":{url:"/staticFile/js/zh_CN.js",cache:true,type:"js"},
			"swfupload.js":{url:"/staticFile/js/SWFUpload/swfupload.js",cache:true,type:"js"},
			"swfuploadCookies.js":{url:"/staticFile/js/SWFUpload/plugins/swfupload.cookies.js",cache:true,type:"js"},
			"swfuploadQueue.js":{url:"/staticFile/js/SWFUpload/plugins/swfupload.queue.js",cache:true,type:"js"},
			"swfuploadSpeed.js":{url:"/staticFile/js/SWFUpload/plugins/swfupload.speed.js",cache:true,type:"js"},
			"swfuploadSwfobject.js":{url:"/staticFile/js/SWFUpload/plugins/swfupload.swfobject.js",cache:true,type:"js"},
	};
	return {

		loadResource:function(resourceKeys,callBack){
				try
				{
				if(resourceKeys.length>0)
				{
					var key=resourceKeys.shift();
					var r=resource[key];
					if(document.getElementById(key))
					{
						resourceUtils.loadResource(resourceKeys,callBack);
					}
					else
					{
					var s;
					if(r.type=="js")
					{
					s=document.createElement("script");
					s.type="text/javascript";
					s.language="javascript";
					if(r.cache)
			        s.src=r.url;
					else
					s.src=r.url+"?"+Math.random();
					
					if (s.readyState){
						s.onreadystatechange = function(){ 
						if (s.readyState == "loaded" || s.readyState == "complete"){ 
							s.onreadystatechange = null; 
							resourceUtils.loadResource(resourceKeys,callBack);
							} 
						};
					}
					else{
						s.onload=function(){
							resourceUtils.loadResource(resourceKeys,callBack);
						};
					}
					s.id=key;
					document.getElementsByTagName("head")[0].appendChild(s);
					}
					else
					{
						s=document.createElement("link");
						s.type="text/css";
						s.rel="stylesheet";
						if(r.cache)
				        s.href=r.url;
						else
						s.href=r.url+"?"+Math.random();
						s.id=key;
						document.getElementsByTagName("head")[0].appendChild(s);
						resourceUtils.loadResource(resourceKeys,callBack);
					}
					}
				}
				else
				{
					if(callBack){
						callBack();
					}
				}
				}
				catch(ex)
				{
					alert(ex);
				};
		    }
	};
}(); 