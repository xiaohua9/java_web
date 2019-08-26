<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/26
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件操作</title>
    <script>
        function add() {/*动态添加标签*/
            var mdd = document.createElement("dd");
            var minput=document.createElement("input");
            minput.type="file";
            minput.name="pic";
            var elementById = document.getElementById("dll");
            mdd.appendChild(minput);
            elementById.appendChild(mdd);
        }
        function del() {/*动态删除标签*/
            var elementsByName = document.getElementsByName("pic");
            elementsByName[elementsByName.length-1].parentElement.remove();
        }
    </script>
</head>
<body>
    <form action="UploadServlet" method="post" enctype="multipart/form-data">
        <dl id="dll">
            <dt style="font-size: 50px">上传文件</dt>
            <dd><input type="file" name="pic"/></dd>
        </dl>
        <input type="button" value="添加" onclick="add()"/>
        <input type="button" value="删除最后一个" onclick="del()" />
        <input type="submit" value="上传" style="font-size: 30px"/>
    </form>
</body>
</html>
