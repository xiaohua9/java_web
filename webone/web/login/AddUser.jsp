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
    <script>
        //表单验证方法
        function checkDate() {
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
        //用户名的失去焦点验证
        function checkUserName() {
            if (userName.value.length<1){
                userNameError.style.color="red";
                userNameError.innerText="用户名不能为空";
            } else {
                userNameError.innerText="";
            }
        }
        //用户密码的失去焦点验证
        function checkUserPassword() {
            if (userPassword.value.length<6){
                userPasswordError.style.color="red";
                userPasswordError.innerText="密码不能小于6位";
            }else {
                userPasswordError.innerText="";
            }
        }
    </script>
</head>
<body>
    <form action="userServlet" method="post" onsubmit="return checkDate()">
        <input type="hidden" name="method" value="add"/><%--添加识别--%>
        用户名称：<input type="text" name="userName" id="userName" onblur="checkUserName()"/>
        <span id="userNameError"></span><br/>
        用户密码：<input type="text" name="userPassword" id="userPassword" onblur="checkUserPassword()"/>
        <span id="userPasswordError"></span><br/>
        用户性别：<input type="text" name="userGender" id="userGender" onblur="checkUserGender()"/><br/>
        用户年龄：<input type="text" name="userAge" id="userAge" onblur="checkUserAge()"/><br/>
        用户地址：<input type="text" name="userAddress" id="userAddress" onblur="checkUserAddress()"/><br/>
        用户生日：<input type="text" name="userBirthday" id="userBirthday" onblur="checkUserBirthday()"/><br/>
        <input type="submit" value="提交"/>
        <input type="reset" value="重置"/>
    </form>
</body>
</html>
