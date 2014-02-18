var content='<div id="cls_pnlHeader_wrap"class="cls_pnlHeader_wrap"><div id="cls_pnlHeader"><div id="cls_header_AppSlide"class=""><div class="cls_header_AppSlide_Title"><a href="/"title="XXX"id="cls_headLogo"></a></div></div><div class="cls_listUserInfo_box"><div class="cls_pnlSearch"><form id="searchForm"action="/search/all"method="get"><a href="javascript:void();"id="cls_btnSearch"title="点击搜索"></a><input name="key"type="text"id="cls_txtHeadSearch"class="lightgray"></form></div><ul class="cls_listUserInfo floatleft"><#if user??><li id="cls_headMenu_pofile"class=""><div class="layer_slideTitle"><a href="/i/${user.id}"class="slide link_shadow"title="${user.nickName}">${user.nickName}</a><b></b><div class="clear"></div></div><div class="layer_slideList layer_friendsList layright"><div class="layer_cnt"><ul><li><a href="/i/${user.id}"nouserface="true">我的空间</a></li><li><a href="/i/${user.id}/following">我的关注</a></li><li><a href="/i/${user.id}/follower">我的粉丝</a></li><li><a href="/i/${user.id}/blog">我的日志</a></li><li><a href="/i/${user.id}/answer">我的回答</a></li><li><a href="/i/${user.id}/question">我的提问</a></li><li><a href="/i/${user.id}/post">我的帖子</a></li><li><a class="link_headLogout"href="javascript:"onclick="document.location.href=\'/ajax/user/logout?backUrl=\'+encodeURIComponent(document.location.href);return false;">退出</a></li><div class="clear"></div></ul></div></div></li><li id="cls_headMenu_notice"class="li_msgList"><div class="layer_slideTitle"><a href="/infoCenter/notice"class="slide link_shadow">通知</a><b></b><div class="clear"></div></div><div class="layer_slideList layer_newsList layright"style="width: 270px; "><div class="layer_cnt"style="width: 250px;height:200px;"><div id="noticeList"style="text-align:center;"><img src="/staticFile/img/loading2.gif"/></div><div class="cls_bottom"><a style="float:left"href="/infoCenter/notice">查看全部</a><a style="float:right"href="javascripti:void(0);">清空全部</a><div class="clear"></div></div></div></div><div id="unreadNoticeCount">0</div></li><li id="cls_headMenu_message"class="li_msgList"><div class="layer_slideTitle"><a href="/infoCenter/message"class="slide link_shadow">消息</a><b></b><div class="clear"></div></div><div class="layer_slideList layer_newsList layright"style="width: 270px; "><div class="layer_cnt"style="width: 250px;height:200px;"><div id="messageList"style="text-align:center;"><img src="/staticFile/img/loading2.gif"/></div><div class="cls_bottom"><a style="float:left"href="/infoCenter/message">查看全部</a><a style="float:right"href="javascripti:void(0);">清空全部</a><div class="clear"></div></div></div></div><div id="unreadMessageCount">0</div></li><li id="cls_headMenu_settings"class=""><div class="layer_slideTitle"><a class="slide slidelink_shadow"href="/settings/profile/">设置</a><b></b><div class="clear"></div></div><div class="layer_slideList layer_friendsList layright"><div class="layer_cnt"><ul><li><a title="个人资料"href="/settings/profile/">个人资料</a></li><li><a title="我的头像"href="/settings/avatar/">我的头像</a></li><div class="clear"></div></ul></div></div></li><#else><li class="head_menu"><a class="link_shadow"href="javascript:void(0);"onclick="popWin.regClose();popWin.loginShow();return false;">登陆</a></li><li class="head_menu"><a class="link_shadow"href="javascript:void(0);"onclick="popWin.loginClose();popWin.regShow();return false;">注册</a></li></#if></ul><div class="clear"></div></div><ul class="cls_listApp"><li class="head_menu"id="cls_headMenu_home"><a class="link_shadow"href="/">首页</a></li><li id="cls_headMenu_question"class=""><div class="layer_slideTitle"><a class="slide slidelink_shadow"href="/question">问答</a><b></b><div class="clear"></div></div><div class="layer_slideList layer_friendsList"><div class="layer_cnt"><ul><li><a href="/question/newest">最新问题</a></li><li><a href="/question/hottest">最热问题</a></li><li><a href="/question/pending">等待回答</a></li><div class="clear"></div></ul></div></div></li><li class="head_menu"id="cls_headMenu_menu"><a class="link_shadow"href="/site/all">主题站</a></li><li class="head_menu"id="cls_headMenu_menu"><a class="link_shadow"href="/blog/all">日志</a></li><li id="cls_headMenu_group"class=""><div class="layer_slideTitle"><a class="slide slidelink_shadow"href="/group/all">小组</a><b></b><div class="clear"></div></div><div class="layer_slideList layer_friendsList"><div class="layer_cnt"><ul><li><a href="/group/all">全部小组</a></li><li><a href="/group/search">搜索小组</a></li><li><a href="/group/hot_posts">最热帖子</a></li><div class="clear"></div></ul></div></div></li><li class="head_menu"id="cls_headMenu_menu"><a class="link_shadow"href="/tag/all">标签</a></li><div class="clear"></div></div></div>';
$(function(){
   $("body").append(content);
   
var showIn=function(){
  this.className="current";
};
var showOut=function(){
  this.className="";
};

var noticeShowIn=function(){
  this.className="current";
};
var loadNoticeList=function(){
    $.ajax({
			url : "/ajax/user/getNoticeList",
			datatype : "json",
			cache : false,
			type : "get",
			success : function(o) {
				if (o.status == "A00004") {
                    $("#cls_pnlHeader #noticeList").html(o.data);
                      var count=$("#cls_pnlHeader #noticeList li").length;
                    if(count>0)
                    {
                        $("#unreadNoticeCount").html(count).show();
                        
                    }
				}
				else
				{
				     $("#cls_pnlHeader #noticeList").html("加载失败...");
				}
			},
				error : function(request) {
					$("#cls_pnlHeader #noticeList").html("加载失败...");
				}
		});
};
var loadMessageList=function(){
  $.ajax({
			url : "/ajax/user/getMessageList",
			datatype : "json",
			cache : false,
			type : "get",
			success : function(o) {
				if (o.status == "A00004") {
                    $("#cls_pnlHeader #messageList").html(o.data);
                    var count=$("#cls_pnlHeader #messageList li").length;
                    if(count>0)
                    {
                        $("#unreadMessageCount").html(count).show();
                        
                    }
				}
				else
				{
				     $("#cls_pnlHeader #messageList").html("加载失败...");
				}
			},
				error : function(request) {
					$("#cls_pnlHeader #messageList").html("加载失败...");
				}
		});
};


$("#cls_headMenu_question").mouseenter(showIn).mouseleave(showOut);
$("#cls_headMenu_group").mouseenter(showIn).mouseleave(showOut);
$("#cls_headMenu_pofile").mouseenter(showIn).mouseleave(showOut);
$("#cls_headMenu_message").mouseenter(showIn).mouseleave(showOut);
$("#cls_headMenu_notice").mouseenter(showIn).mouseleave(showOut);
$("#cls_headMenu_settings").mouseenter(showIn).mouseleave(showOut);
$("#cls_txtHeadSearch").JQP_HighLineInput({nullCon:"搜问题/文章/帖子..."});
$("#cls_btnSearch").click(function(){$("#searchForm").submit();return false;});
loadNoticeList();
loadMessageList();
});
var userState={};
<#if user??>userState.isLogon=1;<#else>userState.isLogon=0;</#if>
<#if user??>userState.id=${user.id}</#if>