<div class="gui-block-b" style="width:300px;">
<h3 class="gui-block-hd"><a onclick="popWin.loginClose(); return false;" href="javascript:;" class="gui-block-close">X</a><span>登录 ( <a href="javascript:popWin.loginClose();popWin.regShow();" >还没有注册?</a> ) </span></h3>
<div id="pop_win_login" class="gui-block-bd">
        <fieldset>
            <div class="item">
                <label for="loginemail">帐号</label>
                <input tabindex="1" type="text" id="login_email"  class="gstxt"/>
            </div>
            <div class="item">
                <label for="login_password">密码</label>
                <input tabindex="2" type="password"  id="login_password" class="gstxt"/>
            </div>
            <div class="item remember">
                <label>&nbsp;</label>
                <input id="login_remember" type="checkbox" name="login_remember" tabindex="4"/>
                <label for="login_remember" class="sub">下次自动登录 |</label><a href="http://www.douban.com/accounts/resetpassword" style="margin-left: 0.8em;" >忘记密码了</a>
            </div>
            <div class="item recsubmit">
                <label>&nbsp;</label>
                <div><span class="bn-flat"><a href="javascript:void(0);"  id="loginSubmit" class="gbtn-primary">登陆</a> </span></div>
            </div>
        </fieldset>
</div>
</div>
