<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>XXX Admin</title>

<link href="/staticFile/js/dwz-ria-1.4.4/dwz-ria/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/staticFile/js/dwz-ria-1.4.4/dwz-ria/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/staticFile/js/dwz-ria-1.4.4/dwz-ria/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="/staticFile/css/admin.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="/staticFile/js/dwz-ria-1.4.4/dwz-ria/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/jquery.cookie.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.core.js" type="text/javascript"></script>

<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.drag.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.accordion.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.ui.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.theme.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.navTab.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.tree.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.panel.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.pagination.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.ajax.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.combox.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.util.date.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.dialog.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.resize.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.suggest.js" type="text/javascript"></script>
<script src="/staticFile/js/dwz-ria-1.4.4/dwz-ria/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="/staticFile/js/admin/admin.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	DWZ.init("/staticFile/js/dwz-ria-1.4.4/dwz-ria/dwz.frag.xml", {
		loginUrl:"/admin/embedPage/user/login", loginTitle:"登录",	// 弹出登录对话框
		status:{ok:"A00004", error:"A00002", timeout:"A00001"}, //【可选】
		pageInfo:{pageNum:"params.pageIndex", numPerPage:"params.pageSize", orderField:"params.sort", orderDirection:"params.order"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"/staticFile/js/dwz-ria-1.4.4/dwz-ria/themes"}); // themeBase 相对于index页面的主题base路径
		    <#if Request["needLogin"]??>
		    DWZ.loadLogin();
		    </#if>
		}
	});
});

</script>
</head>
<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<ul class="nav">
				<#if curUser??>
				    <li><a href="javascript:void(0);"><@s.property value="curUser.nickName" /></a></li>
					<li><a onclick="document.location.href='/ajax/user/logout?backUrl='+encodeURIComponent(document.location.href);return false;" href="javascript:void(0);">退出</a></li>
				<#else>
				    <li><a onclick="DWZ.loadLogin();return false;" href="javascript:void(0);">登陆</a></li>
				</#if>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>用户管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a target="navTab" rel="searchUser" href="/admin/embedPage/user/searchUser">查找用户</a>
							</li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>问答管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a target="navTab" rel="searchQuestion"  href="/admin/embedPage/question/searchQuestion">查找问题</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>日志管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a target="navTab" rel="searchBlog"  href="/admin/embedPage/blog/searchBlog">查找日志</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>文章管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a target="navTab" rel="searchArticle"  href="/admin/embedPage/article/searchArticle">查找文章</a></li>
							<li><a target="navTab" rel="searchArticleTopic"  href="/admin/embedPage/article/searchArticleTopic">查找主题</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>小组管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a target="navTab" rel="searchGroup"  href="/admin/embedPage/group/searchGroup">查找小组</a></li>
						    <li><a target="navTab" rel="searchGroupPost"  href="/admin/embedPage/group/searchGroupPost">查找帖子</a></li>
						     <li><a target="navTab" rel="searchGroupCategory"  href="/admin/embedPage/group/searchGroupCategory">查找小组分类</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>标签管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a  target="navTab" rel="searchTag"  href="/admin/embedPage/tag/searchTag">查找标签</a></li>
						</ul>
					</div>
					<!--<div class="accordionHeader">
						<h2><span>Folder</span>通知管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a target="navTab" rel="searchSysNotice"  href="/admin/embedPage/notice/searchSysNotice">查找系统通知</a></li>
						</ul>
					</div> -->
					<div class="accordionHeader">
						<h2><span>Folder</span>设置</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="tabsPage.html">？？？</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
					test
					</div>
					</div>

				</div>
			</div>
		</div>

	</div>

	<div id="footer">made by <a href="http://weibo.com/findsombody" target="_blank">Idiot_s_Sky</a> | QQ：542752480</div>
</body>
</html>