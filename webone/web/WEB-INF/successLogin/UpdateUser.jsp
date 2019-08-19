<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/16
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更改数据</title>
    <script>
        //表单数据提交验证
        function checkDate(){
            var flag=true;
            if (userName.value.length<1){
                flag=false;
                userNameError.style.color="red";
                userNameError.innerText="用户名不能为空";
            }
            if (userPassword.value.length<6){
                flag=false;
                userPasswordError.style.color="red";
                userPasswordError.innerText="密码不能小于6位";
            }
            return flag;
        }
        //用户名失去焦点验证
        function checkUserName() {
            if (userName.value.length<1){
                userNameError.style.color="red";
                userNameError.innerText="用户名不能为空";
            } else {
                userNameError.innerText="";
            }
        }
        //用户密码失去焦点验证
        function checkUserPassword() {
            if (userPassword.value.length<6){
                userPasswordError.style.color="red";
                userPasswordError.innerText="密码不能小于6位";
            } else {
                userPasswordError.innerText="";
            }
        }
    </script>
</head>
<body>
    <form action="userServlet" method="post" onsubmit="return checkDate()">
        <input type="hidden" name="method" value="change"/><%--更新识别--%>
        用户名称：<input type="text" name="userName" id="userName" onblur="checkUserName()"/>
        <span id="userNameError"></span><br/>
        更新密码：<input type="text" name="userPassword" id="userPassword" onblur="checkUserPassword()"/>
        <span id="userPasswordError"></span><br/>
        <input type="submit" value="确定"/>
    </form>
</body>
</html>
