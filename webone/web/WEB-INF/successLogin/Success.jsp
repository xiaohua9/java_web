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
    <h1>登陆成功</h1>
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
    <a href="/WEB-INF/LoginSuccess/addUser.jsp" style="font-size: 50px">添加</a><br/>
    <a href="/login/UpdateUser.jsp" style="font-size: 50px">更改数据</a>
    <%--jsp真的太美妙了，让Java和HTML你中有我，我中有你，完美得融合在一起，然后取长补短--%>
</body>
</html>
