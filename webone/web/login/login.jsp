<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/15
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
    <form action="/login" method="get">
        用户名称：<input type="text" name="userName"><br/>
        用户密码：<input type="password" name="userPwd"><br/>
        <input type="submit" value="提交">
        <input type="reset" value="重置">
    </form>
</body>
</html>
