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
    <title>发帖</title>
    <script type="text/javascript" src="/js/jquery-3.2.1.js" ></script>
    <script type="text/javascript">
        $(function () {
            $("#picFile").change(function () {
                $(this).val();
                //在文件表单值改变的时候处理
            });
        });
    </script>
</head>
<body style="background-color: lightyellow">
<h1>论坛发帖</h1>
<form action="PostInfoServlet" method="post" enctype="multipart/form-data" onsubmit="return infoCheckData()">
    <input type="hidden" name="method" value="add"/><%--添加识别--%>
    <input type="hidden" name="clickNum" value="0"/>
    <table border="1px" width="600px">
        <tr>
            <td>标题</td><td><input type="text" name="title" /></td>
        </tr>
        <tr>
            <td>帖子类别</td>
            <td>
                <select name="topicId">
                    <c:forEach items="${sessionScope.topics}" var="topic">
                        <option value="${topic.topicId}">${topic.topicName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>帖子内容</td><td><textarea name="content"></textarea></td>
        </tr>
        <tr>
            <td>发布时间</td><td><input type="text" name="postTime"/></td>
        </tr>
        <tr>
            <td>上传图片</td><td><input type="file" value="" name="pic" id="picFile" /><img src="" id="picSrc" width="100px" height="100px" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="发帖"/><input type="reset" value="重置"/></td>
        </tr>
    </table>
</form>
</body>
</html>