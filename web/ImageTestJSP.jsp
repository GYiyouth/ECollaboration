<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <form action="uploadPhoto.action" enctype="multipart/form-data" method="post" >
         <%--上传用户：<input type="text" name="username"><br/>--%>
         上传文件：<input type="file" name="file"
                     accept="image/jpeg, image/png"
         ><br/>
         <%--上传文件2：<input type="file" name="file2"><br/>--%>
             <%--<input name="projectId" value="1" hidden="hidden">--%>
             <%--<input name="teamId" value="1" hidden="hidden">--%>
         <input type="submit" value="提交">
    </form>
    <a href="downloadAction.action?fileName=QuickTime Player.jpg">下载</a>

    <s:form action="logIn" method="post">
        <s:textfield name="userName" label="username"></s:textfield><br>
        <s:textfield name="passWord" label="password"></s:textfield><br>
        <s:submit value="登录"></s:submit>
    </s:form>
</body>
</html>
