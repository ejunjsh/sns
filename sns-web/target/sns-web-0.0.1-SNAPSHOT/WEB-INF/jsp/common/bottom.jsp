<div class="gbottom">
    <div class="gbottom-nav">
       <a href="http://weibo.com/339238080">我的微薄</a>
    </div>
    <div class="gbottom-i">Idiot's Sky</div>
</div>
<s:if test="#request.needLogin==1">
<script type="text/javascript">
   $(function(){
	   if(userState.isLogon==0)
	   popWin.loginShow();
   });
</script>
</s:if>

<script type="text/javascript">
$(function() {
    var backToTopTxt = "返回顶部", backToTopEle = $('<div class="backToTop tops"></div>').appendTo($("body"))
        .text(backToTopTxt).attr("title", backToTopTxt).click(function() {
            $("html, body").animate({ scrollTop: 0 }, 520);
    }), backToTopFun = function() {
        var st = $(document).scrollTop(), winh = $(window).height();
        (st > 300)? backToTopEle.show(): backToTopEle.hide();    
        //IE6下的定位
        if (!window.XMLHttpRequest) {
            backToTopEle.css("top", st + winh - 60);    
        }
    };
    $(window).bind("scroll", backToTopFun);
    backToTopFun();
    <s:if test="prompt!=null">
    popWin.tipShow('suc','<s:property value="prompt" />', 1000,null);
    </s:if>
});
</script>