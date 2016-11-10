<%--
  Created by IntelliJ IDEA.
  User: geyao
  Date: 2016/11/10
  Time: 上午9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LogIng</title>
</head>
<body>
<%
    int flag = Integer.parseInt(request.getAttribute("flag").toString());
%>
<script type="text/javascript">
    function success1()
    {
        alert("登陆成功！");
        window.location.href="log/teacher.jsp";
    }
    function failAccount()
    {
        alert("用户名或者密码输入错误");
        window.location.href="log/login.jsp";

    }
    function failSQL()
    {
        alert("数据库连接失败，请联系葛尧");
        window.location.href="log/login.jsp";
    }
    function failSign() {
        alert("注册失败");
        window.location.href="log_sign_game/signup.jsp"
    }
    <%if(flag==0){%>
    success1();
    <%}if(flag==1){%>
    failAccount();
    <%}if (flag==2){%>
    failSQL();
    <%}if (flag==3){%>
    failSign();
    <%}%>

</script>
</body>
</html>
