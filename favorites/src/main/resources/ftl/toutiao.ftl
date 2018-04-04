<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="Content-Language" content="zh-CN"/>
    <meta name="robots" content="index, follow"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>今日头条收藏博客列表</title>
    <style>
        .left {
            margin: 0 auto;
            width: 960px;
            border: 0px solid;
            text-align: left;
        }
    </style>
</head>
<body style="background:#f8f8f8">
<center>今日头条收藏博客列表   总数量:${nums}</center>
<div class="left">
    <#list blogs as blog>
        * <a href="${blog.display_url}" target="_blank">  ${blog.title}</a>     ${blog.behot_time} </br>
    </#list>
</div>
</body>