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
    <form action="${pageContext.request.contextPath}/servlet.test.ImageUploadTestServlet" enctype="multipart/form-data" method="post">
         上传用户：<input type="text" name="username"><br/>
         上传文件1：<input type="file" name="file1"><br/>
         上传文件2：<input type="file" name="file2"><br/>

         <input type="submit" value="提交">
    </form>
</body>
</html>
