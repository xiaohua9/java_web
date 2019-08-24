<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/23
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
</head>
<body>
<h1 align="center">学生信息详情</h1>
    <table border="1px" align="center">
        <tr>
            <td>编号</td><td>${param.studentNo}</td>
        </tr>
        <tr>
            <td>姓名</td><td><%=new String(request.getParameter("studentName").getBytes("iso-8859-1"),"utf-8")%></td>
        </tr>
        <tr>
            <td>年龄</td><td>${param.age}</td>
        </tr>
        <tr>
            <td>出生日期</td><td>${param.boreDate}</td>
        </tr>
        <tr>
            <td>班级名称</td>
            <td>
                <c:forEach items="${sessionScope.clazzes}" var="clazz" >
                    <c:if test="${clazz.classNo==param.classNo}">
                        ${clazz.className}
                    </c:if>
                </c:forEach>
            </td>
        </tr>
        <tr><td colspan="2" align="center">
            <a href="StudentServlet">返回首页</a>
        </td></tr>
    </table>
</body>
</html>
