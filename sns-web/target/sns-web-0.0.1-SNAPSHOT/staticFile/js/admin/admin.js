if(!DWZ.resource){
	DWZ.resource={};
}
jsUrl=application.contextPath+"/staticFile/js/admin";
DWZ.resource["tag.js"]={url:jsUrl+"/tag/tag.js",cache:false,type:"js"};
DWZ.resource["question.js"]={url:jsUrl+"/question/question.js",cache:false,type:"js"};
DWZ.resource["blog.js"]={url:jsUrl+"/blog/blog.js",cache:false,type:"js"};
DWZ.resource["article.js"]={url:jsUrl+"/article/article.js",cache:false,type:"js"};
DWZ.resource["fileUpload.js"]={url:jsUrl+"/common/fileUpload.js",cache:false,type:"js"};
DWZ.resource["getBack.js"]={url:jsUrl+"/common/getBack.js",cache:false,type:"js"};
DWZ.resource["group.js"]={url:jsUrl+"/group/group.js",cache:false,type:"js"};
DWZ.resource["notice.js"]={url:jsUrl+"/notice/notice.js",cache:false,type:"js"};