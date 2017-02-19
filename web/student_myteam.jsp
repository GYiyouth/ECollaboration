<%--
  Created by IntelliJ IDEA.
  User: dingxiaotian
  Date: 17/1/2
  Time: 下午2:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>学生查看个人团队</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="files/student_myteam/styles.css" type="text/css" rel="stylesheet"/>
    <script src="resources/scripts/jquery-1.7.1.min.js"></script>
    <script src="resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
    <script src="resources/scripts/axure/axQuery.js"></script>
    <script src="resources/scripts/axure/globals.js"></script>
    <script src="resources/scripts/axutils.js"></script>
    <script src="resources/scripts/axure/annotation.js"></script>
    <script src="resources/scripts/axure/axQuery.std.js"></script>
    <script src="resources/scripts/axure/doc.js"></script>
    <script src="data/document.js"></script>
    <script src="resources/scripts/messagecenter.js"></script>
    <script src="resources/scripts/axure/events.js"></script>
    <script src="resources/scripts/axure/action.js"></script>
    <script src="resources/scripts/axure/expr.js"></script>
    <script src="resources/scripts/axure/geometry.js"></script>
    <script src="resources/scripts/axure/flyout.js"></script>
    <script src="resources/scripts/axure/ie.js"></script>
    <script src="resources/scripts/axure/model.js"></script>
    <script src="resources/scripts/axure/repeater.js"></script>
    <script src="resources/scripts/axure/sto.js"></script>
    <script src="resources/scripts/axure/utils.temp.js"></script>
    <script src="resources/scripts/axure/variables.js"></script>
    <script src="resources/scripts/axure/drag.js"></script>
    <script src="resources/scripts/axure/move.js"></script>
    <script src="resources/scripts/axure/visibility.js"></script>
    <script src="resources/scripts/axure/style.js"></script>
    <script src="resources/scripts/axure/adaptive.js"></script>
    <script src="resources/scripts/axure/tree.js"></script>
    <script src="resources/scripts/axure/init.temp.js"></script>
    <script src="files/student_myteam/data.js"></script>
    <script src="resources/scripts/axure/legacy.js"></script>
    <script src="resources/scripts/axure/viewer.js"></script>
    <script type="text/javascript">
        $axure.utils.getTransparentGifPath = function() { return 'resources/images/transparent.gif'; };
        $axure.utils.getOtherPath = function() { return 'resources/Other.html'; };
        $axure.utils.getReloadPath = function() { return 'resources/reload.html'; };
    </script>
</head>
<body>
<div id="base" class="">
    </br>
    </br>
    </br>
    </br>
    </br>
    <div>
    <table border="1" align="center">
        <tr>
            <td align="center">团队名称</td>
            <td align="center">创建日期</td>
            <td align="center">最大人数</td>

        </tr>
        <s:iterator value="teamBeans" >
            <tr>
                <td align="center" ><a href="<s:url action='getTeamInfoByTeamId?teamId=%{id}'/>"><s:property value="teamName"/></a></td>
                <td align="center"><s:property value="createDate"/></td>
                <td align="center"><s:property value="memberMax"/></td>
            </tr>
        </s:iterator>
    </table>
    </div>
    <form action="createTeam" method="post" dir="ltr">
    <!-- Unnamed (提交按钮) -->
    <div id="u5" class="ax_提交按钮">
        <a href="new_team.jsp"><input id="u5_input" type="button" value="新建团队"/></a>
    </div>

    <!-- Unnamed (tail) -->

    <!-- Unnamed (形状) -->
    <div id="u7" class="ax_形状">
        <img id="u7_img" class="img " src="images/student_myteam/u7.png"/>
        <!-- Unnamed () -->
        <div id="u8" class="text">
            <p><span></span></p>
        </div>
    </div>
    </form>
    <!-- Unnamed (水平线) -->
    <div id="u9" class="ax_水平线">
        <img id="u9_start" class="img " src="resources/images/transparent.gif" alt="u9_start"/>
        <img id="u9_end" class="img " src="resources/images/transparent.gif" alt="u9_end"/>
        <img id="u9_line" class="img " src="images/student_myteam/u9_line.png" alt="u9_line"/>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u10" class="ax_文本段落">
        <img id="u10_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u11" class="text">
            <p><span>© 2009 中国科学技术大学软件学院 </span></p>
        </div>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u12" class="ax_文本段落">
        <img id="u12_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u13" class="text">
            <p><span>合肥市徽州大道1129号&nbsp; &nbsp; &nbsp; </span></p>
        </div>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u14" class="ax_文本段落">
        <img id="u14_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u15" class="text">
            <p><span>邮编：230051</span></p>
        </div>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u16" class="ax_文本段落">
        <img id="u16_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u17" class="text">
            <p><span>苏州工业园区仁爱路166号</span></p>
        </div>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u18" class="ax_文本段落">
        <img id="u18_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u19" class="text">
            <p><span>邮编：215123</span></p>
        </div>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u20" class="ax_文本段落">
        <img id="u20_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u21" class="text">
            <p><span>邮箱：websse@ustc.edu.cn</span></p>
        </div>
    </div>
</div>
</body>
</html>
