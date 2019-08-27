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
    <script type="text/javascript" src="../javascript/InfoCheckData.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/LoginCss.css"  />
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
    <form action="userServlet" method="post" enctype="multipart/form-data" onsubmit="return infoCheckData()">
        <input type="hidden" name="method" value="change"/><%--更新识别--%>
        用户名称：<%=request.getParameter("userName")%><br/>
        <input type="hidden" name="userName" id="userName" value="<%=request.getParameter("userName")%>"/>
        更新密码：<input type="text" name="userPassword" id="userPassword"  onblur="checkUserPassword()"/>
        <span id="userPasswordError"></span><br/>
        用户性别：<input type="text" name="userGender" id="userGender" value="<%=new String(request.getParameter("userGender").getBytes("iso-8859-1"),"utf-8")%>" onblur="checkUserGender()"/>
        <span id="userGenderError"></span><br/>
        用户年龄：<input type="text" name="userAge" id="userAge" value="<%=request.getParameter("userAge")%>" onblur="checkUserAge()"/>
        <span id="userAgeError"></span><br/>
        用户地址：<input type="text" name="userAddress" id="userAddress" value="<%=new String(request.getParameter("userAddress").getBytes("iso-8859-1"),"utf-8")%>" onblur="checkUserAddress()"/>
        <span id="userAddressError"></span><br/>
        用户生日：<input type="text" name="userBirthday" id="userBirthday" value="<%=request.getParameter("userBirthday")%>" onblur="checkUserBirthday()"/>
        <span id="userBirthdayError"></span><br/>
        头像文件：<img src="/upload/<%=request.getParameter("pictureName") %>" width="100px" height="100px"/><br/>
        更新请上传：<input type="file" name="pictureName" id="pictureName"  >
        <span id="pictureNameError"></span><br/>
        <input type="submit" value="提交"/>
    </form>
    <%
        }
    %>
</body>
</html>
