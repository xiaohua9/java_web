<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/15
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--引进标签库--%>
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
    <%--没有经过登录就被转到登录页面--%>
    <c:if test="${empty sessionScope.currentUser}" >
        <script>location.href="/login/Login.jsp"</script>
    </c:if>
    <%-- 在登录成功的情况下，才能得到登录之后的页面--%>
    <c:if test="${!empty sessionScope.currentUser}">
        <h1>当前登陆用户：${sessionScope.currentUser.userName}</h1>
        <h2>共${requestScope.pageBean.rows}行,共${requestScope.pageBean.totalPages}页,当前在第${requestScope.pageBean.currentPage}页</h2>
        <form action="userServlet" method="post" id="queryForm"><%--使用一个表单实现分页选择的数据提交--%>
            <input type="hidden" name="page" id="page" value="1"/>
            请选择地址:
            <select name="userAddress">
                <option value="">全部</option>
                <c:forEach items="${requestScope.userAddress}" var="addr">
                    <c:if test="${addr==requestScope.currentUserAddress}">
                        <option selected="selected">${addr}</option>
                    </c:if>
                    <c:if test="${addr!=requestScope.currentUserAddress}">
                        <option >${addr}</option>
                    </c:if>
                </c:forEach>
            </select>
            <input type="submit" value="确定"/>
        </form>
        <a  href="javascript:void(0)"  onclick="go(1)">首页</a>
        <a  href="javascript:void(0)"  onclick="go(${requestScope.pageBean.currentPage-1})">上一页</a>
        <a  href="javascript:void(0)"  onclick="go(${requestScope.pageBean.currentPage+1})">下一页</a>
        <a  href="javascript:void(0)"  onclick="go(${requestScope.pageBean.totalPages})">尾页</a>
        <input type="text" id="goto" value="${requestScope.pageBean.currentPage}" style="width: 70px"/><input type="button" value="跳转" onclick="goPage()"/>
        <br/><br/>
        <table border="2px" width="800px">
            <tr>
                <td>用户名称</td><td>用户密码</td><td>用户性别</td><td>用户年龄</td><td>用户地址</td><td>用户生日</td><td>操作</td>
            </tr>
            <c:forEach items="${requestScope.pageBean.user}" var="user" varStatus="stat">
                <c:if test="${stat.index%2==0}">
                    <tr style="background-color: lightgreen">
                        <td>${user.userName}</td>
                        <td>${user.userPassword}</td>
                        <td>${user.userGender}</td>
                        <td>${user.userAge}</td>
                        <td>${user.userAddress}</td>
                        <td>${user.userBirthday}</td>
                        <td><a href="userServlet?method=delete&userName=${user.userName}">删除</a>
                            <a href="/login/UpdateUser.jsp?userName=${user.userName}&userPassword=${user.userPassword}&userGender=${user.userGender}&userAge=${user.userAge}&userAddress=${user.userAddress}&userBirthday=${user.userBirthday}" >修改</a></td>
                    </tr>
                </c:if>
                <c:if test="${stat.index%2!=0}">
                    <tr >
                        <td>${user.userName}</td>
                        <td>${user.userPassword}</td>
                        <td>${user.userGender}</td>
                        <td>${user.userAge}</td>
                        <td>${user.userAddress}</td>
                        <td>${user.userBirthday}</td>
                        <td><a href="userServlet?method=delete&userName=${user.userName}">删除</a>
                            <a href="/login/UpdateUser.jsp?userName=${user.userName}&userPassword=${user.userPassword}&userGender=${user.userGender}&userAge=${user.userAge}&userAddress=${user.userAddress}&userBirthday=${user.userBirthday}" >修改</a></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        <a href="/login/AddUser.jsp" style="font-size: 50px">添加</a><br/>
        <%--jsp真的太美妙了，让Java和HTML你中有我，我中有你，完美得融合在一起，然后取长补短--%>
    </c:if>
</body>
</html>
