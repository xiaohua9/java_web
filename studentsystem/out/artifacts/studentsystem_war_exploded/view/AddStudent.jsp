<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/23
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>增加学生</title>
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
<h1 align="center">添加学生信息</h1>
<form action="StudentServlet" method="post" onsubmit="return checkData()">
    <input type="hidden" name="method" value="add"/><%--添加识别--%>
    <table border="1px" align="center" id="myTable">
        <tr>
            <td>姓名</td><td><input type="text" name="studentName" id="studentName" onblur="checkStudentName()"/></td><td><span id="studentNameError"></span></td>
        </tr>
        <tr>
            <td>年龄</td><td><input type="text" name="age" id="age" onblur="checkAge()"/></td><td><span id="ageError"></span></td>
        </tr>
        <tr>
            <td>出生日期</td><td><input type="text" name="boreDate" id="boreDate" onblur="checkBoreDate()"/></td><td><span id="boreDateError"></span></td>
        </tr>
        <tr>
            <td>班级名称</td>
            <td>
                <select name="classNo">
                    <c:forEach items="${sessionScope.clazzes}" var="clazz">
                        <option value="${clazz.classNo}">${clazz.className}</option>
                    </c:forEach>
                </select>
            </td><td><span style="color: green">通过验证</span></td>
        </tr>
        <tr><td colspan="3" align="center">
            <input type="submit" value="添加学生"/>
            <input type="reset" value="重置"/>
        </td></tr>
    </table>
</form>
</body>
</html>
