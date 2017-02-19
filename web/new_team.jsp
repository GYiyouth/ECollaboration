<%--
  Created by IntelliJ IDEA.
  User: dingxiaotian
  Date: 17/1/2
  Time: 下午12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新建团队</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="files/new_team/styles.css" type="text/css" rel="stylesheet"/>
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
    <script src="files/new_team/data.js"></script>
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

    <!-- Unnamed (head) -->

    <!-- Unnamed (Image) -->
    <div id="u1" class="ax_image">
        <img id="u1_img" class="img " src="images/student_myteam/u1.png"/>
        <!-- Unnamed () -->
        <div id="u2" class="text">
            <p><span></span></p>
        </div>
    </div>
    <form action="createTeam.action" method="post" dir="ltr">
    <!-- Unnamed (形状) -->
    <div id="u3" class="ax_文本段落">

        <div id="u4" class="text">
            <p><span>团队名称：</span></p>
        </div>
    </div>

    <!-- Unnamed (文本框) -->
    <div id="u5" class="ax_文本框">

        <input name="teamName" type="text" value=""/>

    </div>

    <!-- Unnamed (形状) -->
    <div id="u6" class="ax_文本段落">
        <img id="u6_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u7" class="text">
            <p><span>团队最大成员数：</span></p>
        </div>
    </div>

    <!-- Unnamed (文本框) -->
    <div id="u8" class="ax_文本框">
        <input name="memberMax" type="text" value=""/>

    </div>

    <!-- Unnamed (形状) -->
    <div id="u9" class="ax_文本段落">
        <img id="u9_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u10" class="text">
            <p><span>团队描述：</span></p>
        </div>
    </div>

    <!-- Unnamed (多行文本框) -->
    <div id="u11" class="ax_多行文本框">
        <input name="description" type="text" width="150px" height="400px" value=""/>
    </div>

    <!-- Unnamed (提交按钮) -->
    <div id="u12" class="ax_提交按钮">
        <input id="u12_input" type="submit" value="提交"/>

    </div>
</form>
    <!-- Unnamed (tail) -->

    <!-- Unnamed (形状) -->
    <div id="u14" class="ax_形状">
        <img id="u14_img" class="img " src="images/student_myteam/u7.png"/>
        <!-- Unnamed () -->
        <div id="u15" class="text">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (水平线) -->
    <div id="u16" class="ax_水平线">
        <img id="u16_start" class="img " src="resources/images/transparent.gif" alt="u16_start"/>
        <img id="u16_end" class="img " src="resources/images/transparent.gif" alt="u16_end"/>
        <img id="u16_line" class="img " src="images/student_myteam/u9_line.png" alt="u16_line"/>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u17" class="ax_文本段落">
        <img id="u17_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u18" class="text">
            <p><span>© 2009 中国科学技术大学软件学院 </span></p>
        </div>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u19" class="ax_文本段落">
        <img id="u19_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u20" class="text">
            <p><span>合肥市徽州大道1129号&nbsp; &nbsp; &nbsp; </span></p>
        </div>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u21" class="ax_文本段落">
        <img id="u21_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u22" class="text">
            <p><span>邮编：230051</span></p>
        </div>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u23" class="ax_文本段落">
        <img id="u23_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u24" class="text">
            <p><span>苏州工业园区仁爱路166号</span></p>
        </div>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u25" class="ax_文本段落">
        <img id="u25_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u26" class="text">
            <p><span>邮编：215123</span></p>
        </div>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u27" class="ax_文本段落">
        <img id="u27_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u28" class="text">
            <p><span>邮箱：websse@ustc.edu.cn</span></p>
        </div>
    </div>
</div>
</body>
</html>
