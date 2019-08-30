<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/29
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改</title>
</head>
<h1>论坛修改</h1>
<body style="background-color: lightyellow">
<form action="PostInfoServlet" method="post" enctype="multipart/form-data" onsubmit="return infoCheckData()">
    <input type="hidden" name="method" value="change"/><%--更新识别--%>
    <input type="hidden" name="id" value="<%=request.getParameter("id") %>"/>
    <input type="hidden" name="clickNum" value="<%=request.getParameter("clickNum") %>"/>
    <input type="hidden" name="postTime" value="<%=request.getParameter("postTime") %>"/>

    <table border="1px" width="600px">
        <tr>
            <td>标题</td><td><input type="text" name="title" value="<%=new String(request.getParameter("title").getBytes("iso-8859-1"),"utf-8") %>" /></td>
        </tr>
        <tr>
            <td>帖子类别</td>
            <td>
                <select name="topicId">
                    <c:forEach items="${sessionScope.topics}" var="topic">
                        <c:if test="${topic.topicId==param.topicId}">
                            <option value="${topic.topicId}" selected="selected">${topic.topicName}</option>
                        </c:if>
                        <c:if test="${topic.topicId!=param.topicId}">
                            <option value="${topic.topicId}" >${topic.topicName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>帖子内容</td><td><textarea name="content"><%=new String(request.getParameter("content").getBytes("iso-8859-1"),"utf-8") %></textarea></td>
        </tr>
        <tr>
            <td>上传图片</td><td><input type="file" name="pic" /><img src="/upload/<%=request.getParameter("pic") %>" width="100px" height="100px"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="修改"/><input type="reset" value="重置"/></td>
        </tr>
    </table>
</form>
</body>
</html>
