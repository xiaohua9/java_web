<%@ page import="com.learn.entity.User" %><%--
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
    <%//获取当前登录的用户对象
        User currentUser = (User) session.getAttribute("currentUser");
    %>

    <%
        //登录成功才会有currentUser,直接访问和登录失败，自然currentUser 为空
        if (currentUser==null){
    %>
          <script>location.href="/login/Login.jsp"</script>
    <%
        }else {//在登录成功的情况下，才能得到登录的页面
    %>
    <h1>当前登陆用户：<%=currentUser.getUserName() %></h1>
    <form action="userServlet" method="post" onsubmit="return checkDate()">
        <input type="hidden" name="method" value="change"/><%--更新识别--%>
        用户名称：<input type="text" name="userName" id="userName" onblur="checkUserName()"/>
        <span id="userNameError"></span><br/>
        更新密码：<input type="text" name="userPassword" id="userPassword" onblur="checkUserPassword()"/>
        <span id="userPasswordError"></span><br/>
        <input type="submit" value="确定"/>
    </form>
    <%
        }
    %>
</body>
</html>
