<%@ page import="com.learn.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.learn.utils.PageBean" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/15
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
    <link rel="stylesheet" type="text/css" href="../css/LoginCss.css"  />
    <script type="text/javascript">
        /*首页，尾页，上一页，下一页*/
        function go(p) {
            page.value=p;
            queryForm.submit();
        }
        /*跳转页面方法*/
        function goPage() {
            page.value=goto.value;
            queryForm.submit();
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
        }else { //在登录成功的情况下，才能得到登录的页面
    %>
    <h1>当前登陆用户：<%=currentUser.getUserName() %></h1>
    <%
        //获取数据的页面显示信息
        PageBean pageBean = (PageBean)request.getAttribute("pageBean");
    %>
    <h2>共<%=pageBean.getRows() %>行,共<%=pageBean.getTotalPages() %>页,当前在第<%=pageBean.getCurrentPage() %>页</h2>
    <%
        List<String> userAddress = (List<String>) request.getAttribute("userAddress");//获取地址集合，用于条件选择
    %>
    <form action="userServlet" method="post" id="queryForm"><%--使用一个表单实现分页选择的数据提交--%>
        <input type="hidden" name="page" id="page" value="1"/>
        请选择地址:
        <select name="userAddress">
            <option value="">全部</option>
            <%
            for (String addr:userAddress){
                if (addr.equals(request.getAttribute("currentUserAddress"))){
            %>
                    <option selected="selected"><%=addr %></option>
            <%
                }else {
            %>
            <option><%=addr %></option>
            <%
                }
                }
            %>
        </select>
        <input type="submit" value="确定"/>
    </form>
    <a  href="javascript:void(0)"  onclick="go(1)">首页</a>
    <a  href="javascript:void(0)"  onclick="go(<%=pageBean.getCurrentPage()-1  %>)">上一页</a>
    <a  href="javascript:void(0)"  onclick="go(<%=pageBean.getCurrentPage()+1 %>)">下一页</a>
    <a  href="javascript:void(0)"  onclick="go(<%=pageBean.getTotalPages() %>)">尾页</a>
    <input type="text" id="goto" value="<%=pageBean.getCurrentPage() %>" style="width: 70px"/><input type="button" value="跳转" onclick="goPage()"/>
    <br/><br/>
    <table border="2px" width="800px">
        <tr>
            <td>用户名称</td><td>用户密码</td><td>用户性别</td><td>用户年龄</td><td>用户地址</td><td>用户生日</td><td>操作</td>
        </tr>
        <%
            List<User> users =(List<User>)pageBean.getUser();/*//获取服务器传来的请求的数据*/
            for (User user:users) {
        %>
        <tr>
            <td><%=user.getUserName()%></td>
            <td><%=user.getUserPassword()%></td>
            <td><%=user.getUserGender()%></td>
            <td><%=user.getUserAge()%></td>
            <td><%=user.getUserAddress()%></td>
            <td><%=user.getUserBirthday()%></td>
            <td><a href="userServlet?method=delete&userName=<%=user.getUserName()%>">删除</a>
                <a href="/login/UpdateUser.jsp?userName=<%=user.getUserName()%>&userPassword=<%=user.getUserPassword()%>&userGender=<%=user.getUserGender()%>&userAge=<%=user.getUserAge()%>&userAddress=<%=user.getUserAddress()%>&userBirthday=<%=user.getUserBirthday()%>" >修改</a></td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="/login/AddUser.jsp" style="font-size: 50px">添加</a><br/>
    <%--jsp真的太美妙了，让Java和HTML你中有我，我中有你，完美得融合在一起，然后取长补短--%>
    <%
        }
    %>
</body>
</html>
