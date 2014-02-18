<div class="gui-block-b"  style="width:500px;">
<h3 class="gui-block-hd"><a onclick="popWin.regClose(); return false;" href="javascript:;" class="gui-block-close">X</a><span>注册( <a href="javascript:popWin.regClose();popWin.loginShow();" >已有账号?</a> ) </span></h3>
<div id="pop_win_login" class="gui-block-bd">
        <fieldset>
            <div class="item extra-tips">
                <label style="width:45px" for="form_email">邮箱</label>
                <input tabindex="1" type="text"  id="form_email" class="gstxt" value=""/>
                <span defValue="邮箱是你登陆的凭证" class="validate-option">邮箱是你登陆的凭证</span>
            </div>
            <div class="item extra-tips">
                <label style="width:45px" for="form_nickname">昵称</label>
                <input tabindex="2" type="text" id="form_nickname"  class="gstxt" value=""/>
                <span defValue="昵称是你在这里的称号"  class="validate-option">昵称是你在这里的称号</span>
            </div>
            <div class="item extra-tips">
                <label style="width:45px" for="form_password">密码</label>
                <input tabindex="3" type="password" id="form_password" class="gstxt"/>
                <span defValue="强壮的密码是你安全的保障" class="validate-option">强壮的密码是你安全的保障</span>
            </div>
          <div class="item extra-tips">
                <label style="width:45px" for="form_code">验证码</label>
                <input tabindex="4" type="text" style="width:100px;" id="form_code" class="gstxt"/>
                <img class="code" src="/randomImg?t=" alt="点击刷新" onclick='this.src="/randomImg?t="+Math.random();'/>
                <span defValue="防止机器人?" class="validate-option">防止机器人?</span>
            </div>
            <div class="item recsubmit">
                <label style="width:45px">&nbsp;</label>
                <div><span class="bn-flat"><a href="javascript:void(0);"  id="form_submit" class="gbtn-primary">注册</a></span></div>
            </div>
        </fieldset>
</div>
</div>
