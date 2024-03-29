<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/23
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改信息</title>
    <script type="text/javascript" src="/js/checkData.js"></script>
    <style>
        #myTable{
            width: 1000px;
            font-size: 30px;
        }
        input{
            font-size: 30px;
        }
    </style>
</head>
<body style="background-color: lightyellow">
<h1 align="center">修改学生信息</h1>
<form action="StudentServlet" method="post" onsubmit="return checkData()">
    <input type="hidden" name="method" value="change"/><%--更新识别--%>
    <input type="hidden" name="studentNo" value="${param.studentNo}"/>
    <table border="1px" align="center" id="myTable">
        <tr>
            <td>姓名</td><td><input type="text" name="studentName" value="<%=new String(request.getParameter("studentName").getBytes("iso-8859-1"),"utf-8")%>" id="studentName" onblur="checkStudentName()"/></td><td id="studentNameError"></td>
        </tr>
        <tr>
            <td>年龄</td><td><input type="text" name="age" value="${param.age}" id="age" onblur="checkAge()"/></td><td id="ageError"></td>
        </tr>
        <tr>
            <td>出生日期</td><td><input type="text" name="boreDate" value="${param.boreDate}" id="boreDate" onblur="checkBoreDate()"/></td></td><td id="boreDateError"></td>
        </tr>
        <tr>
            <td>班级名称</td>
            <td>
                <select name="classNo">
                    <c:forEach items="${sessionScope.clazzes}" var="clazz">
                        <c:if test="${param.classNo==clazz.classNo}">
                            <option value="${clazz.classNo}" selected="selected">${clazz.className}</option>
                        </c:if>
                        <c:if test="${param.classNo!=clazz.classNo}">
                            <option value="${clazz.classNo}">${clazz.className}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td><td><span style="color: green">通过验证</span></td>
        </tr>
        <tr><td colspan="2" align="center">
            <input type="submit" value="修改学生"/>
            <input type="reset" value="重置"/>
        </td></tr>
    </table>
</form>
</body>
</html>
