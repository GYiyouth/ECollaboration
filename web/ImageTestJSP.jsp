<%--
  Created by IntelliJ IDEA.
  User: geyao
  Date: 2016/11/17
  Time: 下午6:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ImageTestJSP</title>
</head>
<body>
    <form action="uploadPhoto.action" enctype="multipart/form-data" method="post">
         <%--上传用户：<input type="text" name="username"><br/>--%>
         上传文件：<input type="file" name="file"><br/>
         <%--上传文件2：<input type="file" name="file2"><br/>--%>

         <input type="submit" value="提交">
    </form>
    <a href="downloadAction.action?fileName=QuickTime Player.jpg">下载</a>
</body>
</html>
