<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/15
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <script type="text/javascript">
        //表单提交验证方法
        function checkDate() {
            var flag=true;
            if (userName.value.length <1 ){
                flag=false;//用户名为空时报错
                error1.style.color="red";
                error1.innerText="用户名不能为空";
            }
            if (userPwd.value.length < 6){
                flag=false;//密码小于6位时报错
                error2.style.color="red";
                error2.innerText="密码不能小于6位";
            }
            return flag;
        }
        //用户名的失去焦点验证
        function checkUserName() {
            if (userName.value.length<1) {
                error1.style.color="red";
                error1.innerText="用户名不能为空";
            }else {
                error1.innerText="";
            }
        }
        //用户密码的失去焦点验证
        function checkUserPwd() {
            if (userPwd.value.length<6) {
                error2.style.color="red";
                error2.innerText="密码不能小于6位";
            }else {
                error2.innerText="";
            }

        }
    </script>
</head>
<body>
    <form action="userServlet" method="post" onsubmit="return checkDate()">
        <input type="hidden" name="method" value="login"/><%--登录识别--%>
        用户名称：<input type="text" name="userName" placeholder="a" id="userName" onblur="checkUserName()"/>
        <span id="error1" ></span><br/>
        用户密码：<input type="password" name="userPwd" placeholder="aaaaaa" id="userPwd" onblur="checkUserPwd()"/>
        <span id="error2"></span><br/><br/>
        <input type="submit" value="登录"/>
        <input type="reset" value="重置"/>
    </form>
    <a href="/login/addUser.jsp">注册</a>
</body>
</html>
<%--表单验证存在的理由在于：可以分担一些后端服务器的运算压力--%>
<%--表单验证的思路（如何让用户的体验更加优质）：首先在每一个表单提交项失去焦点时根据判断规则给与提示，
最后在表单提交时就不用给提示了，直接根据已定的规则判断是否让整个表单他提交到服务器--%>