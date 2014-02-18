<ul>
<#list notices as notice>
<li>
<#if notice.noticeType==1>
<a href="/question/${notice.refId!""}">在问题"${notice.title!""}"中@了你${notice.count!""}次</a>
</#if>
<#if notice.noticeType==2>
<a href="/answer/redirect/${notice.refId!""}">在问题"${notice.title!""}"的答案中@了你${notice.count!""}次</a>
</#if>
<#if notice.noticeType==3>
<a href="/blog/${notice.refId!""}">在日志"${notice.title!""}"中@了你${notice.count!""}次</a>
</#if>
<#if notice.noticeType==4>
<a href="/blog/comment/redirect/${notice.refId!""}">在日志"${notice.title!""}"的评论中@了你${notice.count!""}次</a>
</#if>
<#if notice.noticeType==5>
<a href="/article/${notice.refId!""}">在文章"${notice.title!""}"中@了你${notice.count!""}次</a>
</#if>
<#if notice.noticeType==6>
<a href="/article/comment/redirect/${notice.refId!""}">在文章"${notice.title!""}"的评论中@了你${notice.count!""}次</a>
</#if>
<#if notice.noticeType==7>
<a href="/post/${notice.refId!""}">在帖子"${notice.title!""}"中@了你${notice.count!""}次</a>
</#if>
<#if notice.noticeType==8>
<a href="/post/comment/redirect/${notice.refId!""}">在帖子"${notice.title!""}"的评论中@了你${notice.count!""}次</a>
</#if>
<#if notice.noticeType==9>
<a href="/question/${notice.refId!""}">有个问题的评论中@了你${notice.count!""}次</a>
</#if>
<#if notice.noticeType==10>
<a href="/answer/redirect/${notice.refId!""}">有个答案的评论中@了你${notice.count!""}次</a>
</#if>
<#if notice.noticeType==11>
<a href="/photo/comment/redirect/${notice.refId!""}">在图片"${notice.title!""}"的评论中@了你${notice.count!""}次</a>
</#if>
</li>
</#list>
</ul>