<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新聞首頁</title>
</head>
<body>
<h1>新聞首頁</h1>
<p>
<ul>
    <#list newList>
        <ul>
            <#items as news>
            <li>標題: ${news.title} 作者:${news.author} 發布時間:${news.createTime}     <form action="/news/id=${news.id}" method="POST">
                    <a href="javascript:;" onclick="parentNode.submit();">點我查看內容</a>
                </form><br>
                </#items>
        </ul>
    </#list>
</ul>
<#if (context)??>
    文章內容:
    ${context} </br>
</#if>
<a href="/index">回到首頁</a>
<a href="/login">登入首頁</a>
</p>
</body>
</html>