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
    <script  type="text/javascript" src="../javascript/LoginCheckData.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/LoginCss.css"  />
</head>
<body>
    <div>
        <form action="userServlet" method="post" onsubmit="return loginCheckData()">
            <input type="hidden" name="method" value="login"/><%--登录识别--%>
            用户名称：<input type="text" name="userName" placeholder="a" id="userName" onblur="checkUserName()"/>
            <span id="userNameError" ></span><br/>
            用户密码：<input type="password" name="userPwd" placeholder="aaaaaa" id="userPassword" onblur="checkUserPassword()"/>
            <span id="userPasswordError"></span><br/><br/>
            <input type="submit" value="登录"/>
            <input type="reset" value="重置"/>
        </form>
        <a href="/login/AddUser.jsp">注册</a>
    </div>
</body>
</html>
<%--表单验证存在的理由在于：可以分担一些后端服务器的运算压力--%>
<%--表单验证的思路（如何让用户的体验更加优质）：首先在每一个表单提交项失去焦点时根据判断规则给与提示，
最后在表单提交时就不用给提示了，直接根据已定的规则判断是否让整个表单他提交到服务器--%>