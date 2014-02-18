<ul>
<#list messages as message>
<li>
<a href="/infoCenter/message/send/${message.fromUserId!""}">${message.fromUser.nickName!""}发了${message.messageCount!""}封私信给你哦</a>
</li>
</#list>
</ul>