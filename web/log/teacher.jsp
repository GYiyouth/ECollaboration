<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">--%>
<html>
  <head>
    <%

      String path = request.getContextPath();
      String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//      HttpSession session = request.getSession();
      String photo = "/WEB-INF/upload/userBean/photo/u27.jpg";
      Object sessionValueName = session.getAttribute("name");
      Object sessionValueStaffId = session.getAttribute("schoolId");
      String name = sessionValueName.toString();
      String staffId = sessionValueStaffId.toString();
    %>
    <title>teacher</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="./resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="./resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="./data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="./files/teacher/styles.css" type="text/css" rel="stylesheet"/>
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
    <script src="./files/teacher/data.js"></script>
    <script src="./resources/scripts/axure/legacy.js"></script>
    <script src="./resources/scripts/axure/viewer.js"></script>
    <script type="text/javascript">
      $axure.utils.getTransparentGifPath = function() { return './log/resources/images/transparent.gif'; };
      $axure.utils.getOtherPath = function() { return './log/resources/Other.html'; };
      $axure.utils.getReloadPath = function() { return './log/resources/reload.html'; };
    </script>
  </head>
  <body>
    <div id="base" class="">

      <!-- Unnamed (head) -->

      <!-- Unnamed (形状) -->
      <div id="u1" class="ax_形状">
        <img id="u1_img" class="img " src="./images/teacher-1-1/u1.png"/>
        <!-- Unnamed () -->
        <div id="u2" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u3" class="ax_形状">
        <img id="u3_img" class="img " src="./images/teacher-1-1/u3.png"/>
        <!-- Unnamed () -->
        <div id="u4" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u5" class="ax_文本段落">
        <img id="u5_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u6" class="text">
          <p><span>c</span><span>ollaboration</span></p>
        </div>
      </div>

      <!-- Unnamed (Image) -->
      <div id="u7" class="ax_image">
        <img id="u7_img" class="img " src="./images/teacher-1-1/u7.png"/>
        <!-- Unnamed () -->
        <div id="u8" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u9" class="ax_形状">
        <img id="u9_img" class="img " src="./images/student/u9.png"/>
        <!-- Unnamed () -->
        <div id="u10" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (tail) -->

      <!-- Unnamed (形状) -->
      <div id="u12" class="ax_形状">
        <img id="u12_img" class="img " src="./images/teacher-1-1/u73.png"/>
        <!-- Unnamed () -->
        <div id="u13" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (水平线) -->
      <div id="u14" class="ax_水平线">
        <img id="u14_start" class="img " src="./resources/images/transparent.gif" alt="u14_start"/>
        <img id="u14_end" class="img " src="./resources/images/transparent.gif" alt="u14_end"/>
        <img id="u14_line" class="img " src="./images/teacher-1-1/u75_line.png" alt="u14_line"/>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u15" class="ax_文本段落">
        <img id="u15_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u16" class="text">
          <p><span>© 2009 中国科学技术大学软件学院 </span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u17" class="ax_文本段落">
        <img id="u17_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u18" class="text">
          <p><span>合肥市徽州大道1129号&nbsp; &nbsp; &nbsp; </span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u19" class="ax_文本段落">
        <img id="u19_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u20" class="text">
          <p><span>邮编：230051</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u21" class="ax_文本段落">
        <img id="u21_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u22" class="text">
          <p><span>苏州工业园区仁爱路166号</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u23" class="ax_文本段落">
        <img id="u23_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u24" class="text">
          <p><span>邮编：215123</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u25" class="ax_文本段落">
        <img id="u25_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u26" class="text">
          <p><span>邮箱：websse@ustc.edu.cn</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u27" class="ax_文本段落">
        <img id="u27_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u28" class="text">
          <p><span>系统公告</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u29" class="ax_形状">
        <img id="u29_img" class="img " src="./images/student/u29.png"/>
        <!-- Unnamed () -->
        <div id="u30" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u31" class="ax_文本段落">
        <img id="u31_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u32" class="text">
          <p><span>个</span><span>人通知</span></p>
        </div>
      </div>

      <!-- Unnamed (水平线) -->
      <div id="u33" class="ax_水平线">
        <img id="u33_start" class="img " src="./resources/images/transparent.gif" alt="u33_start"/>
        <img id="u33_end" class="img " src="./resources/images/transparent.gif" alt="u33_end"/>
        <img id="u33_line" class="img " src="./images/student/u33_line.png" alt="u33_line"/>
      </div>

      <!-- wenzi1 (形状) -->
      <div id="u34" class="ax_形状" data-label="wenzi1">
        <img id="u34_img" class="img " src="./images/student/wenzi1_u34.png"/>
        <!-- Unnamed () -->
        <div id="u35" class="text">
          <p style="font-size:16px;"><span style="font-size:12px;">●</span><span style="font-size:16px;">&nbsp;</span><span style="font-size:16px;">16级合肥师生座谈会顺利举行</span></p>
        </div>
      </div>

      <!-- wenzi1 (形状) -->
      <div id="u36" class="ax_形状" data-label="wenzi1">
        <img id="u36_img" class="img " src="./images/student/wenzi1_u34.png"/>
        <!-- Unnamed () -->
        <div id="u37" class="text">
          <p style="font-size:16px;"><span style="font-size:12px;">●</span><span style="font-size:16px;"> 软件学院（苏州）召开教师与学生交流会</span><span style="font-size:16px;"></span></p>
        </div>
      </div>

      <!-- wenzi1 (形状) -->
      <div id="u38" class="ax_形状" data-label="wenzi1">
        <img id="u38_img" class="img " src="./images/student/wenzi1_u34.png"/>
        <!-- Unnamed () -->
        <div id="u39" class="text">
          <p style="font-size:16px;"><span style="font-size:12px;">●</span><span style="font-size:16px;"> 软件学院第一次团代表大会胜利召开</span><span style="font-size:16px;"></span></p>
        </div>
      </div>

      <!-- wenzi1 (形状) -->
      <div id="u40" class="ax_形状" data-label="wenzi1">
        <img id="u40_img" class="img " src="./images/student/wenzi1_u34.png"/>
        <!-- Unnamed () -->
        <div id="u41" class="text">
          <p style="font-size:16px;"><span style="font-size:12px;">●</span><span style="font-size:16px;"> 记16级合肥新生参观校史馆活动</span></p>
        </div>
      </div>

      <!-- wenzi1 (形状) -->
      <div id="u42" class="ax_形状" data-label="wenzi1">
        <img id="u42_img" class="img " src="./images/student/wenzi1_u34.png"/>
        <!-- Unnamed () -->
        <div id="u43" class="text">
          <p style="font-size:16px;"><span style="font-size:12px;">●</span><span style="font-size:16px;"> 软件学院举办“实现自我成长”心理专题讲座</span></p>
        </div>
      </div>

      <!-- wenzi1 (形状) -->
      <div id="u44" class="ax_形状" data-label="wenzi1">
        <img id="u44_img" class="img " src="./images/student/wenzi1_u34.png"/>
        <!-- Unnamed () -->
        <div id="u45" class="text">
          <p style="font-size:16px;"><span style="font-size:12px;">●</span><span style="font-size:16px;">&nbsp;</span><span style="font-size:16px;">开题报告时间通知</span></p>
        </div>
      </div>

      <!-- wenzi1 (形状) -->
      <div id="u46" class="ax_形状" data-label="wenzi1">
        <img id="u46_img" class="img " src="./images/student/wenzi1_u34.png"/>
        <!-- Unnamed () -->
        <div id="u47" class="text">
          <p style="font-size:16px;"><span style="font-size:12px;">●</span><span style="font-size:16px;">&nbsp;</span><span style="font-size:16px;">提交周记</span></p>
        </div>
      </div>

      <!-- wenzi1 (形状) -->
      <div id="u48" class="ax_形状" data-label="wenzi1">
        <img id="u48_img" class="img " src="./images/student/wenzi1_u34.png"/>
        <!-- Unnamed () -->
        <div id="u49" class="text">
          <p style="font-size:16px;"><span style="font-size:12px;">●</span><span style="font-size:16px;">&nbsp;</span><span style="font-size:16px;">软件测试计划</span></p>
        </div>
      </div>

      <!-- wenzi1 (形状) -->
      <div id="u50" class="ax_形状" data-label="wenzi1">
        <img id="u50_img" class="img " src="./images/student/wenzi1_u34.png"/>
        <!-- Unnamed () -->
        <div id="u51" class="text">
          <p style="font-size:16px;"><span style="font-size:12px;">●</span><span style="font-size:16px;">&nbsp;</span><span style="font-size:16px;">开会通知</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u52" class="ax_文本段落">
        <img id="u52_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u53" class="text">
          <p><span>&gt;</span><span>&gt;更多</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u54" class="ax_文本段落">
        <img id="u54_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u55" class="text">
          <p><span>&gt;</span><span>&gt;更多</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u56" class="ax_形状">
        <%--<img id="u56_img" class="img " src="././log/images/student/u57.png"/>--%>
        <!-- Unnamed () -->
        <div id="u57" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (老师头像) -->
      <div id="u58" class="ax_image">
        <img id="u58_img" class="img " src="./images/teacher/u58.jpg"/>
        <!-- Unnamed () -->
        <div id="u59" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u60" class="ax_形状">
        <img id="u60_img" class="img " src="./images/student/u61.png"/>
        <!-- Unnamed () -->
        <div id="u61" class="text">
          <p><span>姓名</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u62" class="ax_形状">
        <img id="u62_img" class="img " src="./images/student/u65.png"/>
        <!-- Unnamed () -->
        <div id="u63" class="text">
          <p><span><%=name%></span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u64" class="ax_形状">
        <img id="u64_img" class="img " src="./images/student/u65.png"/>
        <!-- Unnamed () -->
        <div id="u65" class="text">
          <p><span>学工号</span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u66" class="ax_形状">
        <img id="u66_img" class="img " src="./images/teacher/u66.png"/>
        <!-- Unnamed () -->
        <div id="u67" class="text">
          <p><span><%=staffId%></span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u68" class="ax_形状">
        <img id="u68_img" class="img " src="./images/student/u61.png"/>
        <!-- Unnamed () -->
        <div id="u69" class="text">
          <p><span>注销</span></p>
        </div>
      </div>

      <!-- Unnamed (垂直线) -->
      <div id="u70" class="ax_垂直线">
        <img id="u70_start" class="img " src="./resources/images/transparent.gif" alt="u70_start"/>
        <img id="u70_end" class="img " src="./resources/images/transparent.gif" alt="u70_end"/>
        <img id="u70_line" class="img " src="./images/student/u56_line.png" alt="u70_line"/>
      </div>

      <!-- Unnamed (menu-teacher) -->

      <!-- Unnamed (形状) -->
      <div id="u72" class="ax_形状">
        <img id="u72_img" class="img " src="./images/teacher-1-1/u97.png"/>
        <!-- Unnamed () -->
        <div id="u73" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- yijcaidanwenzi1 (形状) -->
      <div id="u74" class="ax_文本段落" data-label="yijcaidanwenzi1">
        <img id="u74_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u75" class="text">
          <p><span>已有项目</span></p>
        </div>
      </div>

      <!-- erjicaidan (动态面板) -->
      <div id="u76" class="ax_动态面板" data-label="erjicaidan">
        <div id="u76_state0" class="panel_state" data-label="xiangmu">
          <div id="u76_state0_content" class="panel_state_content">

            <!-- Unnamed (水平线) -->
            <div id="u77" class="ax_水平线">
              <img id="u77_start" class="img " src="./resources/images/transparent.gif" alt="u77_start"/>
              <img id="u77_end" class="img " src="./resources/images/transparent.gif" alt="u77_end"/>
              <img id="u77_line" class="img " src="./images/teacher-1-1/u75_line.png" alt="u77_line"/>
            </div>

            <!-- Unnamed (水平线) -->
            <div id="u78" class="ax_水平线">
              <img id="u78_start" class="img " src="./resources/images/transparent.gif" alt="u78_start"/>
              <img id="u78_end" class="img " src="./resources/images/transparent.gif" alt="u78_end"/>
              <img id="u78_line" class="img " src="./images/teacher-1-1/u75_line.png" alt="u78_line"/>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u79" class="ax_形状">
              <img id="u79_img" class="img " src="./images/teacher-1-1/u104.png"/>
              <!-- Unnamed () -->
              <div id="u80" class="text">
                <p><span></span></p>
              </div>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u81" class="ax_文本段落">
              <img id="u81_img" class="img " src="./resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u82" class="text">
                <p><span>我</span><span>的项目</span></p>
              </div>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u83" class="ax_文本段落">
              <img id="u83_img" class="img " src="./resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u84" class="text">
                <p><span>学</span><span>生自选</span></p>
              </div>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u85" class="ax_文本段落">
              <img id="u85_img" class="img " src="./resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u86" class="text">
                <p><span>新</span><span>建项目</span></p>
              </div>
            </div>
          </div>
        </div>
        <div id="u76_state1" class="panel_state" data-label="tuandui">
          <div id="u76_state1_content" class="panel_state_content">

            <!-- Unnamed (形状) -->
            <div id="u87" class="ax_形状">
              <img id="u87_img" class="img " src="./images/teacher-1-1/u104.png"/>
              <!-- Unnamed () -->
              <div id="u88" class="text">
                <p><span></span></p>
              </div>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u89" class="ax_文本段落">
              <img id="u89_img" class="img " src="./resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u90" class="text">
                <p><span>已</span><span>有团队</span></p>
              </div>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u91" class="ax_文本段落">
              <img id="u91_img" class="img " src="./resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u92" class="text">
                <p><span>新</span><span>建团队</span></p>
              </div>
            </div>

            <!-- Unnamed (水平线) -->
            <div id="u93" class="ax_水平线">
              <img id="u93_start" class="img " src="./resources/images/transparent.gif" alt="u93_start"/>
              <img id="u93_end" class="img " src="./resources/images/transparent.gif" alt="u93_end"/>
              <img id="u93_line" class="img " src="./images/teacher-1-1/u75_line.png" alt="u93_line"/>
            </div>
          </div>
        </div>
        <div id="u76_state2" class="panel_state" data-label="fabu">
          <div id="u76_state2_content" class="panel_state_content">

            <!-- Unnamed (形状) -->
            <div id="u94" class="ax_形状">
              <img id="u94_img" class="img " src="./images/teacher-1-1/u104.png"/>
              <!-- Unnamed () -->
              <div id="u95" class="text">
                <p><span></span></p>
              </div>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u96" class="ax_文本段落">
              <img id="u96_img" class="img " src="./resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u97" class="text">
                <p><span>计</span><span>划</span></p>
              </div>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u98" class="ax_文本段落">
              <img id="u98_img" class="img " src="./resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u99" class="text">
                <p><span>任</span><span>务</span></p>
              </div>
            </div>

            <!-- Unnamed (水平线) -->
            <div id="u100" class="ax_水平线">
              <img id="u100_start" class="img " src="./resources/images/transparent.gif" alt="u100_start"/>
              <img id="u100_end" class="img " src="./resources/images/transparent.gif" alt="u100_end"/>
              <img id="u100_line" class="img " src="./images/teacher-1-1/u75_line.png" alt="u100_line"/>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u101" class="ax_文本段落">
              <img id="u101_img" class="img " src="./resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u102" class="text">
                <p><span>通知</span></p>
              </div>
            </div>

            <!-- Unnamed (水平线) -->
            <div id="u103" class="ax_水平线">
              <img id="u103_start" class="img " src="./resources/images/transparent.gif" alt="u103_start"/>
              <img id="u103_end" class="img " src="./resources/images/transparent.gif" alt="u103_end"/>
              <img id="u103_line" class="img " src="./images/teacher-1-1/u75_line.png" alt="u103_line"/>
            </div>
          </div>
        </div>
        <div id="u76_state3" class="panel_state" data-label="gerenzhongxin">
          <div id="u76_state3_content" class="panel_state_content">

            <!-- Unnamed (形状) -->
            <div id="u104" class="ax_形状">
              <img id="u104_img" class="img " src="./images/teacher-1-1/u104.png"/>
              <!-- Unnamed () -->
              <div id="u105" class="text">
                <p><span></span></p>
              </div>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u106" class="ax_文本段落">
              <img id="u106_img" class="img " src="./resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u107" class="text">
                <p><span>个人资料</span></p>
              </div>
            </div>

            <!-- Unnamed (水平线) -->
            <div id="u108" class="ax_水平线">
              <img id="u108_start" class="img " src="./resources/images/transparent.gif" alt="u108_start"/>
              <img id="u108_end" class="img " src="./resources/images/transparent.gif" alt="u108_end"/>
              <img id="u108_line" class="img " src="./images/teacher-1-1/u75_line.png" alt="u108_line"/>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u109" class="ax_文本段落">
              <img id="u109_img" class="img " src="./resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u110" class="text">
                <p><span>我的学生</span></p>
              </div>
            </div>

            <!-- Unnamed (水平线) -->
            <div id="u111" class="ax_水平线">
              <img id="u111_start" class="img " src="./resources/images/transparent.gif" alt="u111_start"/>
              <img id="u111_end" class="img " src="./resources/images/transparent.gif" alt="u111_end"/>
              <img id="u111_line" class="img " src="./images/teacher-1-1/u75_line.png" alt="u111_line"/>
            </div>
          </div>
        </div>
        <div id="u76_state4" class="panel_state" data-label="xiangmupingtai">
          <div id="u76_state4_content" class="panel_state_content">

            <!-- Unnamed (形状) -->
            <div id="u112" class="ax_形状">
              <img id="u112_img" class="img " src="./images/teacher-1-1/u104.png"/>
              <!-- Unnamed () -->
              <div id="u113" class="text">
                <p><span></span></p>
              </div>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u114" class="ax_文本段落">
              <img id="u114_img" class="img " src="./resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u115" class="text">
                <p><span>项目平台</span></p>
              </div>
            </div>

            <!-- Unnamed (水平线) -->
            <div id="u116" class="ax_水平线">
              <img id="u116_start" class="img " src="./resources/images/transparent.gif" alt="u116_start"/>
              <img id="u116_end" class="img " src="./resources/images/transparent.gif" alt="u116_end"/>
              <img id="u116_line" class="img " src="./images/teacher-1-1/u75_line.png" alt="u116_line"/>
            </div>
          </div>
        </div>
      </div>

      <!-- yijcaidanwenzi1 (形状) -->
      <div id="u117" class="ax_文本段落" data-label="yijcaidanwenzi1">
        <img id="u117_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u118" class="text">
          <p><span>团队管理</span></p>
        </div>
      </div>

      <!-- yijcaidanwenzi1 (形状) -->
      <div id="u119" class="ax_文本段落" data-label="yijcaidanwenzi1">
        <img id="u119_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u120" class="text">
          <p><span>发布</span></p>
        </div>
      </div>

      <!-- yijcaidanwenzi1 (形状) -->
      <div id="u121" class="ax_文本段落" data-label="yijcaidanwenzi1">
        <img id="u121_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u122" class="text">
          <p><span>个人中心</span></p>
        </div>
      </div>

      <!-- yijcaidanwenzi1 (形状) -->
      <div id="u123" class="ax_文本段落" data-label="yijcaidanwenzi1">
        <img id="u123_img" class="img " src="./resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u124" class="text">
          <p><span>项目平台</span></p>
        </div>
      </div>
    </div>
  </body>
</html>
