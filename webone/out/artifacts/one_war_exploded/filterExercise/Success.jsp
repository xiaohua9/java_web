<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/28
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
在线人数：${num}<br/>
 姓名：${param.name}<br/>
<%
    Integer hitsCount=(Integer) application.getAttribute("hitsCount");
    if (hitsCount==null){
        hitsCount=1;
    }else {
        hitsCount++;
    }
    application.setAttribute("hitsCount",hitsCount);
%>
当前页面的点击数:${applicationScope.hitsCount}
</body>
</html>
