<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/23
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--引进标签库--%>
<html>
<head>
    <title>学生信息</title>
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
    <style>
        #addStudent{
            z-index: 2;
            position: absolute;
            top: 125px;
            left: 300px;
        }
    </style>
</head>
<body>
<%--//空的话 ，去访问查全部组件--%>
<c:if test="${ empty requestScope.pageBean}">
    <script>location.href="/view/StudentServlet"</script>
</c:if>
<h1>学生信息列表</h1>
<h2>共${requestScope.pageBean.rows}行,共${requestScope.pageBean.totalPages}页,当前在第${requestScope.pageBean.currentPage}页</h2>
<form action="StudentServlet" method="post" id="queryForm"><%--使用一个表单实现分页选择的数据提交--%>
    <input type="hidden" name="page" id="page" value="1"/>
    请选择班级:
    <select name="classNo">
        <option value="">全部</option>
        <c:forEach items="${sessionScope.clazzes}" var="clazz">
            <c:if test="${clazz.classNo==requestScope.currentClassNo}">
                <option value="${clazz.classNo}" selected="selected">${clazz.className}</option>
            </c:if>
            <c:if test="${clazz.classNo!=requestScope.currentClassNo}">
                <option value="${clazz.classNo}">${clazz.className}</option>
            </c:if>
        </c:forEach>
    </select>
    <input type="submit" value="查询"/>
</form>
<a href="/view/AddStudent.jsp" id="addStudent">添加学生信息</a> <br/><br/>
<table border="2px" width="800px">
    <tr>
        <td>学号</td><td>姓名</td><td>年龄</td><td>出生日期</td><td>班级名称</td><td>删除</td><td>修改</td><td>详情</td>
    </tr>
    <c:forEach items="${requestScope.pageBean.student}" var="student" varStatus="stat">
        <c:if test="${stat.index%2==0}">
            <tr style="background-color: lightgreen">
                <td>${student.studentNo}</td>
                <td>${student.studentName}</td>
                <td>${student.age}</td>
                <td>${student.boreDate}</td>
                <td>
                    <c:forEach items="${sessionScope.clazzes}" var="clazz">
                        <c:if test="${student.classNo==clazz.classNo}">
                            ${clazz.className}
                        </c:if>
                    </c:forEach>
                </td>
                <td><a href="StudentServlet?method=delete&studentNo=${student.studentNo}">删除</a></td>
                <td><a href="/view/ChangeStudent.jsp?studentNo=${student.studentNo}&studentName=${student.studentName}&age=${student.age}&boreDate=${student.boreDate}&classNo=${student.classNo}" >修改</a></td>
                <td><a href="/view/OneStudent.jsp?studentNo=${student.studentNo}&studentName=${student.studentName}&age=${student.age}&boreDate=${student.boreDate}&classNo=${student.classNo}">详细</a></td>
            </tr>
        </c:if>
        <c:if test="${stat.index%2!=0}">
            <tr>
                <td>${student.studentNo}</td>
                <td>${student.studentName}</td>
                <td>${student.age}</td>
                <td>${student.boreDate}</td>
                <td>
                    <c:forEach items="${sessionScope.clazzes}" var="clazz">
                        <c:if test="${student.classNo==clazz.classNo}">
                            ${clazz.className}
                        </c:if>
                    </c:forEach>
                </td>
                <td><a href="StudentServlet?method=delete&studentNo=${student.studentNo}">删除</a></td>
                <td> <a href="/view/ChangeStudent.jsp?studentNo=${student.studentNo}&studentName=${student.studentName}&age=${student.age}&boreDate=${student.boreDate}&classNo=${student.classNo}" >修改</a></td>
                <td><a href="/view/OneStudent.jsp?studentNo=${student.studentNo}&studentName=${student.studentName}&age=${student.age}&boreDate=${student.boreDate}&classNo=${student.classNo}">详细</a></td>
            </tr>
        </c:if>
    </c:forEach>
    <tr><td colspan="8" align="center">
        <a  href="javascript:void(0)"  onclick="go(1)">首页</a>
        <a  href="javascript:void(0)"  onclick="go(${requestScope.pageBean.currentPage-1})">上一页</a>
        <a  href="javascript:void(0)"  onclick="go(${requestScope.pageBean.currentPage+1})">下一页</a>
        <a  href="javascript:void(0)"  onclick="go(${requestScope.pageBean.totalPages})">尾页</a>
        <input type="text" id="goto" value="${requestScope.pageBean.currentPage}" style="width: 70px"/><input type="button" value="跳转" onclick="goPage()"/>
    </td></tr>
</table>
</body>
</html>
