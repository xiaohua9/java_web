<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/30
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax请求练习</title>
    <script type="text/javascript" src="/javascript/jquery-3.2.1.js"></script>
    <script>
        $(function () {
            $("#selectAll").click(function () {
                $.post("SelectAllServlet",function (data) {
                    var str="<tr>"+"<td>"+"用户名"+"</td>"+"<td>"+"密码"+"</td>"+"<td>"+"性别"+"</td>"+"<td>"+"年龄"+"</td>"+"<td>"+"地址"+"</td>"+"<td>"+"生日"+"</td>"+"<td>"+"头像"+"</td>"+"<tr/>";
                    $.each(data,function (index,obj) {/*将Ajax回应的数据动态生成表格*/
                        str=str+"<tr>"+"<td>"+obj.userName+"</td>"+"<td>"+obj.userPassword+"</td>"+"<td>"+obj.userGender+"</td>"+"<td>"+obj.userAge+"</td>"+"<td>"+obj.userAddress+"</td>"+"<td>"+obj.userBirthday+"</td>"+"<td>"+"<img width='30px' height='30px' src='"+/upload/+obj.pictureName+"'>"+"</td>"+"<tr/>";
                    });
                    $("#msg").html(str);/*动态写入*/
                },"json");//此处的json是将回应的数据定义成json对象，因为你即便是从服务端拿了json数据，但经由网络传播后只是单纯的字符串
            });
        });
    </script>
</head>
<body>
    <a href="javascript:void (0)" id="selectAll">查所有</a>
    <table id="msg" border="1px" width="1000px"></table>
</body>
</html>
