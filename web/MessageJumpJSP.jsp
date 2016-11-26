<%--
  Created by IntelliJ IDEA.
  User: geyao
  Date: 2016/11/17
  Time: 下午7:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String message = request.getAttribute("message").toString();
    %>
    <title><%=message%></title>
</head>
<body>

    <script type="text/javascript">
        function myAlert() {
            var messageAlert = "<%=message%>";
            alert(messageAlert)
        }
    </script>
    <script type="text/javascript">
        myAlert();
    </script>
    <%=message%>
</body>
</html>
