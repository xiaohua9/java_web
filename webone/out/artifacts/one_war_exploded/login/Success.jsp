<%@ page import="com.learn.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/15
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    <table border="1px" width="500px" height="100px">
        <tr>
            <td>用户名称</td><td>用户密码</td><td>操作</td>
        </tr>
        <%
            List<User> users = (List<User>)request.getAttribute("list");/*//获取服务器传来的会话的数据*/
            for (User user:users) {
        %>
        <tr>
            <td><%=user.getUserName()%></td>
            <td><%=user.getUserPassword()%></td>
            <td><a href="userServlet?method=delete&userName=<%=user.getUserName()%>">删除</a></td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="/login/AddUser.jsp" style="font-size: 50px">添加</a><br/>
    <a href="/login/UpdateUser.jsp" style="font-size: 50px">更改数据</a>
    <%--jsp真的太美妙了，让Java和HTML你中有我，我中有你，完美得融合在一起，然后取长补短--%>
    <%
        }
    %>
</body>
</html>
