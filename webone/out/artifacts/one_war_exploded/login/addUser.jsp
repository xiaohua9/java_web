<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/16
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script type="text/javascript" src="../javascript/InfoCheckData.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/LoginCss.css"  />
</head>
<body>
    <form action="userServlet" method="post" onsubmit="return infoCheckData()">
        <input type="hidden" name="method" value="add"/><%--添加识别--%>
        用户名称：<input type="text" name="userName" id="userName" onblur="checkUserName()"/>
        <span id="userNameError"></span><br/>
        用户密码：<input type="text" name="userPassword" id="userPassword" onblur="checkUserPassword()"/>
        <span id="userPasswordError"></span><br/>
        用户性别：<input type="text" name="userGender" id="userGender" onblur="checkUserGender()"/>
        <span id="userGenderError"></span><br/>
        用户年龄：<input type="text" name="userAge" id="userAge" onblur="checkUserAge()"/>
        <span id="userAgeError"></span><br/>
        用户地址：<input type="text" name="userAddress" id="userAddress" onblur="checkUserAddress()"/>
        <span id="userAddressError"></span><br/>
        用户生日：<input type="text" name="userBirthday" id="userBirthday" onblur="checkUserBirthday()"/>
        <span id="userBirthdayError"></span><br/>
        <input type="submit" value="提交"/>
        <input type="reset" value="重置"/>
    </form>
</body>
</html>
