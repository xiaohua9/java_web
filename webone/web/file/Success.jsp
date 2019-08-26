<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/26
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${requestScope.newFileNames}" var="newFileName">
    <img src="/upload/${newFileName}" width="50px" height="50px"><a href="DownloadServlet?fileName=${newFileName}">下载</a>
</c:forEach>
</body>
</html>
