<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>鱼皮官网</title>
</head>
<body>
<h1>欢迎来到鱼皮官网</h1>
<!-- 循环渲染导航条 -->
<ul>
    <#list menuItems as item>
        <li><a href="${item.url}">${item.label}</a></li>
    </#list>
</ul>
<!-- 底部版权信息（注释部分，不会被输出) -->
<footer>
    ${currentYear} 鱼皮官网. All rights reserved.
</footer>
</body>
</html>
