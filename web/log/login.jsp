<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">--%>
<html>
  <head>
    <title>login</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="./resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="./resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="./data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="./files/login/styles.css" type="text/css" rel="stylesheet"/>
    <script src="./resources/scripts/jquery-1.7.1.min.js"></script>
    <script src="./resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
    <script src="./resources/scripts/axure/axQuery.js"></script>
    <script src="./resources/scripts/axure/globals.js"></script>
    <script src="./resources/scripts/axutils.js"></script>
    <script src="./resources/scripts/axure/annotation.js"></script>
    <script src="./resources/scripts/axure/axQuery.std.js"></script>
    <script src="./resources/scripts/axure/doc.js"></script>
    <script src="./data/document.js"></script>
    <script src="./resources/scripts/messagecenter.js"></script>
    <script src="./resources/scripts/axure/events.js"></script>
    <script src="./resources/scripts/axure/action.js"></script>
    <script src="./resources/scripts/axure/expr.js"></script>
    <script src="./resources/scripts/axure/geometry.js"></script>
    <script src="./resources/scripts/axure/flyout.js"></script>
    <script src="./resources/scripts/axure/ie.js"></script>
    <script src="./resources/scripts/axure/model.js"></script>
    <script src="./resources/scripts/axure/repeater.js"></script>
    <script src="./resources/scripts/axure/sto.js"></script>
    <script src="./resources/scripts/axure/utils.temp.js"></script>
    <script src="./resources/scripts/axure/variables.js"></script>
    <script src="./resources/scripts/axure/drag.js"></script>
    <script src="./resources/scripts/axure/move.js"></script>
    <script src="./resources/scripts/axure/visibility.js"></script>
    <script src="./resources/scripts/axure/style.js"></script>
    <script src="./resources/scripts/axure/adaptive.js"></script>
    <script src="./resources/scripts/axure/tree.js"></script>
    <script src="./resources/scripts/axure/init.temp.js"></script>
    <script src="./files/login/data.js"></script>
    <script src="./resources/scripts/axure/legacy.js"></script>
    <script src="./resources/scripts/axure/viewer.js"></script>
    <script type="text/javascript">
      $axure.utils.getTransparentGifPath = function() { return './resources/images/transparent.gif'; };
      $axure.utils.getOtherPath = function() { return './resources/Other.html'; };
      $axure.utils.getReloadPath = function() { return './resources/reload.html'; };
    </script>
  </head>
  <body>
    <div id="base" class="">

      <!-- Unnamed (形状) -->
      <div id="u0" class="ax_形状">
        <img id="u0_img" class="img " src="./images/login/u0.png"/>
        <!-- Unnamed () -->
        <div id="u1" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u2" class="ax_形状">
        <img id="u2_img" class="img " src="./images/login/u2.png"/>
        <!-- Unnamed () -->
        <div id="u3" class="text">
          <p><span>欢迎登录</span><span>中</span><span>科大工程实践平台</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u4" class="ax_文本段落">
        <img id="u4_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u5" class="text">
          <p><span>邮箱</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u6" class="ax_文本段落">
        <img id="u6_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u7" class="text">
          <p><span>密码</span></p>
        </div>
      </div>

      <!-- exemail (形状) -->
      <div id="u8" class="ax_形状" data-label="exemail">
        <img id="u8_img" class="img " src="./images/login/exemail_u8.png"/>
        <!-- Unnamed () -->
        <div id="u9" class="text">
          <p><span></span></p>
        </div>
      </div>
<form action="${pageContext.request.contextPath}/servlet.log.LogInServlet" method="post">
      <!-- email (文本框) -->
      <div id="u10" class="ax_文本框" data-label="email">
        <input name="logName" id="u10_input" type="email" value=""/>
      </div>

      <!-- test1 (形状) -->
      <div id="u11" class="ax_文本段落" data-label="test1">
        <img id="u11_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u12" class="text">
          <p><span>请输入正确邮箱</span></p>
        </div>
      </div>

      <!-- exepassword (形状) -->
      <div id="u13" class="ax_形状" data-label="exepassword">
        <img id="u13_img" class="img " src="./images/login/exemail_u8.png"/>
        <!-- Unnamed () -->
        <div id="u14" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- password (文本框) -->
      <div id="u15" class="ax_文本框" data-label="password">
        <input name="passWord" id="u15_input" type="password" value=""/>
      </div>

      <!-- test1 (形状) -->
      <div id="u16" class="ax_文本段落" data-label="test1">
        <img id="u16_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u17" class="text">
          <p><span>请</span><span>输入正确密码</span></p>
        </div>
      </div>

      <!-- chongzhi (Image) -->
      <div id="u18" class="ax_image" data-label="chongzhi">
        <img id="u18_img" class="img " src="./images/login/chongzhi_u18.png"/>
        <!-- Unnamed () -->
        <div id="u19" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (head) -->

      <!-- Unnamed (形状) -->
      <div id="u21" class="ax_形状">
        <img id="u21_img" class="img " src="./images/teacher-1-1/u1.png"/>
        <!-- Unnamed () -->
        <div id="u22" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u23" class="ax_形状">
        <img id="u23_img" class="img " src="./images/teacher-1-1/u3.png"/>
        <!-- Unnamed () -->
        <div id="u24" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u25" class="ax_文本段落">
        <img id="u25_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u26" class="text">
          <p><span>c</span><span>ollaboration</span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u27" class="ax_image">
        <img id="u27_img" class="img " src="./images/teacher-1-1/u7.png"/>
        <!-- Unnamed () -->
        <div id="u28" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (tail) -->

      <!-- Unnamed (形状) -->
      <div id="u30" class="ax_形状">
        <img id="u30_img" class="img " src="./images/teacher-1-1/u73.png"/>
        <!-- Unnamed () -->
        <div id="u31" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (水平线) -->
      <div id="u32" class="ax_水平线">
        <img id="u32_start" class="img " src="./resources/images/transparent.gif" alt="u32_start"/>
        <img id="u32_end" class="img " src="./resources/images/transparent.gif" alt="u32_end"/>
        <img id="u32_line" class="img " src="./images/teacher-1-1/u75_line.png" alt="u32_line"/>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u33" class="ax_文本段落">
        <img id="u33_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u34" class="text">
          <p><span>© 2009 中国科学技术大学软件学院 </span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u35" class="ax_文本段落">
        <img id="u35_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u36" class="text">
          <p><span>合肥市徽州大道1129号&nbsp; &nbsp; &nbsp; </span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u37" class="ax_文本段落">
        <img id="u37_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u38" class="text">
          <p><span>邮编：230051</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u39" class="ax_文本段落">
        <img id="u39_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u40" class="text">
          <p><span>苏州工业园区仁爱路166号</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u41" class="ax_文本段落">
        <img id="u41_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u42" class="text">
          <p><span>邮编：215123</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u43" class="ax_文本段落">
        <img id="u43_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u44" class="text">
          <p><span>邮箱：websse@ustc.edu.cn</span></p>
        </div>
      </div>

      <!-- Unnamed (提交按钮) -->
      <div id="u45" class="ax_提交按钮">
        <input id="u45_input" type="submit" value="登录"/>
      </div>
</form>
    </div>
  </body>
</html>
